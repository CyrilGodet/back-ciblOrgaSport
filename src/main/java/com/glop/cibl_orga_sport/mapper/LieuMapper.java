package com.glop.cibl_orga_sport.mapper;

import com.glop.cibl_orga_sport.data.Lieu;
import com.glop.cibl_orga_sport.data.CoordonneesGPS;
import com.glop.cibl_orga_sport.dto.LieuDTO;
import com.glop.cibl_orga_sport.dto.CoordonneesGPSDTO;

public class LieuMapper {
    
    public static LieuDTO toDTO(Lieu lieu) {
        if (lieu == null) return null;
        LieuDTO dto = new LieuDTO(
            lieu.getIdLieu(),
            lieu.getNomLieu(),
            lieu.getVille(),
            lieu.getAdresse(),
            lieu.getCategorie(),
            null
        );
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
        CoordonneesGPS gps = null;
        if (dto.getGpsCoordinates() != null) {
            gps = new CoordonneesGPS(
                dto.getGpsCoordinates().getLatitude(),
                dto.getGpsCoordinates().getLongitude()
            );
        }
        Lieu lieu = new Lieu(
            dto.getNomLieu(),
            dto.getVille(),
            dto.getAdresse(),
            dto.getCategorie(),
            gps
        );
        lieu.setIdLieu(dto.getIdLieu());
        return lieu;
    }
}
