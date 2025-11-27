package com.glop.cibl_orga_sport.data;

import java.sql.Date;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.JoinColumn;

@Entity
public class Competition {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCompetition;

    @Column
    private String nameCompetition;

    @ManyToMany
    @JoinTable(
        name = "constitution_competition_epreuve", 
        joinColumns = @JoinColumn(name = "competition_id"), 
        inverseJoinColumns = @JoinColumn(name = "epreuve_id")
    )
    private Set<Epreuve> epreuves;

    @Column
    private Date dateDebut;
    
    @Column
    private Date dateFin;

    public Competition() {}

    public Competition(String name, Date dateDebut, Date dateFin) {
        this.nameCompetition = name;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
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
    }

    public void removeEpreuve(Epreuve epreuve) throws IllegalAccessException {
        if(!this.epreuves.remove(epreuve)) {
            throw new IllegalAccessException("L'épreuve n'est pas associée à cette compétition.");
        }
    }
}
