package com.glop.cibl_orga_sport.mapper;

import com.glop.cibl_orga_sport.data.Epreuve;
import com.glop.cibl_orga_sport.dto.EpreuveDTO;

public class EpreuveMapper {
    
    public static EpreuveDTO toDTO(Epreuve epreuve) {
        if (epreuve == null) return null;
        return new EpreuveDTO(
            epreuve.getIdEpreuve(),
            epreuve.getNomEpreuve(),
            CompetitionMapper.toDTO(epreuve.getCompetition()),
            epreuve.getDiscipline(),
            epreuve.getGenre(),
            epreuve.getDateDebut(),
            epreuve.getDateFin(),
            epreuve.getStatut()
        );
    }

    public static Epreuve toEntity(EpreuveDTO dto) {
        if (dto == null) return null;
        Epreuve epreuve = new Epreuve(dto.getNomEpreuve());
        epreuve.setDiscipline(dto.getDiscipline());
        epreuve.setGenre(dto.getGenre());
        epreuve.setDateDebut(dto.getDateDebut());
        epreuve.setDateFin(dto.getDateFin());
        epreuve.setStatut(dto.getStatut());
        return epreuve;
    }
}
