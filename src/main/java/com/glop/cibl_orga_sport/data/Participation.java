package com.glop.cibl_orga_sport.data;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.glop.cibl_orga_sport.data.enumType.ParticipationStatusEnum;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Participation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idParticipation;

    @ManyToOne
    @JsonBackReference("epreuve-participations")
    private Epreuve epreuve;

    @ManyToOne
    @JsonBackReference("equipe-participations")
    private Equipe equipe;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private ParticipationStatusEnum statut;

    public Participation() {
    }

    public Participation(Epreuve epreuve, Equipe equipe, ParticipationStatusEnum statut) {
        this.epreuve = epreuve;
        this.equipe = equipe;
        this.statut = statut;
    }

    public Long getIdParticipation() {
        return idParticipation;
    }

    public void setIdParticipation(Long idParticipation) {
        this.idParticipation = idParticipation;
    }

    public Epreuve getEpreuve() {
        return epreuve;
    }

    public void setEpreuve(Epreuve epreuve) {
        this.epreuve = epreuve;
    }

    public Equipe getEquipe() {
        return equipe;
    }

    public void setEquipe(Equipe equipe) {
        this.equipe = equipe;
    }

    public ParticipationStatusEnum getStatut() {
        return statut;
    }

    public void setStatut(ParticipationStatusEnum statut) {
        this.statut = statut;
    }
}
