package com.glop.cibl_orga_sport.dto;

public class LieuDTO {
    private Long idLieu;
    private String nom;
    private String ville;
    private String adresse;

    public LieuDTO() {}

    public LieuDTO(Long idLieu, String nom, String ville, String adresse) {
        this.idLieu = idLieu;
        this.nom = nom;
        this.ville = ville;
        this.adresse = adresse;
    }

    public Long getIdLieu() {
        return idLieu;
    }

    public void setIdLieu(Long idLieu) {
        this.idLieu = idLieu;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
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
