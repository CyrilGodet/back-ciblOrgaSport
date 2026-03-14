package com.glop.cibl_orga_sport.dto;

import com.glop.cibl_orga_sport.data.enumType.ResultatStatusEnum;
import java.util.List;
import java.util.ArrayList;

public class ResultatDTO {
    private Long idResultat;
    private ResultatStatusEnum status = ResultatStatusEnum.DRAFT;
    private List<ResultatDetailsDTO> details = new ArrayList<>();

    public ResultatDTO() {
    }

    public Long getIdResultat() {
        return idResultat;
    }

    public void setIdResultat(Long idResultat) {
        this.idResultat = idResultat;
    }

    public ResultatStatusEnum getStatus() {
        return status;
    }

    public void setStatus(ResultatStatusEnum status) {
        this.status = status;
    }

    public List<ResultatDetailsDTO> getDetails() {
        return details;
    }

    public void setDetails(List<ResultatDetailsDTO> details) {
        this.details = details;
    }
}
