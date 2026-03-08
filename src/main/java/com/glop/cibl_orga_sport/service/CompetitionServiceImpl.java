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
import com.glop.cibl_orga_sport.data.Participation;
import com.glop.cibl_orga_sport.data.enumType.ParticipationStatusEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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

        if (dto.getPhases() != null) {
            c.setPhases(dto.getPhases().stream()
                    .map(phaseDto -> com.glop.cibl_orga_sport.data.enumType.CompetitionPhaseType
                            .valueOf(phaseDto.getValue()))
                    .collect(java.util.stream.Collectors.toList()));
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

        if (dto.getPhases() != null) {
            c.getPhases().clear();
            c.getPhases().addAll(dto.getPhases().stream()
                    .map(phaseDto -> com.glop.cibl_orga_sport.data.enumType.CompetitionPhaseType
                            .valueOf(phaseDto.getValue()))
                    .collect(java.util.stream.Collectors.toList()));
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
    public Competition publishCompetition(Long id) {
        Optional<Competition> competitionOpt = repository.findById(id);
        if (competitionOpt.isPresent()) {
            Competition competition = competitionOpt.get();
            if (competition.getStatut() != CompetitionStatusEnum.DRAFT) {
                System.out.println("Impossible de publier : la compétition n'est pas en mode DRAFT - ID: " + id);
                return null;
            }
            competition.setStatut(CompetitionStatusEnum.PUBLISH);
            System.out.println("Compétition publiée : " + id);
            return repository.save(competition);
        }
        System.out.println("Compétition non trouvée : " + id);
        return null;
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
