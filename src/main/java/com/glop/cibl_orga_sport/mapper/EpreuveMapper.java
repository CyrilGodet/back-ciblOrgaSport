package com.glop.cibl_orga_sport.mapper;

import com.glop.cibl_orga_sport.data.Epreuve;
import com.glop.cibl_orga_sport.data.Periode;
import com.glop.cibl_orga_sport.data.ConditionAge;
import com.glop.cibl_orga_sport.dto.EpreuveDTO;
import java.sql.Date;

public class EpreuveMapper {

    public static EpreuveDTO toDTO(Epreuve epreuve) {
        if (epreuve == null)
            return null;

        EpreuveDTO dto = new EpreuveDTO();
        dto.setIdEpreuve(epreuve.getIdEpreuve());
        dto.setNomEpreuve(epreuve.getNomEpreuve());
        dto.setDescription(epreuve.getDescription());
        dto.setDiscipline(epreuve.getDiscipline());
        dto.setGenre(epreuve.getGenre());
        dto.setStatut(epreuve.getStatut());

        if (epreuve.getCompetition() != null) {
            dto.setCompetitionId(epreuve.getCompetition().getIdCompetition());
        }

        if (epreuve.getPeriode() != null) {
            dto.setDateDebut(epreuve.getPeriode().getDateDebut());
            dto.setDateFin(epreuve.getPeriode().getDateFin());
        }

        if (epreuve.getConditionAge() != null) {
            dto.setAgeMin(epreuve.getConditionAge().getAgeMin());
            dto.setAgeMax(epreuve.getConditionAge().getAgeMax());
        }

        return dto;
    }

    public static Epreuve toEntity(EpreuveDTO dto) {
        if (dto == null)
            return null;

        Epreuve epreuve = new Epreuve(dto.getNomEpreuve());
        epreuve.setDescription(dto.getDescription());
        epreuve.setDiscipline(dto.getDiscipline());
        epreuve.setGenre(dto.getGenre());
        epreuve.setStatut(dto.getStatut());

        if (dto.getDateDebut() != null && dto.getDateFin() != null) {
            Periode periode = new Periode(
                    new java.sql.Date(dto.getDateDebut().getTime()),
                    new java.sql.Date(dto.getDateFin().getTime()));
            epreuve.setPeriode(periode);
        }

        ConditionAge conditionAge = new ConditionAge(dto.getAgeMin(), dto.getAgeMax());
        epreuve.setConditionAge(conditionAge);

        return epreuve;
    }
}
