package com.glop.cibl_orga_sport.mapper;

import com.glop.cibl_orga_sport.data.Compte;
import com.glop.cibl_orga_sport.dto.CompteDTO;

public class CompteMapper {

    public static CompteDTO toDTO(Compte entity) {
        if (entity == null) return null;
        CompteDTO dto = new CompteDTO();
        dto.setIdCompte(entity.getIdCompte());
        if (entity.getUtilisateur() != null) {
            dto.setIdUtilisateur(entity.getUtilisateur().getIdUtilisateur());
        }
        dto.setUsername(entity.getUsername());
        // On ne renvoie pas le password dans le DTO pour des raisons de sécurité (sauf si nécessaire pour la création)
        dto.setType(entity.getType());
        dto.setActive(entity.isActive());
        dto.setDateCreation(entity.getDateCreation());
        return dto;
    }

    public static Compte toEntity(CompteDTO dto) {
        if (dto == null) return null;
        Compte entity = new Compte();
        entity.setIdCompte(dto.getIdCompte());
        entity.setUsername(dto.getUsername());
        entity.setPassword(dto.getPassword());
        entity.setType(dto.getType());
        entity.setActive(dto.isActive());
        entity.setDateCreation(dto.getDateCreation());
        return entity;
    }
}
