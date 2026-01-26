package com.glop.cibl_orga_sport.mapper;

import com.glop.cibl_orga_sport.data.Competition;
import com.glop.cibl_orga_sport.dto.CompetitionDTO;

public class CompetitionMapper {
    
    public static CompetitionDTO toDTO(Competition competition) {
        if (competition == null) return null;
        return new CompetitionDTO(
            competition.getIdCompetition(),
            competition.getNameCompetition(),
            competition.getDescription(),
            competition.getSport(),
            competition.getDateDebut(),
            competition.getDateFin(),
            competition.getPays(),
            competition.isEstEnFrance(),
            competition.getAdresse(),
            competition.getCodePostal(),
            competition.getVille(),
            competition.getGenre(),
            competition.getAgeMin(),
            competition.getAgeMax(),
            competition.getStatut()
        );
    }

    public static Competition toEntity(CompetitionDTO dto) {
        if (dto == null) return null;
        Competition competition = new Competition(
            dto.getNameCompetition(),
            dto.getDateDebut(),
            dto.getDateFin()
        );
        competition.setDescription(dto.getDescription());
        competition.setSport(dto.getSport());
        competition.setPays(dto.getPays());
        competition.setEstEnFrance(dto.isEstEnFrance());
        competition.setAdresse(dto.getAdresse());
        competition.setCodePostal(dto.getCodePostal());
        competition.setVille(dto.getVille());
        competition.setGenre(dto.getGenre());
        competition.setAgeMin(dto.getAgeMin());
        competition.setAgeMax(dto.getAgeMax());
        competition.setStatut(dto.getStatut());
        return competition;
    }
}
