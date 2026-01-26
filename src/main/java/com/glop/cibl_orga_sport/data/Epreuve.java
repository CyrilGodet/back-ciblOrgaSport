package com.glop.cibl_orga_sport.data;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.glop.cibl_orga_sport.data.enumType.CompetitionStatusEnum;
import com.glop.cibl_orga_sport.data.enumType.DisciplineEnum;
import com.glop.cibl_orga_sport.data.enumType.GenreEnum;

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

    @Column(nullable = false)
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

    @Column(nullable = false)
    private DisciplineEnum discipline;

    @Column(nullable = false)
    private GenreEnum genre;

    @Column(nullable = false)
    private Date dateDebut;

    @Column(nullable = false)
    private Date dateFin;

    @Column(nullable = false)
    private CompetitionStatusEnum statut;

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

    public DisciplineEnum getDiscipline() {
        return discipline;
    }

    public void setDiscipline(DisciplineEnum discipline) {
        this.discipline = discipline;
    }

    public GenreEnum getGenre() {
        return genre;
    }

    public void setGenre(GenreEnum genre) {
        this.genre = genre;
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

    public CompetitionStatusEnum getStatut() {
        return statut;
    }

    public void setStatut(CompetitionStatusEnum statut) {
        this.statut = statut;
    }

}
