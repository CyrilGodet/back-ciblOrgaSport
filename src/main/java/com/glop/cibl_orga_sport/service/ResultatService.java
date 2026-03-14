package com.glop.cibl_orga_sport.service;

import com.glop.cibl_orga_sport.dto.ResultatDTO;

public interface ResultatService {
    ResultatDTO saveDraftResultat(Long matchId, ResultatDTO resultatDTO);
    ResultatDTO completeResultat(Long matchId);
    void publishResultatsForEtape(Long idEtapeEpreuve);
}
