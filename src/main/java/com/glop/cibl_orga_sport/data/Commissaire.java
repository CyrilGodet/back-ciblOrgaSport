package com.glop.cibl_orga_sport.data;

import jakarta.persistence.Entity;
import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;

@Entity
@DiscriminatorValue("COMMISSAIRE")
public class Commissaire extends Utilisateur {

    @Column
    public boolean estAcrediteCEN;

    public Commissaire() {
        super();
    }

    public Commissaire(String nom, String prenom, String email, int age, Lieu lieu) {
        super(nom, prenom, email, age, lieu);
    }
}
