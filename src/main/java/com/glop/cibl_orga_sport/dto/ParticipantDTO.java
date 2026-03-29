package com.glop.cibl_orga_sport.dto;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = ParticipantSportifDTO.class, name = "SPORTIF"),
        @JsonSubTypes.Type(value = ParticipantEquipeDTO.class, name = "EQUIPE")
})
public abstract class ParticipantDTO {
    private Long idParticipant;

    public ParticipantDTO() {
    }

    public Long getIdParticipant() {
        return idParticipant;
    }

    public void setIdParticipant(Long idParticipant) {
        this.idParticipant = idParticipant;
    }
}
