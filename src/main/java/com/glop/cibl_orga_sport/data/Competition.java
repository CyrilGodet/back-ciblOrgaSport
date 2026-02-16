package com.glop.cibl_orga_sport.data;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.glop.cibl_orga_sport.data.enumType.CompetitionStatusEnum;
import com.glop.cibl_orga_sport.data.enumType.CompetitionGenreEnum;
import com.glop.cibl_orga_sport.data.enumType.CompetitionSportEnum;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Competition {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCompetition;

    @Column(unique = true, nullable = false)
    private String nameCompetition;

    @Column(nullable = true)
    private String description;

    @OneToMany(mappedBy = "competition")
    @JsonManagedReference("competition-epreuves")
    private List<Epreuve> epreuves;

    @OneToMany(mappedBy = "competition")
    @JsonManagedReference("competition-equipes")
    private List<Equipe> equipes;

    @Embedded
    private Periode periode;

    @ManyToOne
    private Lieu lieu;

    @Embedded
    private ConditionAge conditionAge;

    @Column(nullable = false)
    private CompetitionGenreEnum genre;

    @Column(nullable = false)
    private CompetitionStatusEnum statut;

    @Column(nullable = false)
    private CompetitionSportEnum sport;

    public Competition() {}

    public Competition(String nameCompetition, String description, Periode periode, Lieu lieu,
            ConditionAge conditionAge, CompetitionGenreEnum genre, CompetitionStatusEnum statut, CompetitionSportEnum sport) {
        this.nameCompetition = nameCompetition;
        this.description = description;
        this.periode = periode;
        this.lieu = lieu;
        this.conditionAge = conditionAge;
        this.genre = genre;
        this.statut = statut;
        this.sport = sport;
        this.epreuves = new ArrayList<>();
        this.equipes = new ArrayList<>();
    }

    public Long getIdCompetition() {
        return idCompetition;
    }

    public void setIdCompetition(Long idCompetition) {
        this.idCompetition = idCompetition;
    }

    public String getNameCompetition() {
        return nameCompetition;
    }

    public void setNameCompetition(String name) {
        this.nameCompetition = name;
    }

    public List<Epreuve> getEpreuves() {
        return epreuves;
    }

    public void setEpreuves(List<Epreuve> epreuves) {
        this.epreuves = epreuves;
    }

    public void addEpreuve(Epreuve epreuve) {
        this.epreuves.add(epreuve);
        epreuve.setCompetition(this);
    }

    public void removeEpreuve(Epreuve epreuve) throws IllegalAccessException {
        if(!this.epreuves.remove(epreuve)) {
            throw new IllegalAccessException("L'épreuve n'est pas associée à cette compétition.");
        }
        epreuve.setCompetition(null);
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public CompetitionSportEnum getSport() {
        return sport;
    }

    public void setSport(CompetitionSportEnum sport) {
        this.sport = sport;
    }

    public CompetitionGenreEnum getGenre() {
        return genre;
    }

    public void setGenre(CompetitionGenreEnum genre) {
        this.genre = genre;
    }

    public CompetitionStatusEnum getStatut() {
        return statut;
    }

    public void setStatut(CompetitionStatusEnum statut) {
        this.statut = statut;
    }

    public Periode getPeriode() {
        return periode;
    }

    public void setPeriode(Periode periode) {
        this.periode = periode;
    }

    public Lieu getLieu() {
        return lieu;
    }

    public void setLieu(Lieu lieu) {
        this.lieu = lieu;
    }

    public ConditionAge getConditionAge() {
        return conditionAge;
    }

    public void setConditionAge(ConditionAge conditionAge) {
        this.conditionAge = conditionAge;
    }

    public List<Equipe> getEquipes() {
        return equipes;
    }

    public void setEquipes(List<Equipe> equipes) {
        this.equipes = equipes;
    }
    
}
