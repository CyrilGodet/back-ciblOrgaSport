package com.glop.cibl_orga_sport.mapper;

import com.glop.cibl_orga_sport.data.Sportif;
import com.glop.cibl_orga_sport.dto.SportifDTO;

public class SportifMapper {

    public static SportifDTO toDTO(Sportif sportif) {
        if (sportif == null)
            return null;

        SportifDTO dto = new SportifDTO();
        dto.setIdSportif(sportif.getIdSportif());
        dto.setNom(sportif.getNom());
        dto.setPrenom(sportif.getPrenom());
        return dto;
    }

    public static Sportif toEntity(SportifDTO dto) {
        if (dto == null)
            return null;

        Sportif sportif = new Sportif();
        sportif.setIdSportif(dto.getIdSportif());
        sportif.setNom(dto.getNom());
        sportif.setPrenom(dto.getPrenom());
        return sportif;
    }
}
