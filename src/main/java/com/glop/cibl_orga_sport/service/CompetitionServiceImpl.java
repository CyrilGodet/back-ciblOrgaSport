package com.glop.cibl_orga_sport.service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.glop.cibl_orga_sport.data.Competition;
import com.glop.cibl_orga_sport.data.Periode;
import com.glop.cibl_orga_sport.data.Lieu;
import com.glop.cibl_orga_sport.data.ConditionAge;
import com.glop.cibl_orga_sport.data.enumType.CompetitionStatusEnum;
import com.glop.cibl_orga_sport.data.enumType.CompetitionGenreEnum;
import com.glop.cibl_orga_sport.data.enumType.CompetitionSportEnum;
import com.glop.cibl_orga_sport.repository.CompetitionRepository;
import com.glop.cibl_orga_sport.repository.EquipeRepository;
import com.glop.cibl_orga_sport.repository.LieuRepository;
import com.glop.cibl_orga_sport.repository.ParticipationRepository;
import com.glop.cibl_orga_sport.dto.CompetitionDTO;
import com.glop.cibl_orga_sport.data.Equipe;
import com.glop.cibl_orga_sport.data.EtapeEpreuve;
import com.glop.cibl_orga_sport.data.Match;
import com.glop.cibl_orga_sport.data.Participation;
import com.glop.cibl_orga_sport.data.enumType.ParticipationStatusEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.stream.Collectors;

@Service
public class CompetitionServiceImpl implements CompetitionService {

    private static final Logger logger = LoggerFactory.getLogger(CompetitionServiceImpl.class);

    @Autowired
    private CompetitionRepository repository;

    @Autowired
    private LieuRepository lieuRepository;

    @Autowired
    private EquipeRepository equipeRepository;

    @Autowired
    private ParticipationRepository participationRepository;

    @Override
    public Competition createCompetition(CompetitionDTO dto) {
        if (dto.getDateDebut() != null && dto.getDateFin() != null && dto.getDateDebut().after(dto.getDateFin())) {
            throw new IllegalArgumentException("La date de début doit être avant la date de fin");
        }

        Periode periode = new Periode(dto.getDateDebut(), dto.getDateFin());
        Lieu lieu = getOrCreateLieu(dto.getLieu());
        ConditionAge conditionAge = new ConditionAge(dto.getAgeMin(), dto.getAgeMax());

        Competition c = new Competition(dto.getNameCompetition(), dto.getDescription(), periode, lieu, conditionAge,
                dto.getGenre(), CompetitionStatusEnum.DRAFT, dto.getSport());

        if (dto.getEpreuves() != null) {
            dto.getEpreuves().forEach(eDto -> {
                if (eDto.getNbPerMatch() <= 0) {
                    throw new IllegalArgumentException(
                            "Le nombre d'équipes par match (nbPerMatch) doit être supérieur à 0 pour l'épreuve '"
                                    + eDto.getNomEpreuve() + "'.");
                }
                if (eDto.getNbElimMatch() <= 0) {
                    throw new IllegalArgumentException(
                            "Le nombre d'équipes éliminées par match (nbElimMatch) doit être supérieur à 0 pour l'épreuve '"
                                    + eDto.getNomEpreuve() + "'.");
                }
                if (eDto.getNbElimMatch() >= eDto.getNbPerMatch()) {
                    throw new IllegalArgumentException(
                            "Le nombre d'équipes éliminées par match (nbElimMatch) doit être inférieur à nbPerMatch pour l'épreuve '"
                                    + eDto.getNomEpreuve() + "'.");
                }
                com.glop.cibl_orga_sport.data.Epreuve e = com.glop.cibl_orga_sport.mapper.EpreuveMapper.toEntity(eDto);
                c.addEpreuve(e);

                // Process participations
                if (eDto.getParticipations() != null) {
                    eDto.getParticipations().forEach(pDto -> {
                        if (pDto.getEquipe() != null && pDto.getEquipe().getIdEquipe() != null) {
                            Optional<Equipe> equipeOpt = equipeRepository.findById(pDto.getEquipe().getIdEquipe());
                            if (equipeOpt.isPresent()) {
                                Participation p = new Participation();
                                p.setEpreuve(e);
                                p.setEquipe(equipeOpt.get());
                                p.setStatut(
                                        pDto.getStatut() != null ? pDto.getStatut() : ParticipationStatusEnum.INSCRIT);
                                e.getParticipations().add(p);
                            } else {
                                logger.error("Équipe non trouvée avec l'ID: {}", pDto.getEquipe().getIdEquipe());
                            }
                        }
                    });
                }
            });
        }

        System.out.println("Création compétition : " + dto.getNameCompetition());
        return repository.save(c);
    }

