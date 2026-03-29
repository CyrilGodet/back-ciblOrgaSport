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
    private Participant participant;

    @ManyToOne
    @JsonBackReference("resultat-details")
    private Resultat resultat;

    @Column(nullable = false)
    private int rang;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ResultatDetailsStatusEnum status;

    @Column(nullable = true)
    private Double valeur;

    public ResultatDetails() {
    }

    public ResultatDetails(Participant participant, Resultat resultat, int rang, ResultatDetailsStatusEnum status, Double valeur) {
        this.participant = participant;
        this.resultat = resultat;
        this.rang = rang;
        this.status = status;
        this.valeur = valeur;
    }

    public Long getIdResultatDetails() {
        return idResultatDetails;
    }

    public void setIdResultatDetails(Long idResultatDetails) {
        this.idResultatDetails = idResultatDetails;
    }

    public Participant getParticipant() {
        return participant;
    }

    public void setParticipant(Participant participant) {
        this.participant = participant;
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

    public Double getValeur() {
        return valeur;
    }

    public void setValeur(Double valeur) {
        this.valeur = valeur;
    }
}
