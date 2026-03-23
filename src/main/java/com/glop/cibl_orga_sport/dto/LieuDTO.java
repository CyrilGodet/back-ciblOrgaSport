package com.glop.cibl_orga_sport.dto;

import com.glop.cibl_orga_sport.data.Lieu;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class LieuDTO {
    private Long idLieu;
    private String nomLieu;
    private String ville;
    private String adresse;

    public static LieuDTO fromEntity(Lieu lieu) {
        if (lieu == null) {
            return null;
        }
        return LieuDTO.builder()
                .idLieu(lieu.getIdLieu())
                .nomLieu(lieu.getNomLieu())
                .ville(lieu.getVille())
                .adresse(lieu.getAdresse())
                .build();
    }

    public static Lieu toEntity(LieuDTO lieuDTO) {
        if (lieuDTO == null) {
            return null;
        }
        Lieu lieu = new Lieu();
        lieu.setIdLieu(lieuDTO.getIdLieu());
        lieu.setNomLieu(lieuDTO.getNomLieu());
        lieu.setVille(lieuDTO.getVille());
        lieu.setAdresse(lieuDTO.getAdresse());
        return lieu;
    }

    public LieuDTO(Long idLieu, String nom, String ville, String adresse) {
        this.idLieu = idLieu;
        this.nomLieu = nom;
        this.ville = ville;
        this.adresse = adresse;
    }

}
