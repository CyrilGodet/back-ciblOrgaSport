package com.glop.cibl_orga_sport.mapper;

import com.glop.cibl_orga_sport.data.Lieu;
import com.glop.cibl_orga_sport.dto.LieuDTO;

public class LieuMapper {
    
    public static LieuDTO toDTO(Lieu lieu) {
        if (lieu == null) return null;
        return new LieuDTO(
            lieu.getIdLieu(),
            lieu.getNom(),
            lieu.getVille(),
            lieu.getAdresse()
        );
    }

    public static Lieu toEntity(LieuDTO dto) {
        if (dto == null) return null;
        Lieu lieu = new Lieu(
            dto.getNom(),
            dto.getVille(),
            dto.getAdresse()
        );
        return lieu;
    }
}
