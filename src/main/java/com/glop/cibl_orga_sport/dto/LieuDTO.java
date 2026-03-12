package com.glop.cibl_orga_sport.dto;

public class LieuDTO {
    private Long idLieu;
    private String nomLieu;
    private String ville;
    private String adresse;

    public LieuDTO() {}

    public LieuDTO(Long idLieu, String nom, String ville, String adresse) {
        this.idLieu = idLieu;
        this.nomLieu = nom;
        this.ville = ville;
        this.adresse = adresse;
    }

    public Long getIdLieu() {
        return idLieu;
    }

    public void setIdLieu(Long idLieu) {
        this.idLieu = idLieu;
    }

    public String getNomLieu() {
        return nomLieu;
    }

    public void setNomLieu(String nom) {
        this.nomLieu = nom;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }
}
