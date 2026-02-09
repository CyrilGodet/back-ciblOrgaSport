package com.glop.cibl_orga_sport.service;

import com.glop.cibl_orga_sport.data.EtapeEpreuve;
import com.glop.cibl_orga_sport.data.Epreuve;
import com.glop.cibl_orga_sport.data.Lieu;

import java.util.List;
import java.util.Optional;

public interface PhaseService {

    EtapeEpreuve createPhase(String nomPhase, java.sql.Date dateDebut, java.sql.Date dateFin, Epreuve epreuve, Lieu lieu);

    EtapeEpreuve updatePhase(Long id, String nomPhase, java.sql.Date dateDebut, java.sql.Date dateFin, Epreuve epreuve,
            Lieu lieu);

    boolean deletePhase(Long id);

    List<EtapeEpreuve> getAllPhases();

    Optional<EtapeEpreuve> getPhase(Long id);

    List<EtapeEpreuve> getPhasesByCompetitionId(Long competitionId);
}
