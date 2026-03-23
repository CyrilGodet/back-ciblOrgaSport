package com.glop.cibl_orga_sport.mapper;

import com.glop.cibl_orga_sport.data.Utilisateur;
import com.glop.cibl_orga_sport.dto.UtilisateurDTO;
import com.glop.cibl_orga_sport.dto.LieuDTO;
import com.glop.cibl_orga_sport.mapper.LieuMapper;

public class UtilisateurMapper {

    public static void mapToDTO(Utilisateur entity, UtilisateurDTO dto) {
        if (entity == null || dto == null) return;
        dto.setIdUtilisateur(entity.getIdUtilisateur());
        dto.setNom(entity.getNom());
        dto.setPrenom(entity.getPrenom());
        dto.setEmail(entity.getEmail());
        dto.setAge(entity.getAge());
        if (entity.getLieu() != null) {
            dto.setLieu(LieuMapper.toDTO(entity.getLieu()));
        }
    }

    public static void mapToEntity(UtilisateurDTO dto, Utilisateur entity) {
        if (dto == null || entity == null) return;
        entity.setIdUtilisateur(dto.getIdUtilisateur());
        entity.setNom(dto.getNom());
        entity.setPrenom(dto.getPrenom());
        entity.setEmail(dto.getEmail());
        entity.setAge(dto.getAge());
        // Lieu mapping usually handled in service via ID or lookup
    }
}
