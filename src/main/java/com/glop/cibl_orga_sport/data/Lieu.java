package com.glop.cibl_orga_sport.data;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
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

    public Lieu() {}

    public Lieu(String nomLieu, String ville, String adresse) {
        this.nomLieu = nomLieu;
        this.ville = ville;
        this.adresse = adresse;
    }

}
