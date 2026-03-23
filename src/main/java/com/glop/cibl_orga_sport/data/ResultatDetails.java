package com.glop.cibl_orga_sport.data;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.glop.cibl_orga_sport.data.enumType.ResultatDetailsStatusEnum;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class ResultatDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idResultatDetails;

    @ManyToOne
    private Equipe equipe;

    @ManyToOne
    @JsonBackReference("resultat-details")
    private Resultat resultat;

    @Column(nullable = false)
    private int rang;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ResultatDetailsStatusEnum status;

    public ResultatDetails() {
    }

    public ResultatDetails(Equipe equipe, Resultat resultat, int rang, ResultatDetailsStatusEnum status) {
        this.equipe = equipe;
        this.resultat = resultat;
        this.rang = rang;
        this.status = status;
    }

    public Long getIdResultatDetails() {
        return idResultatDetails;
    }

    public void setIdResultatDetails(Long idResultatDetails) {
        this.idResultatDetails = idResultatDetails;
    }

    public Equipe getEquipe() {
        return equipe;
    }

    public void setEquipe(Equipe equipe) {
        this.equipe = equipe;
    }

    public Resultat getResultat() {
        return resultat;
    }

    public void setResultat(Resultat resultat) {
        this.resultat = resultat;
    }

    public int getRang() {
        return rang;
    }

    public void setRang(int rang) {
        this.rang = rang;
    }

    public ResultatDetailsStatusEnum getStatus() {
        return status;
    }

    public void setStatus(ResultatDetailsStatusEnum status) {
        this.status = status;
    }
}
