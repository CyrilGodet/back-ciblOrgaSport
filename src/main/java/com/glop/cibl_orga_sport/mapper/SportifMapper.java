package com.glop.cibl_orga_sport.mapper;

import com.glop.cibl_orga_sport.data.Sportif;
import com.glop.cibl_orga_sport.dto.SportifDTO;

public class SportifMapper {

    public static SportifDTO toDTO(Sportif sportif) {
        if (sportif == null)
            return null;

        SportifDTO dto = new SportifDTO();
        UtilisateurMapper.mapToDTO(sportif, dto);
        return dto;
    }

    public static Sportif toEntity(SportifDTO dto) {
        if (dto == null)
            return null;

        Sportif sportif = new Sportif();
        UtilisateurMapper.mapToEntity(dto, sportif);
        return sportif;
    }
}
