package com.glop.cibl_orga_sport.data;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.glop.cibl_orga_sport.data.enumType.CompetitionStatusEnum;
import com.glop.cibl_orga_sport.data.enumType.DisciplineEnum;
import com.glop.cibl_orga_sport.data.enumType.CompetitionGenreEnum;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
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

    @ManyToOne
    @JsonBackReference("competition-epreuves")
    private Competition competition;

    @Column(nullable = false)
    private String nomEpreuve;

    @Column(nullable = true)
    private String description;

    @Embedded
    private Periode periode;

    @Embedded
    private ConditionAge conditionAge;

    @OneToMany(mappedBy = "epreuve", cascade = jakarta.persistence.CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference("epreuve-phases")
    private List<EtapeEpreuve> etapesEpreuves;

    @OneToMany(mappedBy = "epreuve", cascade = jakarta.persistence.CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference("epreuve-participations")
    private List<Participation> participations = new ArrayList<>();

    @Column(nullable = false)
    private DisciplineEnum discipline;

    @Column(nullable = false)
    private CompetitionGenreEnum genre;

    @Column(nullable = false)
    private CompetitionStatusEnum statut;

    @Column(nullable = false)
    private int nombreEquipeParMatch = 2;

    public Epreuve() {
    }

    public Epreuve(String nomEpreuve) {
        this.nomEpreuve = nomEpreuve;
        this.etapesEpreuves = new ArrayList<>();
        this.nombreEquipeParMatch = 2;
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

    public List<EtapeEpreuve> getEtapesEpreuves() {
        return etapesEpreuves;
    }

    public void setEtapesEpreuves(List<EtapeEpreuve> etapesEpreuves) {
        this.etapesEpreuves = etapesEpreuves;
    }

    public void addPhase(EtapeEpreuve phase) {
        this.etapesEpreuves.add(phase);
        phase.setEpreuve(this);
    }

    public void removePhase(EtapeEpreuve phase) throws IllegalArgumentException {
        if (!this.etapesEpreuves.remove(phase)) {
            throw new IllegalArgumentException("La phase n'est pas associée à cette épreuve");
        }
        phase.setEpreuve(null);
    }

    public DisciplineEnum getDiscipline() {
        return discipline;
    }

    public void setDiscipline(DisciplineEnum discipline) {
        this.discipline = discipline;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Periode getPeriode() {
        return periode;
    }

    public void setPeriode(Periode periode) {
        this.periode = periode;
    }

    public ConditionAge getConditionAge() {
        return conditionAge;
    }

    public void setConditionAge(ConditionAge conditionAge) {
        this.conditionAge = conditionAge;
    }

    public List<Participation> getParticipations() {
        return participations;
    }

    public void setParticipations(List<Participation> participations) {
        this.participations = participations;
    }

    public int getNombreEquipeParMatch() {
        return nombreEquipeParMatch;
    }

    public void setNombreEquipeParMatch(int nombreEquipeParMatch) {
        this.nombreEquipeParMatch = nombreEquipeParMatch;
    }

}
