package com.glop.cibl_orga_sport.data;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Volontaire extends Personne {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idVolontaire;
    
    @Column(unique = true, nullable = false)
    private String email;
    
    @Column
    private String telephone;
    
    @Column
    private String competences;
    
    @OneToMany(mappedBy = "volontaire", cascade = jakarta.persistence.CascadeType.ALL)
    @JsonManagedReference("volontaire-affectations")
    private List<AffectationVolontaire> affectations;
    
    public Volontaire() {
        super();
        this.affectations = new ArrayList<>();
    }
    
    public Volontaire(String nom, String prenom, String email) {
        super(nom, prenom);
        this.email = email;
        this.affectations = new ArrayList<>();
    }
        
    public Long getIdVolontaire() {
        return idVolontaire;
    }
    
    public void setIdVolontaire(Long idVolontaire) {
        this.idVolontaire = idVolontaire;
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
    
    public List<AffectationVolontaire> getAffectations() {
        return affectations;
    }
    
    public void setAffectations(List<AffectationVolontaire> affectations) {
        this.affectations = affectations;
    }
}