    @Override
    public Competition updateCompetition(Long id, CompetitionDTO dto) {
        Competition c = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Compétition non trouvée : " + id));

        if (c.getStatut() != CompetitionStatusEnum.DRAFT) {
            System.out.println("Impossible de modifier une compétition qui n'est pas en DRAFT : " + id);
            return c;
        }

        if (dto.getDateDebut() != null && dto.getDateFin() != null && dto.getDateDebut().after(dto.getDateFin())) {
            throw new IllegalArgumentException("La date de début doit être avant la date de fin");
        }

        c.setNameCompetition(dto.getNameCompetition());
        c.setDescription(dto.getDescription());
        c.setSport(dto.getSport());
        c.setGenre(dto.getGenre());
        c.setStatut(dto.getStatut());

        if (dto.getDateDebut() != null && dto.getDateFin() != null) {
            if (c.getPeriode() == null) {
                c.setPeriode(new Periode(dto.getDateDebut(), dto.getDateFin()));
            } else {
                c.getPeriode().setDateDebut(dto.getDateDebut());
                c.getPeriode().setDateFin(dto.getDateFin());
            }
        }

        c.setLieu(getOrCreateLieu(dto.getLieu()));

        if (c.getConditionAge() == null) {
            c.setConditionAge(new ConditionAge(dto.getAgeMin(), dto.getAgeMax()));
        } else {
            c.getConditionAge().setAgeMin(dto.getAgeMin());
            c.getConditionAge().setAgeMax(dto.getAgeMax());
        }

        // Mise à jour des épreuves
        if (dto.getEpreuves() != null) {
            c.getEpreuves().clear();
            dto.getEpreuves().forEach(eDto -> {
                if (eDto.getNbPerMatch() <= 0) {
                    throw new IllegalArgumentException(
                            "Le nombre d'équipes par match (nbPerMatch) doit être supérieur à 0 pour l'épreuve '"
                                    + eDto.getNomEpreuve() + "'.");
                }
                if (eDto.getNbElimMatch() <= 0) {
                    throw new IllegalArgumentException(
                            "Le nombre d'équipes éliminées par match (nbElimMatch) doit être supérieur à 0 pour l'épreuve '"
                                    + eDto.getNomEpreuve() + "'.");
                }
                if (eDto.getNbElimMatch() >= eDto.getNbPerMatch()) {
                    throw new IllegalArgumentException(
                            "Le nombre d'équipes éliminées par match (nbElimMatch) doit être inférieur à nbPerMatch pour l'épreuve '"
                                    + eDto.getNomEpreuve() + "'.");
                }
                com.glop.cibl_orga_sport.data.Epreuve e = com.glop.cibl_orga_sport.mapper.EpreuveMapper.toEntity(eDto);
                c.addEpreuve(e);

                // Process participations
                if (eDto.getParticipations() != null) {
                    eDto.getParticipations().forEach(pDto -> {
                        if (pDto.getEquipe() != null && pDto.getEquipe().getIdEquipe() != null) {
                            Optional<Equipe> equipeOpt = equipeRepository.findById(pDto.getEquipe().getIdEquipe());
                            if (equipeOpt.isPresent()) {
                                // For update, we might want to check if it exists, but since we cleared
                                // epreuves, we recreate
                                Participation p = new Participation();
                                p.setEpreuve(e);
                                p.setEquipe(equipeOpt.get());
                                p.setStatut(
                                        pDto.getStatut() != null ? pDto.getStatut() : ParticipationStatusEnum.INSCRIT);
                                e.getParticipations().add(p);
                            } else {
                                logger.error("Équipe non trouvée avec l'ID: {}", pDto.getEquipe().getIdEquipe());
                            }
                        }
                    });
                }
            });
        }

        System.out.println("Modification compétition : " + id);
        return repository.save(c);
    }

