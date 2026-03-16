package com.glop.cibl_orga_sport.service;

import com.glop.cibl_orga_sport.data.Equipe;

import java.util.List;
import java.util.Optional;

public interface EquipeService {
    List<Equipe> getAllEquipes();

    Optional<Equipe> getEquipe(Long id);

    Equipe createEquipe(String nomEquipe);

    Equipe updateEquipe(Long id, String nomEquipe);

    boolean deleteEquipe(Long id);
}
