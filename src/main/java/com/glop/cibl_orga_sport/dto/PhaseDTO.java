package com.glop.cibl_orga_sport.dto;

public class PhaseDTO {
    private Long idPhase;
    private String nomPhase;
    private EpreuveDTO epreuve;
    private LieuDTO lieu;

    public PhaseDTO() {}

    public PhaseDTO(Long idPhase, String nomPhase, EpreuveDTO epreuve, LieuDTO lieu) {
        this.idPhase = idPhase;
        this.nomPhase = nomPhase;
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
}
