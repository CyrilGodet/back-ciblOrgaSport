package com.glop.cibl_orga_sport.dto;

import java.sql.Date;

public class PeriodeDTO {
    private Date dateDebut;
    private Date dateFin;

    public PeriodeDTO() {
    }

    public PeriodeDTO(Date dateDebut, Date dateFin) {
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
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
