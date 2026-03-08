package com.glop.cibl_orga_sport.mapper;

import com.glop.cibl_orga_sport.data.Competition;
import com.glop.cibl_orga_sport.data.Periode;
import com.glop.cibl_orga_sport.data.Lieu;
import com.glop.cibl_orga_sport.data.ConditionAge;
import com.glop.cibl_orga_sport.dto.CompetitionDTO;
import java.util.stream.Collectors;
import java.util.ArrayList;

public class CompetitionMapper {

    public static CompetitionDTO toDTO(Competition competition) {
        if (competition == null)
            return null;

        CompetitionDTO dto = new CompetitionDTO();
        dto.setIdCompetition(competition.getIdCompetition());
        dto.setNameCompetition(competition.getNameCompetition());
        dto.setDescription(competition.getDescription());
        dto.setSport(competition.getSport());
        dto.setGenre(competition.getGenre());
        dto.setStatut(competition.getStatut());

        if (competition.getPeriode() != null) {
            dto.setDateDebut(competition.getPeriode().getDateDebut());
            dto.setDateFin(competition.getPeriode().getDateFin());
        }

        if (competition.getLieu() != null) {
            dto.setLieu(LieuMapper.toDTO(competition.getLieu()));
        }

        if (competition.getConditionAge() != null) {
            dto.setAgeMin(competition.getConditionAge().getAgeMin());
            dto.setAgeMax(competition.getConditionAge().getAgeMax());
        }

        if (competition.getEpreuves() != null) {
            dto.setEpreuves(competition.getEpreuves().stream()
                    .map(EpreuveMapper::toDTO)
                    .collect(Collectors.toList()));
        }

        if (competition.getEquipes() != null) {
            dto.setEquipes(competition.getEquipes().stream()
                    .map(EquipeMapper::toDTO)
                    .collect(Collectors.toList()));
        }

        return dto;
    }

    public static Competition toEntity(CompetitionDTO dto) {
        if (dto == null)
            return null;

        Periode periode = null;
        if (dto.getDateDebut() != null && dto.getDateFin() != null) {
            periode = new Periode(dto.getDateDebut(), dto.getDateFin());
        }

        Lieu lieu = null;
        if (dto.getLieu() != null) {
            lieu = LieuMapper.toEntity(dto.getLieu());
        }

        ConditionAge conditionAge = new ConditionAge(dto.getAgeMin(), dto.getAgeMax());

        Competition competition = new Competition(
                dto.getNameCompetition(),
                dto.getDescription(),
                periode,
                lieu,
                conditionAge,
                dto.getGenre(),
                dto.getStatut(),
                dto.getSport());

        if (dto.getEpreuves() != null) {
            competition.setEpreuves(dto.getEpreuves().stream()
                    .map(EpreuveMapper::toEntity)
                    .collect(Collectors.toList()));
            competition.getEpreuves().forEach(e -> e.setCompetition(competition));
        }

        if (dto.getEquipes() != null) {
            competition.setEquipes(dto.getEquipes().stream()
                    .map(EquipeMapper::toEntity)
                    .collect(Collectors.toList()));
            competition.getEquipes().forEach(e -> e.setCompetition(competition));
        }

        return competition;
    }
}
