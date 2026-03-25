package com.glop.cibl_orga_sport.service.impl;

import com.glop.cibl_orga_sport.data.Commissaire;
import com.glop.cibl_orga_sport.data.EtapeEpreuve;
import com.glop.cibl_orga_sport.data.Participant;
import com.glop.cibl_orga_sport.data.ParticipantEquipe;
import com.glop.cibl_orga_sport.data.ParticipantSportif;
import com.glop.cibl_orga_sport.data.Participation;
import com.glop.cibl_orga_sport.data.Sportif;
import com.glop.cibl_orga_sport.data.Utilisateur;
import com.glop.cibl_orga_sport.dto.ConformiteCharteEuropeenneDTO;
import com.glop.cibl_orga_sport.dto.SportifInscritDTO;
import com.glop.cibl_orga_sport.dto.SportifNonConformeDTO;
import com.glop.cibl_orga_sport.exception.InvalidEntityException;
import com.glop.cibl_orga_sport.repository.EtapeEpreuveRepository;
import com.glop.cibl_orga_sport.repository.ParticipationRepository;
import com.glop.cibl_orga_sport.repository.UtilisateurRepository;
import com.glop.cibl_orga_sport.service.CommissaireService;
import com.glop.cibl_orga_sport.service.ResultatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
public class CommissaireServiceImpl implements CommissaireService {

    @Autowired
    private ParticipationRepository participationRepository;

    @Autowired
    private UtilisateurRepository utilisateurRepository;

    @Autowired
    private EtapeEpreuveRepository etapeEpreuveRepository;

    @Autowired
    private ResultatService resultatService;

    @Override
    public List<SportifInscritDTO> listerSportifsInscrits(Long idCompetition) {
        Map<Long, Sportif> sportifsInscrits = collecterSportifsInscritsParCompetition(idCompetition);

        return sportifsInscrits.values().stream()
                .map(s -> new SportifInscritDTO(
                        s.getIdUtilisateur(),
                        s.getNom(),
                        s.getPrenom(),
                        s.getEmail(),
                        s.isEstConformeCharteEuropeenne()))
                .toList();
    }

    @Override
    public ConformiteCharteEuropeenneDTO verifierConformiteCharteEuropeenne(Long idCompetition) {
        Map<Long, Sportif> sportifsInscrits = collecterSportifsInscritsParCompetition(idCompetition);

        List<SportifNonConformeDTO> nonConformes = sportifsInscrits.values().stream()
                .filter(s -> !s.isEstConformeCharteEuropeenne())
                .map(s -> new SportifNonConformeDTO(
                        s.getIdUtilisateur(),
                        s.getNom(),
                        s.getPrenom(),
                        s.getEmail()))
                .toList();

        long total = sportifsInscrits.size();
        long totalNonConformes = nonConformes.size();
        long totalConformes = total - totalNonConformes;

        return new ConformiteCharteEuropeenneDTO(
                idCompetition,
                totalNonConformes == 0,
                total,
                totalConformes,
                totalNonConformes,
                nonConformes);
    }

    @Override
    public void validerSaisieResultats(Long idCompetition, Long idEtapeEpreuve, Long idCommissaire,
            boolean saisieManuelle) {
        Utilisateur utilisateur = utilisateurRepository.findById(idCommissaire)
                .orElseThrow(() -> new InvalidEntityException("Commissaire introuvable: " + idCommissaire));

        if (!(utilisateur instanceof Commissaire)) {
            throw new InvalidEntityException("L'utilisateur " + idCommissaire + " n'est pas un commissaire.");
        }

        Commissaire commissaire = (Commissaire) utilisateur;

        if (saisieManuelle && !commissaire.estAcrediteCEN) {
            throw new InvalidEntityException(
                    "Saisie manuelle interdite: le commissaire n'est pas accredite CEN.");
        }

        EtapeEpreuve etape = etapeEpreuveRepository.findById(idEtapeEpreuve)
                .orElseThrow(() -> new InvalidEntityException("Etape d'epreuve introuvable: " + idEtapeEpreuve));

        if (etape.getEpreuve() == null || etape.getEpreuve().getCompetition() == null) {
            throw new InvalidEntityException("Etape d'epreuve invalide: competition introuvable.");
        }

        Long idCompetitionEtape = etape.getEpreuve().getCompetition().getIdCompetition();
        if (!idCompetition.equals(idCompetitionEtape)) {
            throw new InvalidEntityException(
                    "L'etape " + idEtapeEpreuve + " n'appartient pas a la competition " + idCompetition + ".");
        }

        resultatService.publishResultatsForEtape(idEtapeEpreuve);
    }

    private Map<Long, Sportif> collecterSportifsInscritsParCompetition(Long idCompetition) {
        List<Participation> participations = participationRepository.findByCompetition_IdCompetition(idCompetition);

        Map<Long, Sportif> sportifs = new LinkedHashMap<>();

        for (Participation participation : participations) {
            Participant participant = participation.getParticipant();
            if (participant == null) {
                continue;
            }

            if (participant instanceof ParticipantSportif) {
                Sportif sportif = ((ParticipantSportif) participant).getSportif();
                if (sportif != null && sportif.getIdUtilisateur() != null) {
                    sportifs.putIfAbsent(sportif.getIdUtilisateur(), sportif);
                }
            } else if (participant instanceof ParticipantEquipe) {
                ParticipantEquipe equipe = (ParticipantEquipe) participant;
                if (equipe.getSportifs() != null) {
                    for (Sportif sportif : equipe.getSportifs()) {
                        if (sportif != null && sportif.getIdUtilisateur() != null) {
                            sportifs.putIfAbsent(sportif.getIdUtilisateur(), sportif);
                        }
                    }
                }
            }
        }

        return sportifs;
    }
}