    private Lieu getOrCreateLieu(com.glop.cibl_orga_sport.dto.LieuDTO lieuDTO) {
        if (lieuDTO == null) {
            return null;
        }

        // 1. Recherche par ID si présent
        if (lieuDTO.getIdLieu() != null) {
            Optional<Lieu> lieuOpt = lieuRepository.findById(lieuDTO.getIdLieu());
            if (lieuOpt.isPresent()) {
                return lieuOpt.get();
            }
        }

        // 2. Recherche par Nom et Ville si présent
        if (lieuDTO.getNomLieu() != null && lieuDTO.getVille() != null) {
            Optional<Lieu> lieuOpt = lieuRepository.findByNomLieuAndVille(lieuDTO.getNomLieu(), lieuDTO.getVille());
            if (lieuOpt.isPresent()) {
                return lieuOpt.get();
            }

            // 3. Création si non trouvé
            Lieu newLieu = new Lieu(lieuDTO.getNomLieu(), lieuDTO.getVille(), lieuDTO.getAdresse());
            System.out.println("Création automatique du lieu : " + lieuDTO.getNomLieu());
            return lieuRepository.save(newLieu);
        }

        return null;
    }

    @Override
    public boolean deleteCompetition(Long id) {
        Optional<Competition> c = repository.findById(id);
        if (c.isPresent()) {
            Competition competition = c.get();

            if (competition.getStatut() != CompetitionStatusEnum.DRAFT) {
                logger.warn(
                        "Tentative de suppression d'une compétition non-DRAFT (ID: {}). Passage en statut CANCELLED.",
                        id);
                competition.setStatut(CompetitionStatusEnum.CANCELLED);
                repository.save(competition);
                return false;
            }

            repository.delete(competition);
            logger.info(
                    "Compétition supprimée avec succès (ID: {}). Toutes les épreuves et participations liées ont été supprimées.",
                    id);
            return true;
        }
        logger.error("Échec de la suppression: Compétition non trouvée (ID: {})", id);
        return false;
    }

    @Override
    public List<Competition> getAllCompetitions() {
        return repository.findAll();
    }

    @Override
    public Optional<Competition> getCompetition(Long id) {
        return repository.findById(id);
    }

    @Override
    @jakarta.transaction.Transactional
    public Competition publishCompetition(Long id) {
        Optional<Competition> competitionOpt = repository.findById(id);
        if (competitionOpt.isEmpty()) {
            logger.error("Compétition non trouvée pour publication (ID: {})", id);
            return null;
        }

        Competition competition = competitionOpt.get();
        if (competition.getStatut() != CompetitionStatusEnum.DRAFT) {
            logger.warn("Échec de publication: La compétition n'est pas en mode DRAFT (ID: {})", id);
            return null;
        }

        // 1. Validation des données
        validateCompetitionForPublication(competition);

        // 2. Initialisation des étapes et matchs
        initializeCompetitionPhases(competition);

        competition.setStatut(CompetitionStatusEnum.PUBLISH);
        logger.info("Compétition publiée avec succès (ID: {}). Étapes et matchs initiaux générés.", id);
        return repository.save(competition);
    }

