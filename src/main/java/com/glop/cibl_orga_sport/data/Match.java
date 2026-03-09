package com.glop.cibl_orga_sport.data;

import com.glop.cibl_orga_sport.data.enumType.MatchStatusEnum;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Match {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idMatch;

    @ManyToOne
    private EtapeEpreuve etapeEpreuve;

    @Embedded
    private Periode periode;

    @ManyToOne
    private Resultat resultat;

    @ManyToMany
    @JoinTable(name = "match_equipe", joinColumns = @JoinColumn(name = "match_id"), inverseJoinColumns = @JoinColumn(name = "equipe_id"))
    private List<Equipe> equipes = new ArrayList<>();

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private MatchStatusEnum status;

    public Match() {
    }

    public Match(Periode periode, Resultat resultat) {
        this.periode = periode;
        this.resultat = resultat;
        this.status = MatchStatusEnum.PENDING;
    }

    public Long getIdMatch() {
        return idMatch;
    }

    public void setIdMatch(Long idMatch) {
        this.idMatch = idMatch;
    }

    public EtapeEpreuve getEtapeEpreuve() {
        return etapeEpreuve;
    }

    public void setEtapeEpreuve(EtapeEpreuve etapeEpreuve) {
        this.etapeEpreuve = etapeEpreuve;
    }

    public Periode getPeriode() {
        return periode;
    }

    public void setPeriode(Periode periode) {
        this.periode = periode;
    }

    public Resultat getResultat() {
        return resultat;
    }

    public void setResultat(Resultat resultat) {
        this.resultat = resultat;
    }

    public List<Equipe> getEquipes() {
        return equipes;
    }

    public void setEquipes(List<Equipe> equipes) {
        this.equipes = equipes;
    }

    public MatchStatusEnum getStatus() {
        return status;
    }

    public void setStatus(MatchStatusEnum status) {
        this.status = status;
    }
}
