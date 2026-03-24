package com.glop.cibl_orga_sport.dto;

import java.time.LocalDateTime;

public class BilletDTO {
    private Long idBillet;
    private Long visiteurId;
    private Long epreuveId;
    private String numeroBillet;
    private String categorie;
    private LocalDateTime dateAchat;

    public BilletDTO() {
    }

    public BilletDTO(Long idBillet, Long visiteurId, Long epreuveId, String numeroBillet, String categorie,
            LocalDateTime dateAchat) {
        this.idBillet = idBillet;
        this.visiteurId = visiteurId;
        this.epreuveId = epreuveId;
        this.numeroBillet = numeroBillet;
        this.categorie = categorie;
        this.dateAchat = dateAchat;
    }

    public Long getIdBillet() {
        return idBillet;
    }

    public void setIdBillet(Long idBillet) {
        this.idBillet = idBillet;
    }

    public Long getVisiteurId() {
        return visiteurId;
    }

    public void setVisiteurId(Long visiteurId) {
        this.visiteurId = visiteurId;
    }

    public Long getEpreuveId() {
        return epreuveId;
    }

    public void setEpreuveId(Long epreuveId) {
        this.epreuveId = epreuveId;
    }

    public String getNumeroBillet() {
        return numeroBillet;
    }

    public void setNumeroBillet(String numeroBillet) {
        this.numeroBillet = numeroBillet;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public LocalDateTime getDateAchat() {
        return dateAchat;
    }

    public void setDateAchat(LocalDateTime dateAchat) {
        this.dateAchat = dateAchat;
    }
}
