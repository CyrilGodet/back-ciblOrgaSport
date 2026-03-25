package com.glop.cibl_orga_sport.dto;

import com.glop.cibl_orga_sport.data.enumType.IncidentStatutEnum;
import com.glop.cibl_orga_sport.data.enumType.NiveauImpactEnum;

import java.time.LocalDateTime;

public class IncidentDTO {
    private Long idIncident;
    private Long idCompetition;
    private String titre;
    private String description;
    private NiveauImpactEnum niveauImpact;
    private LocalDateTime dateIncident;
    private Long idLieu;
    private String nomLieu;
    private boolean resolu;
    private IncidentStatutEnum statut;
    private String statutFront;
    private String gravite;
    private Long idCreateur;
    private String nomCreateur;
    private String prenomCreateur;
    
    public IncidentDTO() {}
        
    public Long getIdIncident() {
        return idIncident;
    }
    
    public void setIdIncident(Long idIncident) {
        this.idIncident = idIncident;
    }
    
    public Long getIdCompetition() {
        return idCompetition;
    }
    
    public void setIdCompetition(Long idCompetition) {
        this.idCompetition = idCompetition;
    }
    
    public String getTitre() {
        return titre;
    }
    
    public void setTitre(String titre) {
        this.titre = titre;
    }
    
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    public NiveauImpactEnum getNiveauImpact() {
        return niveauImpact;
    }
    
    public void setNiveauImpact(NiveauImpactEnum niveauImpact) {
        this.niveauImpact = niveauImpact;
    }
    
    public LocalDateTime getDateIncident() {
        return dateIncident;
    }
    
    public void setDateIncident(LocalDateTime dateIncident) {
        this.dateIncident = dateIncident;
    }
    
    public Long getIdLieu() {
        return idLieu;
    }
    
    public void setIdLieu(Long idLieu) {
        this.idLieu = idLieu;
    }
    
    public String getNomLieu() {
        return nomLieu;
    }
    
    public void setNomLieu(String nomLieu) {
        this.nomLieu = nomLieu;
    }
    
    public boolean isResolu() {
        return resolu;
    }
    
    public void setResolu(boolean resolu) {
        this.resolu = resolu;
    }

    public IncidentStatutEnum getStatut() {
        return statut;
    }

    public void setStatut(IncidentStatutEnum statut) {
        this.statut = statut;
    }

    public String getStatutFront() {
        return statutFront;
    }

    public void setStatutFront(String statutFront) {
        this.statutFront = statutFront;
    }

    public String getGravite() {
        return gravite;
    }

    public void setGravite(String gravite) {
        this.gravite = gravite;
    }

    public Long getIdCreateur() {
        return idCreateur;
    }

    public void setIdCreateur(Long idCreateur) {
        this.idCreateur = idCreateur;
    }

    public String getNomCreateur() {
        return nomCreateur;
    }

    public void setNomCreateur(String nomCreateur) {
        this.nomCreateur = nomCreateur;
    }

    public String getPrenomCreateur() {
        return prenomCreateur;
    }

    public void setPrenomCreateur(String prenomCreateur) {
        this.prenomCreateur = prenomCreateur;
    }
}
