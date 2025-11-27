package com.glop.cibl_orga_sport.data;

import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;

@Entity
public class Epreuve {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idEpreuve;

    @Column
    private String nomEpreuve;

    @ManyToMany(mappedBy = "epreuves")
    private Set<Competition> competitions;

    @OneToMany(mappedBy = "epreuve")
    private Set<Phase> phases;

    @OneToMany(mappedBy = "epreuve")
    private Set<Category> categories;

    public Epreuve() {}

    public Epreuve(String nomEpreuve) {
        this.nomEpreuve = nomEpreuve;
    }

    public Long getIdEpreuve() {
        return idEpreuve;
    }

    public void setIdEpreuve(Long idEpreuve) {
        this.idEpreuve = idEpreuve;
    }
    
    public String getNomEpreuve() {
        return nomEpreuve;
    }

    public void setNomEpreuve(String nomEpreuve) {
        this.nomEpreuve = nomEpreuve;
    }

    public Set<Competition> getCompetitions() {
        return competitions;
    }

    public void setCompetitions(Set<Competition> competitions) {
        this.competitions = competitions;
    }

    public void addCompetition(Competition competition) {
        this.competitions.add(competition);
    }

    public void removeCompetition(Competition competition) throws IllegalArgumentException {
        if (!this.competitions.remove(competition)) {
            throw new IllegalArgumentException("La compétition n'est pas associée à cette épreuve");
        }
    }

    public Set<Phase> getPhases() {
        return phases;
    }

    public void setPhases(Set<Phase> phases) {
        this.phases = phases;
    }

    public void addPhase(Phase phase) {
        this.phases.add(phase);
    }

    public void removePhase(Phase phase) throws IllegalArgumentException {
        if (!this.phases.remove(phase)) {
            throw new IllegalArgumentException("La phase n'est pas associée à cette épreuve");
        }
    }

    public Set<Category> getCategories() {
        return categories;
    }

    public void setCategories(Set<Category> categories) {
        this.categories = categories;
    }

    public void addCategory(Category category) {
        this.categories.add(category);
    }

    public void removeCategory(Category category) throws IllegalArgumentException {
        if (!this.categories.remove(category)) {
            throw new IllegalArgumentException("La catégorie n'est pas associée à cette épreuve");
        }
    }

}
