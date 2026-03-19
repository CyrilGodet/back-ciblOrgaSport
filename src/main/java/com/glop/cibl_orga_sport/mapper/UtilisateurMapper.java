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

    public static UtilisateurDTO toSpecificDTO(Utilisateur user) {
        if (user == null) return null;
        UtilisateurDTO dto;
        if (user instanceof com.glop.cibl_orga_sport.data.Sportif) {
            dto = SportifMapper.toDTO((com.glop.cibl_orga_sport.data.Sportif) user);
            dto.setType("SPORTIF");
        } else if (user instanceof com.glop.cibl_orga_sport.data.Visiteur) {
            dto = VisiteurMapper.toDTO((com.glop.cibl_orga_sport.data.Visiteur) user);
            dto.setType("VISITEUR");
        } else if (user instanceof com.glop.cibl_orga_sport.data.Volontaire) {
            dto = VolontaireMapper.toDTO((com.glop.cibl_orga_sport.data.Volontaire) user);
            dto.setType("VOLONTAIRE");
        } else if (user instanceof com.glop.cibl_orga_sport.data.Commissaire) {
            dto = CommissaireMapper.toDTO((com.glop.cibl_orga_sport.data.Commissaire) user);
            dto.setType("COMMISSAIRE");
        } else if (user instanceof com.glop.cibl_orga_sport.data.Administrateur) {
            dto = new com.glop.cibl_orga_sport.dto.AdministrateurDTO();
            mapToDTO(user, dto);
            dto.setType("ADMIN");
        } else {
            dto = new UtilisateurDTO();
            mapToDTO(user, dto);
        }
        return dto;
    }
}
