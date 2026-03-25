package com.glop.cibl_orga_sport.dto;

public class SportifNonConformeDTO {

    private Long idSportif;
    private String nom;
    private String prenom;
    private String email;

    public SportifNonConformeDTO() {
    }

    public SportifNonConformeDTO(Long idSportif, String nom, String prenom, String email) {
        this.idSportif = idSportif;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