    private void validateCompetitionForPublication(Competition competition) {
        // Validation des dates de la compétition
        if (competition.getPeriode() == null || competition.getPeriode().getDateDebut() == null
                || competition.getPeriode().getDateFin() == null) {
            throw new IllegalStateException("La compétition doit avoir une période définie.");
        }
        if (competition.getPeriode().getDateDebut().after(competition.getPeriode().getDateFin())) {
            throw new IllegalStateException("La date de début de la compétition doit être avant la date de fin.");
        }

        if (competition.getEpreuves() == null || competition.getEpreuves().isEmpty()) {
            throw new IllegalStateException("La compétition doit avoir au moins une épreuve.");
        }

        for (com.glop.cibl_orga_sport.data.Epreuve epreuve : competition.getEpreuves()) {
            // Validation des dates de l'épreuve
            if (epreuve.getPeriode() != null) {
                if (epreuve.getPeriode().getDateDebut() != null
                        && epreuve.getPeriode().getDateDebut().before(competition.getPeriode().getDateDebut())) {
                    throw new IllegalStateException(
                            "L'épreuve '" + epreuve.getNomEpreuve() + "' ne peut pas commencer avant la compétition.");
                }
                if (epreuve.getPeriode().getDateFin() != null
                        && epreuve.getPeriode().getDateFin().after(competition.getPeriode().getDateFin())) {
                    throw new IllegalStateException(
                            "L'épreuve '" + epreuve.getNomEpreuve() + "' ne peut pas finir après la compétition.");
                }
            }

            // Validation du genre
            if (competition.getGenre() != com.glop.cibl_orga_sport.data.enumType.CompetitionGenreEnum.MIXTE) {
                if (epreuve.getGenre() != competition.getGenre()) {
                    throw new IllegalStateException("Le genre de l'épreuve '" + epreuve.getNomEpreuve() + "' ("
                            + epreuve.getGenre() + ") est incohérent avec celui de la compétition ("
                            + competition.getGenre() + ").");
                }
            }

            if (epreuve.getParticipations() == null || epreuve.getParticipations().isEmpty()) {
                throw new IllegalStateException(
                        "L'épreuve '" + epreuve.getNomEpreuve() + "' doit avoir au moins un participant.");
            }

            // Validation du nombre d'équipes par match
            if (epreuve.getNombreEquipeParMatch() <= 0) {
                throw new IllegalStateException(
                        "Le nombre d'équipes par match doit être supérieur à 0 pour l'épreuve '"
                                + epreuve.getNomEpreuve() + "'.");
            }

            // Validation du nombre d'équipes éliminées par match
            if (epreuve.getNbElimParMatch() <= 0) {
                throw new IllegalStateException(
                        "Le nombre d'équipes éliminées par match doit être supérieur à 0 pour l'épreuve '"
                                + epreuve.getNomEpreuve() + "'.");
            }
            if (epreuve.getNbElimParMatch() >= epreuve.getNombreEquipeParMatch()) {
                throw new IllegalStateException(
                        "Le nombre d'équipes éliminées par match doit être inférieur au nombre d'équipes par match pour l'épreuve '"
                                + epreuve.getNomEpreuve() + "'.");
            }
        }
    }

    private void initializeCompetitionPhases(Competition competition) {
        for (com.glop.cibl_orga_sport.data.Epreuve epreuve : competition.getEpreuves()) {
            epreuve.getEtapesEpreuves().clear();

            int nbEquipes = epreuve.getParticipations().size();
            int nbPerMatch = epreuve.getNombreEquipeParMatch();
            int nbElim = epreuve.getNbElimParMatch();
            int nbAdvance = nbPerMatch - nbElim;

            // Calculer les phases automatiquement
            List<com.glop.cibl_orga_sport.data.enumType.EtapeEpreuveEnum> phases = computePhases(
                    nbEquipes, nbPerMatch, nbAdvance);

            logger.info("Épreuve '{}': {} équipes, {} par match, {} éliminées → {} phases: {}",
                    epreuve.getNomEpreuve(), nbEquipes, nbPerMatch, nbElim,
                    phases.size(), phases);

            // Stocker les phases sur la compétition
            competition.getPhases().clear();
            for (com.glop.cibl_orga_sport.data.enumType.EtapeEpreuveEnum etapeEnum : phases) {
                competition.getPhases().add(mapEtapeToPhase(etapeEnum));
            }

            EtapeEpreuve firstEtape = null;

            for (int i = 0; i < phases.size(); i++) {
                com.glop.cibl_orga_sport.data.enumType.EtapeEpreuveEnum etapeEnum = phases.get(i);

                EtapeEpreuve etape = new EtapeEpreuve();
                etape.setEpreuve(epreuve);
                etape.setEtapeEpreuveEnum(etapeEnum);
                etape.setPeriode(epreuve.getPeriode());

                epreuve.getEtapesEpreuves().add(etape);

                if (i == 0) {
                    firstEtape = etape;
                }
            }

            // Génération des matchs pour la première étape
            if (firstEtape != null) {
                generateInitialMatches(firstEtape);
            }
        }
    }

