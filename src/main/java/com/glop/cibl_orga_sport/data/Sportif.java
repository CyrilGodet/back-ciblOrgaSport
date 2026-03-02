package com.glop.cibl_orga_sport.data;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Sportif extends Personne {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idSportif;

    public Sportif() {
        super();
    }

    public Sportif(String nom, String prenom) {
        super(nom, prenom);
    }

    public Long getIdSportif() {
        return idSportif;
    }

    public void setIdSportif(Long idSportif) {
        this.idSportif = idSportif;
    }
}
