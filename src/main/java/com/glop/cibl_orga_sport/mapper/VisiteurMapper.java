package com.glop.cibl_orga_sport.mapper;

import com.glop.cibl_orga_sport.data.Visiteur;
import com.glop.cibl_orga_sport.dto.VisiteurDTO;

public class VisiteurMapper {
    public static VisiteurDTO toDTO(Visiteur visiteur) {
        if (visiteur == null) return null;
        VisiteurDTO dto = new VisiteurDTO();
        UtilisateurMapper.mapToDTO(visiteur, dto);
        return dto;
    }

    public static Visiteur toEntity(VisiteurDTO dto) {
        if (dto == null) return null;
        Visiteur visiteur = new Visiteur();
        UtilisateurMapper.mapToEntity(dto, visiteur);
        return visiteur;
    }
}