    /**
     * Calcule la liste des phases (étapes) en fonction du nombre d'équipes,
     * du nombre d'équipes par match, et du nombre d'équipes qui avancent par match.
     * 
     * L'algorithme simule les tours successifs jusqu'à la finale,
     * puis attribue les noms de phases en partant de la fin:
     * FINALE → DEMI_FINALE → QUART_DE_FINALE → HUITIEME → SELECTION → PRE_SELECTION
     */
    private List<com.glop.cibl_orga_sport.data.enumType.EtapeEpreuveEnum> computePhases(
            int nbEquipes, int nbPerMatch, int nbAdvance) {

        // Calculer le nombre de tours nécessaires
        List<Integer> equipesParTour = new ArrayList<>();
        int remaining = nbEquipes;
        equipesParTour.add(remaining);

        int maxIterations = 100; // Sécurité anti-boucle infinie
        while (remaining > nbPerMatch && maxIterations-- > 0) {
            int nbMatchs = remaining / nbPerMatch; // Division entière (floor)
            int byeTeams = remaining % nbPerMatch; // Équipes qui passent directement
            remaining = nbMatchs * nbAdvance + byeTeams;
            equipesParTour.add(remaining);
        }
        // Le dernier tour est la finale

        int nbTours = equipesParTour.size();

        // Attribution des noms de phases en partant de la fin
        // Tableau des phases nommées (du dernier tour au premier)
        com.glop.cibl_orga_sport.data.enumType.EtapeEpreuveEnum[] namedPhases = {
                com.glop.cibl_orga_sport.data.enumType.EtapeEpreuveEnum.FINALE,
                com.glop.cibl_orga_sport.data.enumType.EtapeEpreuveEnum.DEMI_FINALE,
                com.glop.cibl_orga_sport.data.enumType.EtapeEpreuveEnum.QUART_DE_FINALE,
                com.glop.cibl_orga_sport.data.enumType.EtapeEpreuveEnum.HUITIEME,
                com.glop.cibl_orga_sport.data.enumType.EtapeEpreuveEnum.SELECTION,
                com.glop.cibl_orga_sport.data.enumType.EtapeEpreuveEnum.PRE_SELECTION
        };

        List<com.glop.cibl_orga_sport.data.enumType.EtapeEpreuveEnum> result = new ArrayList<>();

        for (int i = 0; i < nbTours; i++) {
            // Index dans namedPhases: le dernier tour = 0 (FINALE), avant-dernier = 1, etc.
            int reverseIndex = nbTours - 1 - i;
            if (reverseIndex < namedPhases.length) {
                result.add(namedPhases[reverseIndex]);
            } else {
                // Plus de noms disponibles, utiliser PRE_SELECTION
                result.add(com.glop.cibl_orga_sport.data.enumType.EtapeEpreuveEnum.PRE_SELECTION);
            }
        }

        return result;
    }

