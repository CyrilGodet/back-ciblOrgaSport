package com.glop.cibl_orga_sport.dto;

import com.glop.cibl_orga_sport.data.enumType.ResultatDetailsStatusEnum;

public class ResultatDetailsDTO {
    private Long idResultatDetails;
    private ParticipantDTO participant;
    private int rang;
    private ResultatDetailsStatusEnum status;

    public ResultatDetailsDTO() {
    }

    public Long getIdResultatDetails() {
        return idResultatDetails;
    }

    public void setIdResultatDetails(Long idResultatDetails) {
        this.idResultatDetails = idResultatDetails;
    }

    public ParticipantDTO getParticipant() {
        return participant;
    }

    public void setParticipant(ParticipantDTO participant) {
        this.participant = participant;
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
