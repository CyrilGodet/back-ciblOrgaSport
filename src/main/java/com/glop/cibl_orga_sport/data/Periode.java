package com.glop.cibl_orga_sport.data;

import java.sql.Date;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Column;

@Embeddable
public class Periode {

    @Column(nullable = false)
    private Date dateDebut;
    
    @Column(nullable = false)
    private Date dateFin;


    public Periode() {
    }

    public Periode(Date dateDebut, Date dateFin) {
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
