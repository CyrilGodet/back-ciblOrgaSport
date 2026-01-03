package com.glop.cibl_orga_sport.mapper;

import com.glop.cibl_orga_sport.data.Competition;
import com.glop.cibl_orga_sport.dto.CompetitionDTO;

public class CompetitionMapper {
    
    public static CompetitionDTO toDTO(Competition competition) {
        if (competition == null) return null;
        return new CompetitionDTO(
            competition.getIdCompetition(),
            competition.getNameCompetition(),
            competition.getDateDebut(),
            competition.getDateFin()
        );
    }

    public static Competition toEntity(CompetitionDTO dto) {
        if (dto == null) return null;
        Competition competition = new Competition(
            dto.getNameCompetition(),
            dto.getDateDebut(),
            dto.getDateFin()
        );
        return competition;
    }
}
