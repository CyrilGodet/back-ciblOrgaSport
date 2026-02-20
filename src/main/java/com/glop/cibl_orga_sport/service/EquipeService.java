package com.glop.cibl_orga_sport.service;

import com.glop.cibl_orga_sport.data.Equipe;

import java.util.List;
import java.util.Optional;

public interface EquipeService {
    List<Equipe> getAllEquipes();
    Optional<Equipe> getEquipe(Long id);
    List<Equipe> getEquipesByCompetitionId(Long competitionId);
    Equipe createEquipe(String nomEquipe, Long competitionId);
    Equipe updateEquipe(Long id, String nomEquipe, Long competitionId);
    boolean deleteEquipe(Long id);
}
