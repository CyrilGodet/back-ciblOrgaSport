package com.glop.cibl_orga_sport.dto;

public class ParticipantSportifDTO extends ParticipantDTO {
    private SportifDTO sportif;

    public ParticipantSportifDTO() {
    }

    public SportifDTO getSportif() {
        return sportif;
    }

    public void setSportif(SportifDTO sportif) {
        this.sportif = sportif;
    }
}
