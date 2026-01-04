package com.glop.cibl_orga_sport.service;

import com.glop.cibl_orga_sport.data.Phase;
import com.glop.cibl_orga_sport.data.Epreuve;
import com.glop.cibl_orga_sport.data.Lieu;

import java.util.List;
import java.util.Optional;

public interface PhaseService {

    Phase createPhase(String nomPhase, java.sql.Date dateDebut, java.sql.Date dateFin, Epreuve epreuve, Lieu lieu);

    Phase updatePhase(Long id, String nomPhase, java.sql.Date dateDebut, java.sql.Date dateFin, Epreuve epreuve,
            Lieu lieu);

    boolean deletePhase(Long id);

    List<Phase> getAllPhases();

    Optional<Phase> getPhase(Long id);

    List<Phase> getPhasesByCompetitionId(Long competitionId);
}
