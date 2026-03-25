package com.glop.cibl_orga_sport.service;

import com.glop.cibl_orga_sport.data.Competition;
import com.glop.cibl_orga_sport.data.Epreuve;
import com.glop.cibl_orga_sport.data.Equipe;
import com.glop.cibl_orga_sport.data.enumType.CompetitionGenreEnum;
import com.glop.cibl_orga_sport.data.enumType.CompetitionStatusEnum;
import com.glop.cibl_orga_sport.data.enumType.DisciplineEnum;
import com.glop.cibl_orga_sport.dto.EquipeDTO;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface EquipeService {
    List<Equipe> getAllEquipes();
    Optional<Equipe> getEquipe(Long id);
    Equipe createEquipe(String nomEquipe);
    Equipe createEquipe(EquipeDTO dto);
    List<Equipe> searchEquipes(String query);
    Equipe updateEquipe(Long id, String nomEquipe);
    boolean deleteEquipe(Long id);

}
