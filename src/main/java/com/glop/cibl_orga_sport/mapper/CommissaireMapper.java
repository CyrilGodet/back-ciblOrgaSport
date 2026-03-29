package com.glop.cibl_orga_sport.mapper;

import com.glop.cibl_orga_sport.data.Commissaire;
import com.glop.cibl_orga_sport.dto.CommissaireDTO;

public class CommissaireMapper {
    public static CommissaireDTO toDTO(Commissaire commissaire) {
        if (commissaire == null) return null;
        CommissaireDTO dto = new CommissaireDTO();
        UtilisateurMapper.mapToDTO(commissaire, dto);
        return dto;
    }
}
