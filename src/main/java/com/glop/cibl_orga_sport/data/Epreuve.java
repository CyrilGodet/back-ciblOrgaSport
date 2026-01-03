package com.glop.cibl_orga_sport.data;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Epreuve {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idEpreuve;

    @Column
    private String nomEpreuve;

    @ManyToOne
    @JsonBackReference("competition-epreuves")
    private Competition competition;

    @OneToMany(mappedBy = "epreuve", cascade = jakarta.persistence.CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference("epreuve-phases")
    private Set<Phase> phases;

    @OneToMany(mappedBy = "epreuve", cascade = jakarta.persistence.CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference("epreuve-categories")
    private Set<Category> categories;

    public Epreuve() {}

    public Epreuve(String nomEpreuve) {
        this.nomEpreuve = nomEpreuve;
        this.phases = new HashSet<>();
        this.categories = new HashSet<>();
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

    public Competition getCompetition() {
        return competition;
    }

    public void setCompetition(Competition competition) {
        this.competition = competition;
    }

    public Set<Phase> getPhases() {
        return phases;
    }

    public void setPhases(Set<Phase> phases) {
        this.phases = phases;
    }

    public void addPhase(Phase phase) {
        this.phases.add(phase);
        phase.setEpreuve(this);
    }

    public void removePhase(Phase phase) throws IllegalArgumentException {
        if (!this.phases.remove(phase)) {
            throw new IllegalArgumentException("La phase n'est pas associée à cette épreuve");
        }
        phase.setEpreuve(null);
    }

    public Set<Category> getCategories() {
        return categories;
    }

    public void setCategories(Set<Category> categories) {
        this.categories = categories;
    }

    public void addCategory(Category category) {
        this.categories.add(category);
        category.setEpreuve(this);
    }

    public void removeCategory(Category category) throws IllegalArgumentException {
        if (!this.categories.remove(category)) {
            throw new IllegalArgumentException("La catégorie n'est pas associée à cette épreuve");
        }
        category.setEpreuve(null);
    }

}
