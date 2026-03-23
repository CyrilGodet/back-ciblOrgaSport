package com.glop.cibl_orga_sport.dto;

import com.glop.cibl_orga_sport.data.enumType.LieuCategorieEnum;

public class LieuDTO {
    private Long idLieu;
    private String nomLieu;
    private String ville;
    private String adresse;
    private LieuCategorieEnum categorie;

    public LieuDTO() {}


    public LieuDTO(Long idLieu, String nom, String ville, String adresse, LieuCategorieEnum categorie) {
        this.idLieu = idLieu;
        this.nomLieu = nom;
        this.ville = ville;
        this.adresse = adresse;
        this.categorie = categorie;
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

    public LieuCategorieEnum getCategorie() {
        return categorie;
    }

    public void setCategorie(LieuCategorieEnum categorie) {
        this.categorie = categorie;
    }
}
