package com.glop.cibl_orga_sport.data;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.*;
import java.util.List;
import java.util.ArrayList;

@Entity
@DiscriminatorValue("SPORTIF")
public class Sportif extends Utilisateur {

    @Column
    public String comiteNational;

    @Column
    public boolean estConformeCharteEuropeenne;

    @Column
    public boolean aFouniPasseport;

    @Column
    public boolean aFounicertificatMedical;

    @ManyToMany(mappedBy = "sportifs")
    @JsonBackReference("equipe-sportifs")
    private List<ParticipantEquipe> equipes = new ArrayList<>();

    public Sportif() {
        super();
    }

    public Sportif(String nom, String prenom, String email, int age, Lieu lieu) {
        super(nom, prenom, email, age, lieu);
    }

    

    public Sportif(String nom, String prenom, String email, int age, Lieu lieu, String comiteNational,
            boolean estConformeCharteEuropeenne, boolean aFouniPasseport, boolean aFounicertificatMedical,
            List<ParticipantEquipe> equipes) {
        super(nom, prenom, email, age, lieu);
        this.comiteNational = comiteNational;
        this.estConformeCharteEuropeenne = estConformeCharteEuropeenne;
        this.aFouniPasseport = aFouniPasseport;
        this.aFounicertificatMedical = aFounicertificatMedical;
        this.equipes = equipes;
    }

    public String getComiteNational() {
        return comiteNational;
    }

    public void setComiteNational(String comiteNational) {
        this.comiteNational = comiteNational;
    }

    public boolean isEstConformeCharteEuropeenne() {
        return estConformeCharteEuropeenne;
    }

    public void setEstConformeCharteEuropeenne(boolean estConformeCharteEuropeenne) {
        this.estConformeCharteEuropeenne = estConformeCharteEuropeenne;
    }

    public boolean isaFouniPasseport() {
        return aFouniPasseport;
    }

    public void setaFouniPasseport(boolean aFouniPasseport) {
        this.aFouniPasseport = aFouniPasseport;
    }

    public boolean isaFounicertificatMedical() {
        return aFounicertificatMedical;
    }

    public void setaFounicertificatMedical(boolean aFounicertificatMedical) {
        this.aFounicertificatMedical = aFounicertificatMedical;
    }

    public List<ParticipantEquipe> getEquipes() {
        return equipes;
    }

    public void setEquipes(List<ParticipantEquipe> equipes) {
        this.equipes = equipes;
    }

    
}
