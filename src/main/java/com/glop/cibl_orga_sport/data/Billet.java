package com.glop.cibl_orga_sport.data;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Billet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idBillet;

    @ManyToOne
    private Visiteur visiteur;

    @ManyToOne
    private Epreuve epreuve;

    @Column(nullable = false)
    private String numeroBillet;   

    @Column(nullable = false)
    private String categorie;     

    @Column(nullable = false)
    private LocalDateTime dateAchat;

    public Billet() {
    }

    public Billet(Visiteur visiteur, Epreuve epreuve, String numeroBillet, String categorie, LocalDateTime dateAchat) {
        this.visiteur = visiteur;
        this.epreuve = epreuve;
        this.numeroBillet = numeroBillet;
        this.categorie = categorie;
        this.dateAchat = dateAchat;
    }

    public Long getIdBillet() {
        return idBillet;
    }

    public void setIdBillet(Long idBillet) {
        this.idBillet = idBillet;
    }

    public Visiteur getVisiteur() {
        return visiteur;
    }

    public void setVisiteur(Visiteur visiteur) {
        this.visiteur = visiteur;
    }

    public Epreuve getEpreuve() {
        return epreuve;
    }

    public void setEpreuve(Epreuve epreuve) {
        this.epreuve = epreuve;
    }

    public String getNumeroBillet() {
        return numeroBillet;
    }

    public void setNumeroBillet(String numeroBillet) {
        this.numeroBillet = numeroBillet;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public LocalDateTime getDateAchat() {
        return dateAchat;
    }

    public void setDateAchat(LocalDateTime dateAchat) {
        this.dateAchat = dateAchat;
    }

}
