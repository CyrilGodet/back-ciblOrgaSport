package com.glop.cibl_orga_sport.data;


import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Match {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idMatch;

    @ManyToOne
    private EtapeEpreuve etapeEpreuve;

    @Embedded
    private Periode periode;

    @ManyToOne
    private Resultat resultat;

    public Match() {
    }

    public Match(Periode periode, Resultat resultat) {
        this.periode = periode;
        this.resultat = resultat;
    }
}
