package com.glop.cibl_orga_sport.dto;

public class VolontaireDTO extends UtilisateurDTO {
    private String telephone;
    private String competences;
    
    public VolontaireDTO() {
        super();
    }
    
    public VolontaireDTO(Long idUtilisateur, String nom, String prenom, String email) {
        super(idUtilisateur, nom, prenom, email, 0, null);
    }
        
    public Long getIdVolontaire() {
        return getIdUtilisateur();
    }
    
    public void setIdVolontaire(Long idVolontaire) {
        setIdUtilisateur(idVolontaire);
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
