package com.glop.cibl_orga_sport.dto;

import com.glop.cibl_orga_sport.data.enumType.PosteVolontaireEnum;

import java.time.LocalDate;
import java.time.LocalTime;

public class AffectationVolontaireDTO {
    private Long idAffectation;
    private Long idVolontaire;
    private String nomVolontaire;
    private String prenomVolontaire;
    private Long idEpreuve;
    private String nomEpreuve;
    private LocalDate dateAffectation;
    private LocalTime heureDebut;
    private LocalTime heureFin;
    private Long idLieuRdv;
    private String nomLieuRdv;
    private PosteVolontaireEnum poste;
    private String instructions;
    
    public AffectationVolontaireDTO() {}
        
    public Long getIdAffectation() {
        return idAffectation;
    }
    
    public void setIdAffectation(Long idAffectation) {
        this.idAffectation = idAffectation;
    }
    
    public Long getIdVolontaire() {
        return idVolontaire;
    }
    
    public void setIdVolontaire(Long idVolontaire) {
        this.idVolontaire = idVolontaire;
    }
    
    public String getNomVolontaire() {
        return nomVolontaire;
    }
    
    public void setNomVolontaire(String nomVolontaire) {
        this.nomVolontaire = nomVolontaire;
    }
    
    public String getPrenomVolontaire() {
        return prenomVolontaire;
    }
    
    public void setPrenomVolontaire(String prenomVolontaire) {
        this.prenomVolontaire = prenomVolontaire;
    }
    
    public Long getIdEpreuve() {
        return idEpreuve;
    }
    
    public void setIdEpreuve(Long idEpreuve) {
        this.idEpreuve = idEpreuve;
    }
    
    public String getNomEpreuve() {
        return nomEpreuve;
    }
    
    public void setNomEpreuve(String nomEpreuve) {
        this.nomEpreuve = nomEpreuve;
    }
    
    public LocalDate getDateAffectation() {
        return dateAffectation;
    }
    
    public void setDateAffectation(LocalDate dateAffectation) {
        this.dateAffectation = dateAffectation;
    }
    
    public LocalTime getHeureDebut() {
        return heureDebut;
    }
    
    public void setHeureDebut(LocalTime heureDebut) {
        this.heureDebut = heureDebut;
    }
    
    public LocalTime getHeureFin() {
        return heureFin;
    }
    
    public void setHeureFin(LocalTime heureFin) {
        this.heureFin = heureFin;
    }
    
    public Long getIdLieuRdv() {
        return idLieuRdv;
    }
    
    public void setIdLieuRdv(Long idLieuRdv) {
        this.idLieuRdv = idLieuRdv;
    }
    
    public String getNomLieuRdv() {
        return nomLieuRdv;
    }
    
    public void setNomLieuRdv(String nomLieuRdv) {
        this.nomLieuRdv = nomLieuRdv;
    }
    
    public PosteVolontaireEnum getPoste() {
        return poste;
    }
    
    public void setPoste(PosteVolontaireEnum poste) {
        this.poste = poste;
    }
    
    public String getInstructions() {
        return instructions;
    }
    
    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }
}
