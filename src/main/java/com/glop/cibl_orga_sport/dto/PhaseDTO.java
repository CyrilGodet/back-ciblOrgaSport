package com.glop.cibl_orga_sport.dto;

import com.glop.cibl_orga_sport.data.enumType.EtapeEpreuveEnum;

public class PhaseDTO {
    private Long idPhase;
    private EtapeEpreuveEnum etapeEpreuve;
    private java.sql.Date dateDebut;
    private java.sql.Date dateFin;
    private EpreuveDTO epreuve;

    public PhaseDTO() {
    }

    public PhaseDTO(Long idPhase, EtapeEpreuveEnum etapeEpreuve, java.sql.Date dateDebut, java.sql.Date dateFin, EpreuveDTO epreuve) {
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

    public java.sql.Date getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(java.sql.Date dateDebut) {
        this.dateDebut = dateDebut;
    }

    public java.sql.Date getDateFin() {
        return dateFin;
    }

    public void setDateFin(java.sql.Date dateFin) {
        this.dateFin = dateFin;
    }
}
