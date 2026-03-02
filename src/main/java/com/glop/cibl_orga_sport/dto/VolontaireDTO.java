package com.glop.cibl_orga_sport.dto;

public class VolontaireDTO {
    private Long idVolontaire;
    private String nom;
    private String prenom;
    private String email;
    private String telephone;
    private String competences;
    
    public VolontaireDTO() {}
    
    public VolontaireDTO(Long idVolontaire, String nom, String prenom, String email) {
        this.idVolontaire = idVolontaire;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
    }
        
    public Long getIdVolontaire() {
        return idVolontaire;
    }
    
    public void setIdVolontaire(Long idVolontaire) {
        this.idVolontaire = idVolontaire;
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
    
    public String getTelephone() {
        return telephone;
    }
    
    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }
    
    public String getCompetences() {
        return competences;
    }
    
    public void setCompetences(String competences) {
        this.competences = competences;
    }
}
