package com.glop.cibl_orga_sport.data;

import jakarta.persistence.Entity;
import jakarta.persistence.DiscriminatorValue;

@Entity
@DiscriminatorValue("VISITEUR")
public class Visiteur extends Utilisateur {
    public Visiteur() {
        super();
    }

    public Visiteur(String nom, String prenom, String email, int age, Lieu lieu) {
        super(nom, prenom, email, age, lieu);
    }
}
