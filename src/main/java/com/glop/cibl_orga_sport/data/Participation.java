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
    @JsonBackReference("competition-participations")
    private Competition competition;

    @ManyToOne
    private Participant participant;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private ParticipationStatusEnum statut;

    public Participation() {
    }

    public Participation(Competition competition, Participant participant, ParticipationStatusEnum statut) {
        this.competition = competition;
        this.participant = participant;
        this.statut = statut;
    }

    public Long getIdParticipation() {
        return idParticipation;
    }

    public void setIdParticipation(Long idParticipation) {
        this.idParticipation = idParticipation;
    }

    public Competition getCompetition() {
        return competition;
    }

    public void setCompetition(Competition competition) {
        this.competition = competition;
    }

    public Participant getParticipant() {
        return participant;
    }

    public void setParticipant(Participant participant) {
        this.participant = participant;
    }

    public ParticipationStatusEnum getStatut() {
        return statut;
    }

    public void setStatut(ParticipationStatusEnum statut) {
        this.statut = statut;
    }
}
