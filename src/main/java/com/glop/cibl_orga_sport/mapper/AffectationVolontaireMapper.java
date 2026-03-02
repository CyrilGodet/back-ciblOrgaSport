package com.glop.cibl_orga_sport.mapper;

import com.glop.cibl_orga_sport.data.AffectationVolontaire;
import com.glop.cibl_orga_sport.dto.AffectationVolontaireDTO;

public class AffectationVolontaireMapper {
    
    public static AffectationVolontaireDTO toDTO(AffectationVolontaire affectation) {
        if (affectation == null) return null;
        
        AffectationVolontaireDTO dto = new AffectationVolontaireDTO();
        dto.setIdAffectation(affectation.getIdAffectation());
        dto.setDateAffectation(affectation.getDateAffectation());
        dto.setHeureDebut(affectation.getHeureDebut());
        dto.setHeureFin(affectation.getHeureFin());
        dto.setPoste(affectation.getPoste());
        dto.setInstructions(affectation.getCommentaire());
        
        if (affectation.getVolontaire() != null) {
            dto.setIdVolontaire(affectation.getVolontaire().getIdVolontaire());
            dto.setNomVolontaire(affectation.getVolontaire().getNom());
            dto.setPrenomVolontaire(affectation.getVolontaire().getPrenom());
        }
        
        if (affectation.getEpreuve() != null) {
            dto.setIdEpreuve(affectation.getEpreuve().getIdEpreuve());
            dto.setNomEpreuve(affectation.getEpreuve().getNomEpreuve());
        }
        
        if (affectation.getLieuRdv() != null) {
            dto.setIdLieuRdv(affectation.getLieuRdv().getIdLieu());
            dto.setNomLieuRdv(affectation.getLieuRdv().getNomLieu());
        }
        
        return dto;
    }
    
    public static AffectationVolontaire toEntity(AffectationVolontaireDTO dto) {
        if (dto == null) return null;
        
        AffectationVolontaire affectation = new AffectationVolontaire();
        affectation.setIdAffectation(dto.getIdAffectation());
        affectation.setDateAffectation(dto.getDateAffectation());
        affectation.setHeureDebut(dto.getHeureDebut());
        affectation.setHeureFin(dto.getHeureFin());
        affectation.setPoste(dto.getPoste());
        affectation.setCommentaire(dto.getInstructions());
        
        return affectation;
    }
}