    private com.glop.cibl_orga_sport.data.enumType.CompetitionPhaseType mapEtapeToPhase(
            com.glop.cibl_orga_sport.data.enumType.EtapeEpreuveEnum etape) {
        return switch (etape) {
            case PRE_SELECTION -> com.glop.cibl_orga_sport.data.enumType.CompetitionPhaseType.PRE_SELECTION;
            case SELECTION -> com.glop.cibl_orga_sport.data.enumType.CompetitionPhaseType.SELECTION;
            case HUITIEME -> com.glop.cibl_orga_sport.data.enumType.CompetitionPhaseType.HUITIEME;
            case QUART_DE_FINALE -> com.glop.cibl_orga_sport.data.enumType.CompetitionPhaseType.QUART_DE_FINALE;
            case DEMI_FINALE -> com.glop.cibl_orga_sport.data.enumType.CompetitionPhaseType.DEMI_FINALE;
            case FINALE -> com.glop.cibl_orga_sport.data.enumType.CompetitionPhaseType.FINALE;
        };
    }

    private void generateInitialMatches(EtapeEpreuve etape) {
        com.glop.cibl_orga_sport.data.Epreuve epreuve = etape.getEpreuve();
        List<Equipe> equipes = epreuve.getParticipations().stream()
                .map(com.glop.cibl_orga_sport.data.Participation::getEquipe)
                .collect(java.util.stream.Collectors.toList());

        logger.info("Génération des matchs pour l'épreuve: {} - Étape: {}. Nombre total d'équipes: {}",
                epreuve.getNomEpreuve(), etape.getEtapeEpreuveEnum(), equipes.size());

        etape.getEquipes().addAll(equipes);

        int nbPerMatch = epreuve.getNombreEquipeParMatch();

        logger.info("Configuration: {} équipes par match.", nbPerMatch);

        List<Match> matches = new ArrayList<>();
        for (int i = 0; i < equipes.size(); i += nbPerMatch) {
            Match match = new Match();
            match.setEtapeEpreuve(etape);
            match.setPeriode(etape.getPeriode());
            match.setStatus(com.glop.cibl_orga_sport.data.enumType.MatchStatusEnum.IN_PROGRESS);

            List<Equipe> matchEquipes = new ArrayList<>();
            for (int j = 0; j < nbPerMatch && (i + j) < equipes.size(); j++) {
                Equipe eq = equipes.get(i + j);
                matchEquipes.add(eq);
            }
            match.setEquipes(matchEquipes);
            matches.add(match);

            logger.info("Match généré: {} équipes - {}", matchEquipes.size(),
                    matchEquipes.stream().map(Equipe::getNomEquipe).collect(java.util.stream.Collectors.joining(", ")));
        }
        etape.setMatches(matches);
        logger.info("Total de {} matchs générés pour l'étape {}.", matches.size(), etape.getEtapeEpreuveEnum());
    }

    @Override
    public Competition startCompetition(Long id) {
        Optional<Competition> competitionOpt = repository.findById(id);
        if (competitionOpt.isPresent()) {
            Competition competition = competitionOpt.get();
            if (competition.getStatut() != CompetitionStatusEnum.PUBLISH) {
                System.out.println("Impossible de démarrer : la compétition n'est pas publiée - ID: " + id);
                return null;
            }
            competition.setStatut(CompetitionStatusEnum.IN_PROGRESS);
            System.out.println("Compétition démarrée : " + id);
            return repository.save(competition);
        }
        System.out.println("Compétition non trouvée : " + id);
        return null;
    }

    @Override
    public Competition finishCompetition(Long id) {
        Optional<Competition> competitionOpt = repository.findById(id);
        if (competitionOpt.isPresent()) {
            Competition competition = competitionOpt.get();
            if (competition.getStatut() != CompetitionStatusEnum.IN_PROGRESS) {
                System.out.println("Impossible de terminer : la compétition n'est pas en cours - ID: " + id);
                return null;
            }
            competition.setStatut(CompetitionStatusEnum.FINISHED);
            System.out.println("Compétition terminée : " + id);
            return repository.save(competition);
        }
        System.out.println("Compétition non trouvée : " + id);
        return null;
    }
}
