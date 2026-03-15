package com.glop.cibl_orga_sport.data;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;

import jakarta.persistence.JoinColumn;

@Entity
public class ParticipantSportif extends Participant {

    @ManyToOne
    @JoinColumn(name = "sportif_id")
    private Sportif sportif;

    public ParticipantSportif() {
    }

    public ParticipantSportif(Sportif sportif) {
        this.sportif = sportif;
    }

    public Sportif getSportif() {
        return sportif;
    }

    public void setSportif(Sportif sportif) {
        this.sportif = sportif;
    }
}
