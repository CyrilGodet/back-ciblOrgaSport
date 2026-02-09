package com.glop.cibl_orga_sport.data;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.glop.cibl_orga_sport.data.enumType.EtapeEpreuveEnum;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class EtapeEpreuve {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idEtapeEpreuve;

    @ManyToOne
    @JsonBackReference("epreuve-phases")
    private Epreuve epreuve;

    @Embedded
    private Periode periode;

    @ManyToOne
    private Resultat resultat;

    @OneToMany(mappedBy = "etapeEpreuve")
    private List<Match> matches;

    @Column(nullable = false)
    private EtapeEpreuveEnum etapeEpreuveEnum;

    public EtapeEpreuve() {
    }

    public EtapeEpreuve(Epreuve epreuve, Periode periode, Resultat resultat, EtapeEpreuveEnum etapeEpreuveEnum) {
        this.epreuve = epreuve;
        this.periode = periode;
        this.resultat = resultat;
        this.etapeEpreuveEnum = etapeEpreuveEnum;
    }

    public Long getIdEtapeEpreuve() {
        return idEtapeEpreuve;
    }

    public void setIdEtapeEpreuve(Long idPhase) {
        this.idEtapeEpreuve = idPhase;
    }

    public Epreuve getEpreuve() {
        return epreuve;
    }

    public void setEpreuve(Epreuve epreuve) {
        this.epreuve = epreuve;
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

    public List<Match> getMatches() {
        return matches;
    }

    public void setMatches(List<Match> matches) {
        this.matches = matches;
    }

    public EtapeEpreuveEnum getEtapeEpreuveEnum() {
        return etapeEpreuveEnum;
    }

    public void setEtapeEpreuveEnum(EtapeEpreuveEnum etapeEpreuveEnum) {
        this.etapeEpreuveEnum = etapeEpreuveEnum;
    }

    

}
