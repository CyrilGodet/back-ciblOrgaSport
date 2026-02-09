package com.glop.cibl_orga_sport.data;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Resultat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idResultat;

    @ManyToOne
    private Equipe equipe;

    @ManyToOne
    private Epreuve epreuve;

    @Column(nullable = false)
    private double score;

    @Column(nullable = false)
    private int rang;

    public Resultat() {
    }

    public Resultat(Equipe equipe, Epreuve epreuve, double score, int rang) {
        this.equipe = equipe;
        this.epreuve = epreuve;
        this.score = score;
        this.rang = rang;
    }

    public Long getIdResultat() {
        return idResultat;
    }

    public void setIdResultat(Long idResultat) {
        this.idResultat = idResultat;
    }

    public Equipe getEquipe() {
        return equipe;
    }

    public void setEquipe(Equipe equipe) {
        this.equipe = equipe;
    }

    public Epreuve getEpreuve() {
        return epreuve;
    }

    public void setEpreuve(Epreuve epreuve) {
        this.epreuve = epreuve;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public int getRang() {
        return rang;
    }

    public void setRang(int rang) {
        this.rang = rang;
    }

    

}
