package com.glop.cibl_orga_sport.data;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Embedded;
import com.glop.cibl_orga_sport.data.enumType.LieuCategorieEnum;

@Entity
public class Lieu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idLieu;

    @Column(nullable = false)
    private String nomLieu;

    @Column(nullable = false)
    private String ville;

    @Column
    private String adresse;

    @Enumerated(EnumType.STRING)
    @Column(name = "categorie_lieu")
    private LieuCategorieEnum categorie = LieuCategorieEnum.UTILISATEUR;

    @Embedded
    private CoordonneesGPS gpsCoordinates;

    public Lieu() {}


    public Lieu(String nomLieu, String ville, String adresse, LieuCategorieEnum categorie, CoordonneesGPS gpsCoordinates) {
        this.nomLieu = nomLieu;
        this.ville = ville;
        this.adresse = adresse;
        this.categorie = categorie;
        this.gpsCoordinates = gpsCoordinates;
    }

    public Lieu(String nomLieu, String ville, String adresse, LieuCategorieEnum categorie) {
        this.nomLieu = nomLieu;
        this.ville = ville;
        this.adresse = adresse;
        this.categorie = categorie;
        this.gpsCoordinates = null;
    }

    public Long getIdLieu() {
        return idLieu;
    }

    public void setIdLieu(Long idLieu) {
        this.idLieu = idLieu;
    }

    public String getNomLieu() {
        return nomLieu;
    }

    public void setNomLieu(String nomLieu) {
        this.nomLieu = nomLieu;
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

    public LieuCategorieEnum getCategorie() {
        return categorie;
    }

    public void setCategorie(LieuCategorieEnum categorie) {
        this.categorie = categorie;
    }

    public CoordonneesGPS getGpsCoordinates() {
        return gpsCoordinates;
    }

    public void setGpsCoordinates(CoordonneesGPS gpsCoordinates) {
        this.gpsCoordinates = gpsCoordinates;
    }
}
