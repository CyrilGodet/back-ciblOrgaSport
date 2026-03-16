package com.glop.cibl_orga_sport.service;

import com.glop.cibl_orga_sport.data.ParticipantEquipe;
import com.glop.cibl_orga_sport.dto.ParticipantEquipeDTO;
import java.util.List;
import java.util.Optional;

public interface ParticipantEquipeService {
    List<ParticipantEquipe> getAllEquipes();
    Optional<ParticipantEquipe> getEquipe(Long id);
    ParticipantEquipe createEquipe(String nomEquipe);
    ParticipantEquipe createEquipe(ParticipantEquipeDTO dto);
    List<ParticipantEquipe> searchEquipes(String query, Integer tailleEquipe);
    ParticipantEquipe updateEquipe(Long id, String nomEquipe);
    boolean deleteEquipe(Long id);
}
