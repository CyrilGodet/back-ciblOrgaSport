package com.glop.cibl_orga_sport.dto;

import java.sql.Date;

import com.glop.cibl_orga_sport.data.enumType.EtapeEpreuveEnum;

public class PhaseDTO {
    private Long idPhase;
    private EtapeEpreuveEnum etapeEpreuve;
    private Date dateDebut;
    private Date dateFin;
    private EpreuveDTO epreuve;

    public PhaseDTO() {
    }

    public PhaseDTO(Long idPhase, EtapeEpreuveEnum etapeEpreuve, Date dateDebut, Date dateFin, EpreuveDTO epreuve) {
        this.idPhase = idPhase;
        this.etapeEpreuve = etapeEpreuve;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.epreuve = epreuve;
    }

    public Long getIdPhase() {
        return idPhase;
    }

    public void setIdPhase(Long idPhase) {
        this.idPhase = idPhase;
    }

    public EtapeEpreuveEnum getEtapeEpreuve() {
        return etapeEpreuve;
    }

    public void setEtapeEpreuve(EtapeEpreuveEnum etapeEpreuve) {
        this.etapeEpreuve = etapeEpreuve;
    }

    public EpreuveDTO getEpreuve() {
        return epreuve;
    }

    public void setEpreuve(EpreuveDTO epreuve) {
        this.epreuve = epreuve;
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
