package com.glop.cibl_orga_sport.mapper;

import com.glop.cibl_orga_sport.data.Epreuve;
import com.glop.cibl_orga_sport.data.Periode;

import java.sql.Date;

import com.glop.cibl_orga_sport.data.ConditionAge;
import com.glop.cibl_orga_sport.dto.EpreuveDTO;
import com.glop.cibl_orga_sport.data.enumType.CompetitionPhaseType;
import com.glop.cibl_orga_sport.data.enumType.TypeResultatEnum;
import com.glop.cibl_orga_sport.dto.CompetitionPhaseTypeDto;

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
        dto.setTailleEquipe(epreuve.getTailleEquipe());
        dto.setTypeResultat(epreuve.getTypeResultat());

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

        if (epreuve.getCompetition() != null && epreuve.getCompetition().getParticipations() != null) {
            dto.setParticipations(epreuve.getCompetition().getParticipations().stream()
                    .map(ParticipationMapper::toDTO)
                    .collect(java.util.stream.Collectors.toList()));
        }

        if (epreuve.getEtapesEpreuves() != null) {
            dto.setEtapesEpreuves(epreuve.getEtapesEpreuves().stream()
                    .map(EtapeEpreuveMapper::toDTO)
                    .collect(java.util.stream.Collectors.toList()));
        }

        if (epreuve.getPhaseOnGoing() != null) {
            dto.setPhaseOnGoing(new CompetitionPhaseTypeDto(epreuve.getPhaseOnGoing().name(),
                    epreuve.getPhaseOnGoing().getLabel()));
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
                    new Date(dto.getDateDebut().getTime()),
                    new Date(dto.getDateFin().getTime()));
            epreuve.setPeriode(periode);
        }

        ConditionAge conditionAge = new ConditionAge(dto.getAgeMin(), dto.getAgeMax());
        epreuve.setConditionAge(conditionAge);
        epreuve.setNombreEquipeParMatch(dto.getNbPerMatch());
        epreuve.setNbElimParMatch(dto.getNbElimMatch());
        epreuve.setTailleEquipe(dto.getTailleEquipe());
        epreuve.setTypeResultat(dto.getTypeResultat());

        if (dto.getPhaseOnGoing() != null) {
            epreuve.setPhaseOnGoing(CompetitionPhaseType.valueOf(dto.getPhaseOnGoing().getValue()));
        }

        return epreuve;
    }
}
