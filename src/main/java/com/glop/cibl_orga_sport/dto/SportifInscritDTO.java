package com.glop.cibl_orga_sport.dto;

public class SportifInscritDTO {

    private Long idSportif;
    private String nom;
    private String prenom;
    private String email;
    private boolean estConformeCharteEuropeenne;

    public SportifInscritDTO() {
    }

    public SportifInscritDTO(Long idSportif, String nom, String prenom, String email,
            boolean estConformeCharteEuropeenne) {
        this.idSportif = idSportif;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.estConformeCharteEuropeenne = estConformeCharteEuropeenne;
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

    public boolean isEstConformeCharteEuropeenne() {
        return estConformeCharteEuropeenne;
    }

    public void setEstConformeCharteEuropeenne(boolean estConformeCharteEuropeenne) {
        this.estConformeCharteEuropeenne = estConformeCharteEuropeenne;
    }
}
