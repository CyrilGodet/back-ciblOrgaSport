package com.glop.cibl_orga_sport.dto;

public class PhaseDTO {
    private Long idPhase;
    private String nomPhase;
    private java.sql.Date dateDebut;
    private java.sql.Date dateFin;
    private EpreuveDTO epreuve;
    private LieuDTO lieu;

    public PhaseDTO() {
    }

    public PhaseDTO(Long idPhase, String nomPhase, java.sql.Date dateDebut, java.sql.Date dateFin, EpreuveDTO epreuve,
            LieuDTO lieu) {
        this.idPhase = idPhase;
        this.nomPhase = nomPhase;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.epreuve = epreuve;
        this.lieu = lieu;
    }

    public Long getIdPhase() {
        return idPhase;
    }

    public void setIdPhase(Long idPhase) {
        this.idPhase = idPhase;
    }

    public String getNomPhase() {
        return nomPhase;
    }

    public void setNomPhase(String nomPhase) {
        this.nomPhase = nomPhase;
    }

    public EpreuveDTO getEpreuve() {
        return epreuve;
    }

    public void setEpreuve(EpreuveDTO epreuve) {
        this.epreuve = epreuve;
    }

    public LieuDTO getLieu() {
        return lieu;
    }

    public void setLieu(LieuDTO lieu) {
        this.lieu = lieu;
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
