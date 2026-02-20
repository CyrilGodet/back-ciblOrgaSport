package com.glop.cibl_orga_sport.mapper;

import com.glop.cibl_orga_sport.data.Equipe;
import com.glop.cibl_orga_sport.dto.EquipeDTO;

public class EquipeMapper {
    
    public static EquipeDTO toDTO(Equipe equipe) {
        if (equipe == null) {
            return null;
        }
        return new EquipeDTO(
            equipe.getIdEquipe(),
            equipe.getNomEquipe(),
            equipe.getCompetition() != null ? equipe.getCompetition().getIdCompetition() : null
        );
    }
    
    public static Equipe toEntity(EquipeDTO dto) {
        if (dto == null) {
            return null;
        }
        Equipe equipe = new Equipe(dto.getNomEquipe());
        equipe.setIdEquipe(dto.getIdEquipe());
        return equipe;
    }
}
