package com.glop.cibl_orga_sport.mapper;

import com.glop.cibl_orga_sport.data.Lieu;
import com.glop.cibl_orga_sport.dto.LieuDTO;

public class LieuMapper {
    
    public static LieuDTO toDTO(Lieu lieu) {
        if (lieu == null) return null;
        return new LieuDTO(
            lieu.getIdLieu(),
            lieu.getNomLieu(),
            lieu.getVille(),
            lieu.getAdresse(),
            lieu.getCategorie()
        );
    }

    public static Lieu toEntity(LieuDTO dto) {
        if (dto == null) return null;
        Lieu lieu = new Lieu(
            dto.getNomLieu(),
            dto.getVille(),
            dto.getAdresse(),
            dto.getCategorie()
        );
        return lieu;
    }
}
