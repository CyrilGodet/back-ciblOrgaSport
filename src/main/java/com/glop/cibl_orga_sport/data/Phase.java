package com.glop.cibl_orga_sport.data;

import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;

@Entity
public class Phase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPhase;

    @Column(nullable = false)
    private String nomPhase;

    @ManyToOne
    @JsonBackReference("epreuve-phases")
    private Epreuve epreuve;

    @ManyToOne
    @JsonBackReference("lieu-phases")
    @JoinColumn(name = "lieu_id")
    private Lieu lieu;

    @Column(nullable = false)
    private Date dateDebut;

    @Column(nullable = false)
    private Date dateFin;

    public Phase() {
    }

    public Phase(String nomPhase, Epreuve epreuve) {
        this.nomPhase = nomPhase;
        this.epreuve = epreuve;
    }

    public Phase(String nomPhase, Epreuve epreuve, Lieu lieu) {
        this.nomPhase = nomPhase;
        this.epreuve = epreuve;
        this.lieu = lieu;
    }

    public Phase(String nomPhase, java.sql.Date dateDebut, java.sql.Date dateFin, Epreuve epreuve, Lieu lieu) {
        this.nomPhase = nomPhase;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.epreuve = epreuve;
        this.lieu = lieu;
    }

    public Long getIdPhase() {
        return idPhase;
    }

    public void setIdPhase(Long idPhase) {
        this.idPhase = idPhase;
    }

    public String getNomPhase() {
        return nomPhase;
    }

    public void setNomPhase(String nomPhase) {
        this.nomPhase = nomPhase;
    }

    public Epreuve getEpreuve() {
        return epreuve;
    }

    public void setEpreuve(Epreuve epreuve) {
        this.epreuve = epreuve;
    }

    public Lieu getLieu() {
        return lieu;
    }

    public void setLieu(Lieu lieu) {
        this.lieu = lieu;
    }

    public java.sql.Date getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(java.sql.Date dateDebut) {
        this.dateDebut = dateDebut;
    }

    public java.sql.Date getDateFin() {
        return dateFin;
    }

    public void setDateFin(java.sql.Date dateFin) {
        this.dateFin = dateFin;
    }

}
