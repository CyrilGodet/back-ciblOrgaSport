package com.glop.cibl_orga_sport.dto;

import com.glop.cibl_orga_sport.data.Lieu;
import com.glop.cibl_orga_sport.data.CoordonneesGPS;
import com.glop.cibl_orga_sport.data.enumType.LieuCategorieEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LieuDTO {
    private Long idLieu;
    private String nomLieu;
    private String ville;
    private String adresse;
    private LieuCategorieEnum categorie;
    private CoordonneesGPSDTO gpsCoordinates;


    public static LieuDTO fromEntity(Lieu lieu) {
        if (lieu == null) return null;
        LieuDTO dto = new LieuDTO();
        dto.setIdLieu(lieu.getIdLieu());
        dto.setNomLieu(lieu.getNomLieu());
        dto.setVille(lieu.getVille());
        dto.setAdresse(lieu.getAdresse());
        dto.setCategorie(lieu.getCategorie());
        if (lieu.getGpsCoordinates() != null) {
            dto.setGpsCoordinates(new CoordonneesGPSDTO(
                lieu.getGpsCoordinates().getLatitude(),
                lieu.getGpsCoordinates().getLongitude()
            ));
        }
        return dto;
    }

    public static Lieu toEntity(LieuDTO dto) {
        if (dto == null) return null;
        Lieu lieu = new Lieu();
        lieu.setIdLieu(dto.getIdLieu());
        lieu.setNomLieu(dto.getNomLieu());
        lieu.setVille(dto.getVille());
        lieu.setAdresse(dto.getAdresse());
        lieu.setCategorie(dto.getCategorie());
        if (dto.getGpsCoordinates() != null) {
            lieu.setGpsCoordinates(new CoordonneesGPS(
                dto.getGpsCoordinates().getLatitude(),
                dto.getGpsCoordinates().getLongitude()
            ));
        }
        return lieu;
    }
}
