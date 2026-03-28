package com.glop.cibl_orga_sport.service;

import com.glop.cibl_orga_sport.data.*;
import com.glop.cibl_orga_sport.data.enumType.MatchStatusEnum;
import com.glop.cibl_orga_sport.data.enumType.ParticipationStatusEnum;
import com.glop.cibl_orga_sport.data.enumType.CompetitionPhaseType;
import com.glop.cibl_orga_sport.data.enumType.CompetitionStatusEnum;
import com.glop.cibl_orga_sport.data.enumType.EtapeEpreuveEnum;
import com.glop.cibl_orga_sport.data.enumType.ResultatDetailsStatusEnum;
import com.glop.cibl_orga_sport.data.enumType.ResultatStatusEnum;
import com.glop.cibl_orga_sport.dto.ResultatDTO;
import com.glop.cibl_orga_sport.dto.ResultatDetailsDTO;
import com.glop.cibl_orga_sport.mapper.ResultatMapper;
import com.glop.cibl_orga_sport.repository.*;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class ResultatServiceImpl implements ResultatService {

    private static final Logger logger = LoggerFactory.getLogger(ResultatServiceImpl.class);

    @Autowired
    private MatchRepository matchRepository;

    @Autowired
    private EtapeEpreuveRepository etapeEpreuveRepository;

    @Autowired
    private EpreuveRepository epreuveRepository;

    @Autowired
    private ResultatRepository resultatRepository;

    @Autowired
    private ParticipantEquipeRepository equipeRepository;

    @Autowired
    private ParticipationRepository participationRepository;

    @Autowired
    private ParticipantRepository participantRepository;


    @Override
    @Transactional
    public ResultatDTO saveDraftResultat(Long matchId, ResultatDTO resultatDTO) {
        logger.info("Tentative de sauvegarde du résultat (brouillon) pour le match ID: {}", matchId);
        Match match = matchRepository.findById(matchId)
                .orElseThrow(() -> new IllegalArgumentException("Match non trouvé : " + matchId));

        Resultat resultat = null;
        if (match.getResultat() != null) {
            resultat = match.getResultat();
            resultat.getDetails().clear(); // Clear existing details to replace them
        } else {
            resultat = new Resultat();
        }
        resultat.setStatus(resultatDTO.getStatus() != null ? resultatDTO.getStatus() : ResultatStatusEnum.DRAFT);

        if (resultatDTO.getDetails() != null) {
            for (ResultatDetailsDTO detailDTO : resultatDTO.getDetails()) {
                if (detailDTO.getParticipant() == null || detailDTO.getParticipant().getIdParticipant() == null) {
                    throw new IllegalArgumentException("Le participant est obligatoire pour chaque détail de résultat.");
                }
                
                // Since this might be a ParticipantEquipe or a ParticipantSportif, we need a ParticipantRepository
                // We'll temporarly stick to Equipe if the ID maps, otherwise we should use a generic one.
                // Assuming Eq has been migrated, let's cast or find. Ideally we need a ParticipantRepository.
                // Fixing for the context provided
                ParticipantEquipe equipe = equipeRepository.findById(detailDTO.getParticipant().getIdParticipant())
                        .orElseThrow(() -> new IllegalArgumentException(
                                "Participant (Equipe) non trouvé : " + detailDTO.getParticipant().getIdParticipant()));

                ResultatDetails detail = new ResultatDetails();
                detail.setParticipant(equipe);
                detail.setRang(detailDTO.getRang());
                detail.setStatus(detailDTO.getStatus());
                detail.setValeur(detailDTO.getValeur());

                resultat.addDetail(detail);
                logger.info("Ajout du détail pour le participant ID: {}, rang: {}, status: {}",
                        equipe.getIdParticipant(), detail.getRang(), detail.getStatus());
            }
        }

        resultat = resultatRepository.save(resultat);
        logger.info("Entité Resultat sauvegardée avec ID: {}", resultat.getIdResultat());
        match.setResultat(resultat);
        match.setStatus(MatchStatusEnum.FINISHED);
        matchRepository.save(match);

        return ResultatMapper.toDTO(resultat);
    }

    @Override
    @Transactional
    public ResultatDTO completeResultat(Long matchId) {
        Match match = matchRepository.findById(matchId)
                .orElseThrow(() -> new IllegalArgumentException("Match non trouvé : " + matchId));

        if (match.getResultat() == null) {
            throw new IllegalStateException("Aucun résultat n'est associé à ce match.");
        }

        Resultat resultat = match.getResultat();
        resultat.setStatus(ResultatStatusEnum.COMPLETED);
        resultat = resultatRepository.save(resultat);

        return ResultatMapper.toDTO(resultat);
    }

    @Override
    @Transactional
    public void publishResultatsForEtape(Long idEtapeEpreuve) {
        EtapeEpreuve etapeEpreuve = etapeEpreuveRepository.findById(idEtapeEpreuve)
                .orElseThrow(() -> new IllegalArgumentException("EtapeEpreuve non trouvée : " + idEtapeEpreuve));

        List<Match> matches = etapeEpreuve.getMatches();

        if (matches == null || matches.isEmpty()) {
            throw new IllegalStateException("L'étape ne contient aucun match.");
        }

        // Vérifier que tous les matchs ont un résultat et qu'ils sont complétés
        for (Match match : matches) {
            if (match.getResultat() == null) {
                throw new IllegalStateException(
                        "Impossible de publier : le match " + match.getIdMatch() + " n'a pas de résultat saisi.");
            }
            if (match.getResultat().getStatus() != ResultatStatusEnum.COMPLETED) {
                throw new IllegalStateException("Impossible de publier : le résultat du match " + match.getIdMatch()
                        + " n'est pas marqué comme COMPLETED.");
            }
        }

        // 1. Agréger les résultats par match au niveau de l'étape
        Resultat etapeResultat = etapeEpreuve.getResultat();
        if (etapeResultat == null) {
            etapeResultat = new Resultat(ResultatStatusEnum.PUBLISHED);
            etapeEpreuve.setResultat(etapeResultat);
        } else {
            etapeResultat.setStatus(ResultatStatusEnum.PUBLISHED);
            etapeResultat.getDetails().clear();
        }

        List<ParticipantEquipe> qualifiedTeams = new ArrayList<>();
        Epreuve epreuve = etapeEpreuve.getEpreuve();
        Competition competition = epreuve.getCompetition();
        List<Participation> participations = participationRepository.findByCompetition_IdCompetition(competition.getIdCompetition());

        for (Match match : matches) {
            Resultat matchResultat = match.getResultat();
            matchResultat.setStatus(ResultatStatusEnum.PUBLISHED);
            resultatRepository.save(matchResultat);

            for (ResultatDetails matchDetail : matchResultat.getDetails()) {
                // Ajouter le détail au résultat de l'étape
                ResultatDetails etapeDetail = new ResultatDetails(
                        matchDetail.getParticipant(), etapeResultat, matchDetail.getRang(), matchDetail.getStatus(), matchDetail.getValeur());
                etapeResultat.addDetail(etapeDetail);

                // Mettre à jour la participation
                // Warning: Downcasting to ParticipantEquipe for logic continuity, but ideally handled polymorphically
                if (matchDetail.getParticipant() instanceof ParticipantEquipe) {
                    updateParticipationStatus(participations, (ParticipantEquipe) matchDetail.getParticipant(), matchDetail.getStatus());
                    
                    if (matchDetail.getStatus() == ResultatDetailsStatusEnum.QUALIFIE) {
                        qualifiedTeams.add((ParticipantEquipe) matchDetail.getParticipant());
                    }
                }
            }
        }
        etapeResultat = resultatRepository.save(etapeResultat);
        participationRepository.saveAll(participations);
        etapeEpreuveRepository.save(etapeEpreuve);
        logger.info("Publication terminée pour l'étape ID: {}", idEtapeEpreuve);

        // 2. Déterminer la phase suivante dynamiquement
        int nbQualified = qualifiedTeams.size();

        // Validation pour la finale
        if (etapeEpreuve.getEtapeEpreuveEnum() == EtapeEpreuveEnum.FINALE) {
            if (nbQualified > 1) {
                throw new IllegalStateException("Impossible de publier la finale : il ne peut y avoir qu'un seul gagnant (QUALIFIE), mais " + nbQualified + " ont été trouvés.");
            }
        }

        if (nbQualified == 1) {
            // Un seul gagnant -> L'épreuve est terminée
            epreuve.setStatut(CompetitionStatusEnum.FINISHED);
            epreuve.setPhaseOnGoing(null); // Plus de phase en cours si fini
            epreuveRepository.save(epreuve);
            logger.info("Épreuve '{}' terminée ! Gagnant: {}", epreuve.getNomEpreuve(), qualifiedTeams.get(0).getNomEquipe());
        } else if (nbQualified > 1) {
            EtapeEpreuve nextEtape = determineNextLogicalEtape(epreuve, etapeEpreuve, nbQualified);
            if (nextEtape != null) {
                // Changing to List<Participant> to match the interface of Match
                List<Participant> qualifiedParticipants = new ArrayList<>(qualifiedTeams);
                generateNextPhaseMatches(nextEtape, qualifiedParticipants);
                etapeEpreuveRepository.save(nextEtape);

                // Mettre à jour la phase en cours de l'épreuve
                epreuve.setPhaseOnGoing(mapEtapeToPhaseOnGoing(nextEtape.getEtapeEpreuveEnum()));
                epreuveRepository.save(epreuve);
                logger.info("Phase suivante déterminée pour '{}' : {}", epreuve.getNomEpreuve(),
                        epreuve.getPhaseOnGoing());
            } else {
                logger.info("Pas de phase suivante après {}", etapeEpreuve.getEtapeEpreuveEnum());
            }
        } else {
            logger.warn("Aucun qualifié pour l'étape {}", etapeEpreuve.getEtapeEpreuveEnum());
        }
    }

    private void updateParticipationStatus(List<Participation> participations, ParticipantEquipe equipe, ResultatDetailsStatusEnum resultStatus) {
        participations.stream()
            .filter(p -> p.getParticipant() != null && p.getParticipant().getIdParticipant().equals(equipe.getIdParticipant()))
            .findFirst()
            .ifPresent(p -> {
                ParticipationStatusEnum partStatus = switch (resultStatus) {
                    case QUALIFIE -> ParticipationStatusEnum.QUALIFIE;
                    case ELIMINE -> ParticipationStatusEnum.ELIMINE;
                    case ABANDON -> ParticipationStatusEnum.ABANDON;
                    case EXCLUS -> ParticipationStatusEnum.EXCLUS;
                };
                p.setStatut(partStatus);
            });
    }

    private EtapeEpreuve determineNextLogicalEtape(Epreuve epreuve, EtapeEpreuve currentEtape, int nbQualified) {
        List<EtapeEpreuve> allEtapes = epreuve.getEtapesEpreuves();
        
        // Déterminer le type d'étape théorique basé sur le nombre de qualifiés
        EtapeEpreuveEnum theoreticalEnum = null;
        if (nbQualified <= 2) theoreticalEnum = EtapeEpreuveEnum.FINALE;
        else if (nbQualified <= 4) theoreticalEnum = EtapeEpreuveEnum.DEMI_FINALE;
        else if (nbQualified <= 8) theoreticalEnum = EtapeEpreuveEnum.QUART_DE_FINALE;
        else if (nbQualified <= 16) theoreticalEnum = EtapeEpreuveEnum.HUITIEME;
        
        // Si on a un enum théorique, on cherche si l'étape existe déjà
        if (theoreticalEnum != null) {
            final EtapeEpreuveEnum target = theoreticalEnum;
            Optional<EtapeEpreuve> targetEtape = allEtapes.stream()
                .filter(e -> e.getEtapeEpreuveEnum() == target)
                .findFirst();
            if (targetEtape.isPresent()) return targetEtape.get();
        }

        // Sinon (ou si non trouvé), on prend simplement l'étape suivante dans la liste indexée
        int currentIndex = allEtapes.indexOf(currentEtape);
        if (currentIndex != -1 && currentIndex + 1 < allEtapes.size()) {
            return allEtapes.get(currentIndex + 1);
        }
        
        return null;
    }

    private CompetitionPhaseType mapEtapeToPhaseOnGoing(EtapeEpreuveEnum etapeEnum) {
        return CompetitionPhaseType.valueOf(etapeEnum.name());
    }

    private void generateNextPhaseMatches(EtapeEpreuve nextEtape, List<Participant> equipes) {
        // Here we map getEquipes to getParticipants
        // However EtapeEpreuve still has getEquipes, for now lets clear and add
        nextEtape.getParticipants().addAll(equipes);
        int nbPerMatch = nextEtape.getEpreuve().getNombreEquipeParMatch();

        List<Match> matches = new ArrayList<>();
        for (int i = 0; i < equipes.size(); i += nbPerMatch) {
            Match match = new Match();
            match.setEtapeEpreuve(nextEtape);
            match.setPeriode(nextEtape.getPeriode()); // Peut être mis à jour plus tard par l'orga
            match.setStatus(MatchStatusEnum.PENDING); // Nouveau match = PENDING

            List<Participant> matchEquipes = new ArrayList<>();
            for (int j = 0; j < nbPerMatch && (i + j) < equipes.size(); j++) {
                Participant eq = equipes.get(i + j);
                matchEquipes.add(eq);
            }
            match.setParticipants(matchEquipes);
            matches.add(match);
        }

        // Si d'anciens matchs existaient, on peut les effacer ou on ajoute.
        // Puisque c'est généré à la volée, on remplace.
        nextEtape.getMatches().clear();
        nextEtape.getMatches().addAll(matches);
    }

    @Override
    @Transactional
    public void declareAbandonForMatch(Long matchId, Long participantId) {
        logger.info("Déclaration d'abandon pour le match ID: {} et le participant ID: {}", matchId, participantId);
        
        Match match = matchRepository.findById(matchId)
                .orElseThrow(() -> new IllegalArgumentException("Match non trouvé : " + matchId));
        
        Participant participant = participantRepository.findById(participantId)
                .orElseThrow(() -> new IllegalArgumentException("Participant non trouvé : " + participantId));
        
        // Vérifier que le participant est bien dans le match
        boolean found = match.getParticipants().stream()
                .anyMatch(p -> p.getIdParticipant().equals(participantId));
        if (!found) {
            throw new IllegalArgumentException("Le participant " + participantId + " ne fait pas partie du match " + matchId);
        }

        Resultat resultat = match.getResultat();
        if (resultat == null) {
            resultat = new Resultat(ResultatStatusEnum.DRAFT);
            match.setResultat(resultat);
        }

        final Resultat finalResult = resultat;
        // Trouver ou créer le détail pour ce participant
        ResultatDetails detail = resultat.getDetails().stream()
                .filter(d -> d.getParticipant().getIdParticipant().equals(participantId))
                .findFirst()
                .orElseGet(() -> {
                    ResultatDetails newDetail = new ResultatDetails();
                    newDetail.setParticipant(participant);
                    finalResult.addDetail(newDetail);
                    return newDetail;
                });

        detail.setStatus(ResultatDetailsStatusEnum.ABANDON);
        detail.setRang(99); // Rang élevé pour indiquer l'abandon
        detail.setValeur(0.0);

        resultatRepository.save(resultat);
        matchRepository.save(match);
        logger.info("Abandon enregistré pour le participant {} dans le match {}", participantId, matchId);
    }
}
