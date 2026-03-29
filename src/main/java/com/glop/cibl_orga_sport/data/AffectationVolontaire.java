package com.glop.cibl_orga_sport.data;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.glop.cibl_orga_sport.data.enumType.PosteVolontaireEnum;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
public class AffectationVolontaire {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idAffectation;
    
    @ManyToOne
    @JsonBackReference("volontaire-affectations")
    private Volontaire volontaire;
    
    @ManyToOne
    private Epreuve epreuve;
    
    @Column(nullable = false)
    private LocalDate dateAffectation;
    
    @Column(nullable = false)
    private LocalTime heureDebut;
    
    @Column(nullable = false)
    private LocalTime heureFin;
    
    @ManyToOne
    private Lieu lieuRdv;
    
    @Column(nullable = false)
    private PosteVolontaireEnum poste;
    
    @Column(nullable = true)
    private String commentaire;
    
    public AffectationVolontaire() {}
    
    public AffectationVolontaire(Volontaire volontaire, Epreuve epreuve, LocalDate dateAffectation) {
        this.volontaire = volontaire;
        this.epreuve = epreuve;
        this.dateAffectation = dateAffectation;
    }
        
    public Long getIdAffectation() {
        return idAffectation;
    }
    
    public void setIdAffectation(Long idAffectation) {
        this.idAffectation = idAffectation;
    }
    
    public Volontaire getVolontaire() {
        return volontaire;
    }
    
    public void setVolontaire(Volontaire volontaire) {
        this.volontaire = volontaire;
    }
    
    public Epreuve getEpreuve() {
        return epreuve;
    }
    
    public void setEpreuve(Epreuve epreuve) {
        this.epreuve = epreuve;
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
    
    public Lieu getLieuRdv() {
        return lieuRdv;
    }
    
    public void setLieuRdv(Lieu lieuRdv) {
        this.lieuRdv = lieuRdv;
    }
    
    public PosteVolontaireEnum getPoste() {
        return poste;
    }
    
    public void setPoste(PosteVolontaireEnum poste) {
        this.poste = poste;
    }
    
    public String getCommentaire() {
        return commentaire;
    }
    
    public void setCommentaire(String instructions) {
        this.commentaire = instructions;
    }
}
