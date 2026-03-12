package com.glop.cibl_orga_sport.dto;

import com.glop.cibl_orga_sport.data.enumType.MatchStatusEnum;
import java.util.List;
import java.util.ArrayList;

public class MatchDTO {
    private Long idMatch;
    private Long etapeEpreuveId;
    private List<EquipeDTO> equipes = new ArrayList<>();
    private PeriodeDTO periode;
    private ResultatDTO resultat;
    private MatchStatusEnum status;

    public MatchDTO() {
    }

    public Long getIdMatch() {
        return idMatch;
    }

    public void setIdMatch(Long idMatch) {
        this.idMatch = idMatch;
    }

    public Long getEtapeEpreuveId() {
        return etapeEpreuveId;
    }

    public void setEtapeEpreuveId(Long etapeEpreuveId) {
        this.etapeEpreuveId = etapeEpreuveId;
    }

    public List<EquipeDTO> getEquipes() {
        return equipes;
    }

    public void setEquipes(List<EquipeDTO> equipes) {
        this.equipes = equipes;
    }

    public PeriodeDTO getPeriode() {
        return periode;
    }

    public void setPeriode(PeriodeDTO periode) {
        this.periode = periode;
    }

    public ResultatDTO getResultat() {
        return resultat;
    }

    public void setResultat(ResultatDTO resultat) {
        this.resultat = resultat;
    }

    public MatchStatusEnum getStatus() {
        return status;
    }

    public void setStatus(MatchStatusEnum status) {
        this.status = status;
    }
}
