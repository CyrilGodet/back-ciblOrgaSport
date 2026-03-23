package com.glop.cibl_orga_sport.dto;

import com.glop.cibl_orga_sport.data.enumType.ParticipationStatusEnum;

public class ParticipationDTO {
    private Long idParticipation;
    private Long epreuveId;
    private EquipeDTO equipe;
    private ParticipationStatusEnum statut;

    public ParticipationDTO() {
    }

    public ParticipationDTO(Long idParticipation, Long epreuveId, EquipeDTO equipe, ParticipationStatusEnum statut) {
        this.idParticipation = idParticipation;
        this.epreuveId = epreuveId;
        this.equipe = equipe;
        this.statut = statut;
    }

    public Long getIdParticipation() {
        return idParticipation;
    }

    public void setIdParticipation(Long idParticipation) {
        this.idParticipation = idParticipation;
    }

    public Long getEpreuveId() {
        return epreuveId;
    }

    public void setEpreuveId(Long epreuveId) {
        this.epreuveId = epreuveId;
    }

    public EquipeDTO getEquipe() {
        return equipe;
    }

    public void setEquipe(EquipeDTO equipe) {
        this.equipe = equipe;
    }

    public ParticipationStatusEnum getStatut() {
        return statut;
    }

    public void setStatut(ParticipationStatusEnum statut) {
        this.statut = statut;
    }
}
