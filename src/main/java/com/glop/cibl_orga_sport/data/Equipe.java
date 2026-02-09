package com.glop.cibl_orga_sport.data;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Transient;

@Entity
public class Equipe {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idEquipe;

    private String nomEquipe;

    @Transient
    private List<Sportif> participants;

    public Equipe() {
    }

    public Equipe(String nomEquipe) {
        this.nomEquipe = nomEquipe;
        this.participants = new ArrayList<>();
    }

    public Long getIdEquipe() {
        return idEquipe;
    }

    public void setIdEquipe(Long idEquipe) {
        this.idEquipe = idEquipe;
    }

    public String getNomEquipe() {
        return nomEquipe;
    }

    public void setNomEquipe(String nomEquipe) {
        this.nomEquipe = nomEquipe;
    }

    public List<Sportif> getParticipants() {
        return participants;
    }

    public void setParticipants(List<Sportif> participants) {
        this.participants = participants;
    }

    
}
