package com.glop.cibl_orga_sport.dto;

public class SportifDTO extends UtilisateurDTO {
    public SportifDTO() {
        super();
    }

    public Long getIdSportif() {
        return getIdUtilisateur();
    }

    private CertificatMedicalDTO certificatMedical;
    private PasseportDTO passeport;

    private boolean estConformeCharteEuropeenne;
    private boolean aFouniPasseport;
    private boolean aFounicertificatMedical;

    public boolean isEstConformeCharteEuropeenne() {
        return estConformeCharteEuropeenne;
    }

    public void setEstConformeCharteEuropeenne(boolean estConformeCharteEuropeenne) {
        this.estConformeCharteEuropeenne = estConformeCharteEuropeenne;
    }

    public boolean isAFouniPasseport() {
        return aFouniPasseport;
    }

    public void setAFouniPasseport(boolean aFouniPasseport) {
        this.aFouniPasseport = aFouniPasseport;
    }

    public boolean isAFounicertificatMedical() {
        return aFounicertificatMedical;
    }

    public void setAFounicertificatMedical(boolean aFounicertificatMedical) {
        this.aFounicertificatMedical = aFounicertificatMedical;
    }

    public CertificatMedicalDTO getCertificatMedical() {
        return certificatMedical;
    }

    public void setCertificatMedical(CertificatMedicalDTO certificatMedical) {
        this.certificatMedical = certificatMedical;
    }

    public PasseportDTO getPasseport() {
        return passeport;
    }

    public void setPasseport(PasseportDTO passeport) {
        this.passeport = passeport;
    }

    public void setIdSportif(Long idSportif) {
        setIdUtilisateur(idSportif);
    }
}
