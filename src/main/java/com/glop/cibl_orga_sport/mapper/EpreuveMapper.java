package com.glop.cibl_orga_sport.mapper;

import com.glop.cibl_orga_sport.data.Epreuve;
import com.glop.cibl_orga_sport.data.Periode;
import com.glop.cibl_orga_sport.data.ConditionAge;
import com.glop.cibl_orga_sport.dto.EpreuveDTO;
import com.glop.cibl_orga_sport.data.enumType.CompetitionPhaseType;
import com.glop.cibl_orga_sport.dto.CompetitionPhaseTypeDto;
import java.sql.Date;
import java.util.stream.Collectors;

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
        dto.setNbPerMatch(epreuve.getNombreEquipeParMatch());
        dto.setNbElimMatch(epreuve.getNbElimParMatch());

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

        if (epreuve.getParticipations() != null) {
            dto.setParticipations(epreuve.getParticipations().stream()
                    .map(ParticipationMapper::toDTO)
                    .collect(java.util.stream.Collectors.toList()));
        }

        if (epreuve.getEtapesEpreuves() != null) {
            dto.setEtapesEpreuves(epreuve.getEtapesEpreuves().stream()
                    .map(EtapeEpreuveMapper::toDTO)
                    .collect(java.util.stream.Collectors.toList()));
        }

        if (epreuve.getPhaseOnGoing() != null) {
            dto.setPhaseOnGoing(new CompetitionPhaseTypeDto(epreuve.getPhaseOnGoing().name(), epreuve.getPhaseOnGoing().getLabel()));
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
        epreuve.setNombreEquipeParMatch(dto.getNbPerMatch());
        epreuve.setNbElimParMatch(dto.getNbElimMatch());

        if (dto.getPhaseOnGoing() != null) {
            epreuve.setPhaseOnGoing(CompetitionPhaseType.valueOf(dto.getPhaseOnGoing().getValue()));
        }

        return epreuve;
    }
}
