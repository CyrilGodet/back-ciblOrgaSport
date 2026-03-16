package com.glop.cibl_orga_sport.data;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.JoinTable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;

@Entity
public class Equipe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idEquipe;

    private String nomEquipe;

    @ManyToMany
    @JoinTable(name = "equipe_sportif", joinColumns = @JoinColumn(name = "equipe_id"), inverseJoinColumns = @JoinColumn(name = "sportif_id"))
    @JsonManagedReference("equipe-sportifs")
    private List<Sportif> participants = new ArrayList<>();

    @OneToMany(mappedBy = "equipe", cascade = jakarta.persistence.CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference("equipe-participations")
    private List<Participation> participations = new ArrayList<>();

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

    public List<Participation> getParticipations() {
        return participations;
    }

    public void setParticipations(List<Participation> participations) {
        this.participations = participations;
    }

}
