package com.glop.cibl_orga_sport.dto;

public class SportifDTO {
    private Long idSportif;
    private String nom;
    private String prenom;

    public SportifDTO() {
    }

    public SportifDTO(Long idSportif, String nom, String prenom) {
        this.idSportif = idSportif;
        this.nom = nom;
        this.prenom = prenom;
    }

    public Long getIdSportif() {
        return idSportif;
    }

    public void setIdSportif(Long idSportif) {
        this.idSportif = idSportif;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }
}
