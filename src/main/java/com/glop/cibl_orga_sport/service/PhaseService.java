package com.glop.cibl_orga_sport.service;

import com.glop.cibl_orga_sport.data.Phase;
import com.glop.cibl_orga_sport.data.Epreuve;
import java.util.List;
import java.util.Optional;

public interface PhaseService {

    Phase createPhase(String nomPhase, Epreuve epreuve);
    Phase updatePhase(Long id, String nomPhase, Epreuve epreuve);
    boolean deletePhase(Long id);
    List<Phase> getAllPhases();
    Optional<Phase> getPhase(Long id);
}
