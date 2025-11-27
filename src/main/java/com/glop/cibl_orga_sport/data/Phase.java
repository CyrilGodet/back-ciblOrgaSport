package com.glop.cibl_orga_sport.data;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Phase {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPhase;

    @Column
    private String nomPhase;

    @ManyToOne
    private Epreuve epreuve;

    public Phase() {}

    public Phase(String nomPhase, Epreuve epreuve) {
        this.nomPhase = nomPhase;
        this.epreuve = epreuve;
    }

    public Long getIdPhase() {
        return idPhase;
    }

    public void setIdPhase(Long idPhase) {
        this.idPhase = idPhase;
    }

    public String getNomPhase() {
        return nomPhase;
    }

    public void setNomPhase(String nomPhase) {
        this.nomPhase = nomPhase;
    }

    public Epreuve getEpreuve() {
        return epreuve;
    }

    public void setEpreuve(Epreuve epreuve) {
        this.epreuve = epreuve;
    }
    
}
