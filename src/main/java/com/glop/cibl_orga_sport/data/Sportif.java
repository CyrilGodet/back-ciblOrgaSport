package com.glop.cibl_orga_sport.data;

import com.fasterxml.jackson.annotation.JsonBackReference;

import com.glop.cibl_orga_sport.data.enumType.DocumentStatusEnum;
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

    @Enumerated(EnumType.STRING)
    private DocumentStatusEnum certificatMedicalStatus;

    @Enumerated(EnumType.STRING)
    private DocumentStatusEnum passeportStatus;

    @Column(name = "certificat_medical_contenu", columnDefinition = "bytea")
    private byte[] certificatMedicalContenu;

    @Column(name = "passeport_contenu", columnDefinition = "bytea")
    private byte[] passeportContenu;

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

    public byte[] getCertificatMedicalContenu() {
        return certificatMedicalContenu;
    }

    public void setCertificatMedicalContenu(byte[] certificatMedicalContenu) {
        this.certificatMedicalContenu = certificatMedicalContenu;
    }

    public byte[] getPasseportContenu() {
        return passeportContenu;
    }

    public void setPasseportContenu(byte[] passeportContenu) {
        this.passeportContenu = passeportContenu;
    }

    public DocumentStatusEnum getCertificatMedicalStatus() {
        return certificatMedicalStatus;
    }

    public void setCertificatMedicalStatus(DocumentStatusEnum certificatMedicalStatus) {
        this.certificatMedicalStatus = certificatMedicalStatus;
    }

    public DocumentStatusEnum getPasseportStatus() {
        return passeportStatus;
    }

    public void setPasseportStatus(DocumentStatusEnum passeportStatus) {
        this.passeportStatus = passeportStatus;
    }

    public List<ParticipantEquipe> getEquipes() {
        return equipes;
    }

    public void setEquipes(List<ParticipantEquipe> equipes) {
        this.equipes = equipes;
    }

    
}
