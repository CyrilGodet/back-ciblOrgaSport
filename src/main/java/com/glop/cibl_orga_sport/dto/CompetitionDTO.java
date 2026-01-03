package com.glop.cibl_orga_sport.dto;

import java.sql.Date;

public class CompetitionDTO {
    private Long idCompetition;
    private String nameCompetition;
    private Date dateDebut;
    private Date dateFin;

    public CompetitionDTO() {}

    public CompetitionDTO(Long idCompetition, String nameCompetition, Date dateDebut, Date dateFin) {
        this.idCompetition = idCompetition;
        this.nameCompetition = nameCompetition;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
    }

    public Long getIdCompetition() {
        return idCompetition;
    }

    public void setIdCompetition(Long idCompetition) {
        this.idCompetition = idCompetition;
    }

    public String getNameCompetition() {
        return nameCompetition;
    }

    public void setNameCompetition(String nameCompetition) {
        this.nameCompetition = nameCompetition;
    }

    public Date getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(Date dateDebut) {
        this.dateDebut = dateDebut;
    }

    public Date getDateFin() {
        return dateFin;
    }

    public void setDateFin(Date dateFin) {
        this.dateFin = dateFin;
    }
}
