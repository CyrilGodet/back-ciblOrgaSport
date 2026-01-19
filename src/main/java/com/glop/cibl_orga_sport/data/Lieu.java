package com.glop.cibl_orga_sport.data;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Lieu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idLieu;

    @Column
    private String nom;

    @Column
    private String ville;

    @Column
    private String adresse;

    @OneToMany(mappedBy = "lieu", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference("lieu-phases")
    private Set<Phase> phases;

    public Lieu() {}

    public Lieu(String nom, String ville, String adresse) {
        this.nom = nom;
        this.ville = ville;
        this.adresse = adresse;
        this.phases = new HashSet<>();
    }

    public Long getIdLieu() {
        return idLieu;
    }

    public void setIdLieu(Long idLieu) {
        this.idLieu = idLieu;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public Set<Phase> getPhases() {
        return phases;
    }

    public void setPhases(Set<Phase> phases) {
        this.phases = phases;
    }

    public void addPhase(Phase phase) {
        this.phases.add(phase);
        phase.setLieu(this);
    }

    public void removePhase(Phase phase) throws IllegalArgumentException {
        if (!this.phases.remove(phase)) {
            throw new IllegalArgumentException("La phase n'est pas associée à ce lieu");
        }
        phase.setLieu(null);
    }

}
