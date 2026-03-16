package com.glop.cibl_orga_sport.mapper;

import com.glop.cibl_orga_sport.data.Competition;
import com.glop.cibl_orga_sport.data.Periode;
import com.glop.cibl_orga_sport.data.Lieu;
import com.glop.cibl_orga_sport.data.ConditionAge;
import com.glop.cibl_orga_sport.data.enumType.CompetitionPhaseType;
import com.glop.cibl_orga_sport.dto.CompetitionDTO;
import com.glop.cibl_orga_sport.dto.CompetitionPhaseTypeDto;
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

        if (competition.getPhases() != null) {
            dto.setPhases(competition.getPhases().stream()
                    .map(phase -> new CompetitionPhaseTypeDto(phase.name(), phase.getLabel()))
                    .collect(Collectors.toList()));
        }

        if (competition.getParticipations() != null) {
            dto.setParticipations(competition.getParticipations().stream()
                    .map(ParticipationMapper::toDTO)
                    .collect(Collectors.toList()));
        }

        if (competition.getPhases() != null) {
            dto.setPhases(competition.getPhases().stream()
                    .map(phase -> new CompetitionPhaseTypeDto(phase.name(), phase.getLabel()))
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

        if (dto.getPhases() != null) {
            competition.setPhases(dto.getPhases().stream()
                    .map(phaseDto -> CompetitionPhaseType.valueOf(phaseDto.getValue()))
                    .collect(Collectors.toList()));
        }

        if (dto.getPhases() != null) {
            competition.setPhases(dto.getPhases().stream()
                    .map(phaseDto -> CompetitionPhaseType.valueOf(phaseDto.getValue()))
                    .collect(Collectors.toList()));
        }

        return competition;
    }
}
