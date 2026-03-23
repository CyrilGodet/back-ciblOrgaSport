package com.glop.cibl_orga_sport.dto;

import com.glop.cibl_orga_sport.data.enumType.EtapeEpreuveEnum;
import java.util.List;
import java.util.ArrayList;

public class EtapeEpreuveDTO {
    private Long idEtapeEpreuve;
    private Long epreuveId;
    private PeriodeDTO periode;
    private ResultatDTO resultat;
    private List<MatchDTO> matches = new ArrayList<>();
    private List<EquipeDTO> equipes = new ArrayList<>();
    private EtapeEpreuveEnum etapeEpreuveEnum;

    public EtapeEpreuveDTO() {
    }

    public Long getIdEtapeEpreuve() {
        return idEtapeEpreuve;
    }

    public void setIdEtapeEpreuve(Long idEtapeEpreuve) {
        this.idEtapeEpreuve = idEtapeEpreuve;
    }

    public Long getEpreuveId() {
        return epreuveId;
    }

    public void setEpreuveId(Long epreuveId) {
        this.epreuveId = epreuveId;
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

    public List<MatchDTO> getMatches() {
        return matches;
    }

    public void setMatches(List<MatchDTO> matches) {
        this.matches = matches;
    }

    public List<EquipeDTO> getEquipes() {
        return equipes;
    }

    public void setEquipes(List<EquipeDTO> equipes) {
        this.equipes = equipes;
    }

    public EtapeEpreuveEnum getEtapeEpreuveEnum() {
        return etapeEpreuveEnum;
    }

    public void setEtapeEpreuveEnum(EtapeEpreuveEnum etapeEpreuveEnum) {
        this.etapeEpreuveEnum = etapeEpreuveEnum;
    }
}
