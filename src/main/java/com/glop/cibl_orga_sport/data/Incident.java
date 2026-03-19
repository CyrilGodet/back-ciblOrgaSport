package com.glop.cibl_orga_sport.data;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import com.glop.cibl_orga_sport.data.enumType.NiveauImpactEnum;

import java.time.LocalDateTime;

@Entity
public class Incident {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idIncident;
    
    @ManyToOne
    private Competition competition;
    
    @Column(nullable = false)
    private String titre;
    
    @Column(nullable = false)
    private String description;
    
    @Column(nullable = false)
    private NiveauImpactEnum niveauImpact;
    
    @Column(nullable = false)
    private LocalDateTime dateIncident;
    
    @ManyToOne
    private Lieu lieu;
    
    @Column(nullable = false)
    private boolean estResolu;
    
    public Incident() {
        this.dateIncident = LocalDateTime.now();
        this.estResolu = false;
    }
    
    public Incident(String titre, String description, NiveauImpactEnum niveauImpact) {
        this.titre = titre;
        this.description = description;
        this.niveauImpact = niveauImpact;
        this.dateIncident = LocalDateTime.now();
        this.estResolu = false;
    }
        
    public Long getIdIncident() {
        return idIncident;
    }
    
    public void setIdIncident(Long idIncident) {
        this.idIncident = idIncident;
    }
    
    public Competition getCompetition() {
        return competition;
    }
    
    public void setCompetition(Competition competition) {
        this.competition = competition;
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
    
    public Lieu getLieu() {
        return lieu;
    }
    
    public void setLieu(Lieu lieu) {
        this.lieu = lieu;
    }
    
    public boolean isEstResolu() {
        return estResolu;
    }
    
    public void setEstResolu(boolean resolu) {
        this.estResolu = resolu;
    }
}
