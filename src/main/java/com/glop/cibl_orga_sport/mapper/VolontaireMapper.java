package com.glop.cibl_orga_sport.mapper;

import com.glop.cibl_orga_sport.data.Volontaire;
import com.glop.cibl_orga_sport.dto.VolontaireDTO;

public class VolontaireMapper {
    
    public static VolontaireDTO toDTO(Volontaire volontaire) {
        if (volontaire == null) return null;
        
        VolontaireDTO dto = new VolontaireDTO();
        dto.setIdVolontaire(volontaire.getIdVolontaire());
        dto.setNom(volontaire.getNom());
        dto.setPrenom(volontaire.getPrenom());
        dto.setEmail(volontaire.getEmail());
        dto.setTelephone(volontaire.getTelephone());
        dto.setCompetences(volontaire.getCompetences());
        
        return dto;
    }
    
    public static Volontaire toEntity(VolontaireDTO dto) {
        if (dto == null) return null;
        
        Volontaire volontaire = new Volontaire();
        volontaire.setIdVolontaire(dto.getIdVolontaire());
        volontaire.setNom(dto.getNom());
        volontaire.setPrenom(dto.getPrenom());
        volontaire.setEmail(dto.getEmail());
        volontaire.setTelephone(dto.getTelephone());
        volontaire.setCompetences(dto.getCompetences());
        
        return volontaire;
    }
}
