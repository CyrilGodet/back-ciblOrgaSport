package com.glop.cibl_orga_sport.data;

import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import java.util.ArrayList;
import java.util.List;

@Entity
@DiscriminatorValue("VOLONTAIRE")
public class Volontaire extends Utilisateur {

    @Column
    private String telephone;

    @Column
    private String competences;

    @OneToMany(mappedBy = "volontaire", cascade = CascadeType.ALL)
    @JsonManagedReference("volontaire-affectations")
    private List<AffectationVolontaire> affectations = new ArrayList<>();

    public Volontaire() {
        super();
    }

    public Volontaire(String nom, String prenom, String email, int age, Lieu lieu, String telephone) {
        super(nom, prenom, email, age, lieu);
        this.telephone = telephone;
        this.affectations = new ArrayList<>();
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

    // Si besoin d'un alias pour l'ID spécifique au volontaire
    public Long getIdVolontaire() {
        return getIdUtilisateur();
    }

    public void setIdVolontaire(Long idVolontaire) {
        setIdUtilisateur(idVolontaire);
    }
}