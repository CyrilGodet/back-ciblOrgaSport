package com.glop.cibl_orga_sport.dto;

import com.glop.cibl_orga_sport.data.enumType.ResultatDetailsStatusEnum;

public class ResultatDetailsDTO {
    private Long idResultatDetails;
    private EquipeDTO equipe;
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

    public EquipeDTO getEquipe() {
        return equipe;
    }

    public void setEquipe(EquipeDTO equipe) {
        this.equipe = equipe;
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
