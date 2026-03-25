package com.glop.cibl_orga_sport.dto;

import java.util.List;

public class ConformiteCharteEuropeenneDTO {

    private Long idCompetition;
    private boolean tousConformes;
    private long nombreSportifsInscrits;
    private long nombreSportifsConformes;
    private long nombreSportifsNonConformes;
    private List<SportifNonConformeDTO> sportifsNonConformes;

    public ConformiteCharteEuropeenneDTO() {
    }

    public ConformiteCharteEuropeenneDTO(Long idCompetition, boolean tousConformes, long nombreSportifsInscrits,
            long nombreSportifsConformes, long nombreSportifsNonConformes,
            List<SportifNonConformeDTO> sportifsNonConformes) {
        this.idCompetition = idCompetition;
        this.tousConformes = tousConformes;
        this.nombreSportifsInscrits = nombreSportifsInscrits;
        this.nombreSportifsConformes = nombreSportifsConformes;
        this.nombreSportifsNonConformes = nombreSportifsNonConformes;
        this.sportifsNonConformes = sportifsNonConformes;
    }

    public Long getIdCompetition() {
        return idCompetition;
    }

    public void setIdCompetition(Long idCompetition) {
        this.idCompetition = idCompetition;
    }

    public boolean isTousConformes() {
        return tousConformes;
    }

    public void setTousConformes(boolean tousConformes) {
        this.tousConformes = tousConformes;
    }

    public long getNombreSportifsInscrits() {
        return nombreSportifsInscrits;
    }

    public void setNombreSportifsInscrits(long nombreSportifsInscrits) {
        this.nombreSportifsInscrits = nombreSportifsInscrits;
    }

    public long getNombreSportifsConformes() {
        return nombreSportifsConformes;
    }

    public void setNombreSportifsConformes(long nombreSportifsConformes) {
        this.nombreSportifsConformes = nombreSportifsConformes;
    }

    public long getNombreSportifsNonConformes() {
        return nombreSportifsNonConformes;
    }

    public void setNombreSportifsNonConformes(long nombreSportifsNonConformes) {
        this.nombreSportifsNonConformes = nombreSportifsNonConformes;
    }

    public List<SportifNonConformeDTO> getSportifsNonConformes() {
        return sportifsNonConformes;
    }

    public void setSportifsNonConformes(List<SportifNonConformeDTO> sportifsNonConformes) {
        this.sportifsNonConformes = sportifsNonConformes;
    }
}
