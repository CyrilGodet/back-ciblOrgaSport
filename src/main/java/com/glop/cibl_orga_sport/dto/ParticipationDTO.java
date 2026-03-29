package com.glop.cibl_orga_sport.dto;

import com.glop.cibl_orga_sport.data.enumType.ParticipationStatusEnum;

public class ParticipationDTO {
    private Long idParticipation;
    private Long competitionId;
    private ParticipantDTO participant;
    private ParticipationStatusEnum statut;

    public ParticipationDTO() {
    }

    public ParticipationDTO(Long idParticipation, Long competitionId, ParticipantDTO participant, ParticipationStatusEnum statut) {
        this.idParticipation = idParticipation;
        this.competitionId = competitionId;
        this.participant = participant;
        this.statut = statut;
    }

    public Long getIdParticipation() {
        return idParticipation;
    }

    public void setIdParticipation(Long idParticipation) {
        this.idParticipation = idParticipation;
    }

    public Long getCompetitionId() {
        return competitionId;
    }

    public void setCompetitionId(Long competitionId) {
        this.competitionId = competitionId;
    }

    public ParticipantDTO getParticipant() {
        return participant;
    }

    public void setParticipant(ParticipantDTO participant) {
        this.participant = participant;
    }

    public ParticipationStatusEnum getStatut() {
        return statut;
    }

    public void setStatut(ParticipationStatusEnum statut) {
        this.statut = statut;
    }
}
