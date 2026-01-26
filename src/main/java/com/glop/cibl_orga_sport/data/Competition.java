package com.glop.cibl_orga_sport.data;

import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.glop.cibl_orga_sport.data.enumType.CompetitionStatusEnum;
import com.glop.cibl_orga_sport.data.enumType.GenreEnum;
import com.glop.cibl_orga_sport.data.enumType.SportEnum;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Competition {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCompetition;

    @Column(unique = true, nullable = false)
    private String nameCompetition;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private SportEnum sport;

    @OneToMany(mappedBy = "competition")
    @JsonManagedReference("competition-epreuves")
    private Set<Epreuve> epreuves;

    @Column(nullable = false)
    private Date dateDebut;
    
    @Column(nullable = false)
    private Date dateFin;

    @Column(nullable = false)
    private String pays;

    @Column(nullable = false)
    private boolean estEnFrance;

    @Column(nullable = false)
    private String adresse;
    
    @Column
    private String codePostal;

    @Column
    private String ville;

    @Column(nullable = false)
    private GenreEnum genre;

    @Column(nullable = true)
    private int ageMin;

    @Column(nullable = true)
    private int ageMax;

    @Column(nullable = false)
    private CompetitionStatusEnum statut;

    public Competition() {}

    public Competition(String name, Date dateDebut, Date dateFin) {
        this.nameCompetition = name;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.epreuves = new HashSet<>();
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

    public Date getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(Date dateDebut) {
        this.dateDebut = dateDebut;
    }

    public Date getDateFin() {
        return dateFin;
    }

    public void setDateFin(Date dateFin) {
        this.dateFin = dateFin;
    }

    public Set<Epreuve> getEpreuves() {
        return epreuves;
    }

    public void setEpreuves(Set<Epreuve> epreuves) {
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

    public SportEnum getSport() {
        return sport;
    }

    public void setSport(SportEnum sport) {
        this.sport = sport;
    }

    public String getPays() {
        return pays;
    }

    public void setPays(String pays) {
        this.pays = pays;
    }

    public boolean isEstEnFrance() {
        return estEnFrance;
    }

    public void setEstEnFrance(boolean estEnFrance) {
        this.estEnFrance = estEnFrance;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getCodePostal() {
        return codePostal;
    }

    public void setCodePostal(String codePostal) {
        this.codePostal = codePostal;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public GenreEnum getGenre() {
        return genre;
    }

    public void setGenre(GenreEnum genre) {
        this.genre = genre;
    }

    public int getAgeMin() {
        return ageMin;
    }

    public void setAgeMin(int ageMin) {
        this.ageMin = ageMin;
    }

    public int getAgeMax() {
        return ageMax;
    }

    public void setAgeMax(int ageMax) {
        this.ageMax = ageMax;
    }

    public CompetitionStatusEnum getStatut() {
        return statut;
    }

    public void setStatut(CompetitionStatusEnum statut) {
        this.statut = statut;
    }
    
}
