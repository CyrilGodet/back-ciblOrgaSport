package com.glop.cibl_orga_sport.data;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.*;
import java.util.List;
import java.util.ArrayList;

@Entity
@DiscriminatorValue("SPORTIF")
public class Sportif extends Utilisateur {

    @ManyToMany(mappedBy = "sportifs")
    @JsonBackReference("equipe-sportifs")
    private List<ParticipantEquipe> equipes = new ArrayList<>();

    public Sportif() {
        super();
    }

    public Sportif(String nom, String prenom, String email, int age, Lieu lieu) {
        super(nom, prenom, email, age, lieu);
    }

    public Long getIdSportif() {
        return getIdUtilisateur();
    }

    public void setIdSportif(Long idSportif) {
        setIdUtilisateur(idSportif);
    }

    public List<ParticipantEquipe> getEquipes() {
        return equipes;
    }

    public void setEquipes(List<ParticipantEquipe> equipes) {
        this.equipes = equipes;
    }
}
