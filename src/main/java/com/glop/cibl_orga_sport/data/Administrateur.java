package com.glop.cibl_orga_sport.data;

import jakarta.persistence.Entity;
import jakarta.persistence.DiscriminatorValue;

@Entity
@DiscriminatorValue("ADMIN")
public class Administrateur extends Utilisateur {
    public Administrateur() {
        super();
    }

    public Administrateur(String nom, String prenom, String email, int age, Lieu lieu) {
        super(nom, prenom, email, age, lieu);
    }
}
