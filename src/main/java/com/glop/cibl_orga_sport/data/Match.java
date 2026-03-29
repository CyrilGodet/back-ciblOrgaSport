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

    @ManyToOne(cascade = jakarta.persistence.CascadeType.ALL)
    private Resultat resultat;

    @ManyToMany
    @JoinTable(name = "match_participant", joinColumns = @JoinColumn(name = "match_id"), inverseJoinColumns = @JoinColumn(name = "participant_id"))
    private List<Participant> participants = new ArrayList<>();

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

    public List<Participant> getParticipants() {
        return participants;
    }

    public void setParticipants(List<Participant> participants) {
        this.participants = participants;
    }

    public MatchStatusEnum getStatus() {
        return status;
    }

    public void setStatus(MatchStatusEnum status) {
        this.status = status;
    }
}
