package com.glop.cibl_orga_sport.data;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.JoinTable;
import jakarta.persistence.JoinColumn;

@Entity
public class ParticipantEquipe extends Participant {

    private String nomEquipe;

    @ManyToMany
    @JoinTable(name = "equipe_sportif", joinColumns = @JoinColumn(name = "equipe_id"), inverseJoinColumns = @JoinColumn(name = "sportif_id"))
    @JsonManagedReference("equipe-sportifs")
    private List<Sportif> sportifs = new ArrayList<>();

    public ParticipantEquipe() {
    }

    public ParticipantEquipe(String nomEquipe) {
        this.nomEquipe = nomEquipe;
        this.sportifs = new ArrayList<>();
    }

    public String getNomEquipe() {
        return nomEquipe;
    }

    public void setNomEquipe(String nomEquipe) {
        this.nomEquipe = nomEquipe;
    }

    public List<Sportif> getSportifs() {
        return sportifs;
    }

    public void setSportifs(List<Sportif> sportifs) {
        this.sportifs = sportifs;
    }
}
