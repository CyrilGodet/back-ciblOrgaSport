package com.glop.cibl_orga_sport.data;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Sportif {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idSportif;

    @Column(nullable = false)
    private String nom;

    @Column(nullable = false)
    private String prenom;

    public Sportif() {
    }

    public Sportif(String nom, String prenom) {
        this.nom = nom;
        this.prenom = prenom;
    }

    public Long getIdSportif() {
        return idSportif;
    }

    public void setIdSportif(Long idSportif) {
        this.idSportif = idSportif;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }
}
