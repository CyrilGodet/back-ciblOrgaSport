package com.glop.cibl_orga_sport.mapper;

import com.glop.cibl_orga_sport.data.Match;
import com.glop.cibl_orga_sport.data.Periode;
import com.glop.cibl_orga_sport.dto.MatchDTO;
import com.glop.cibl_orga_sport.dto.PeriodeDTO;
import java.util.stream.Collectors;

public class MatchMapper {

    public static MatchDTO toDTO(Match match) {
        if (match == null)
            return null;

        MatchDTO dto = new MatchDTO();
        dto.setIdMatch(match.getIdMatch());
        if (match.getEtapeEpreuve() != null) {
            dto.setEtapeEpreuveId(match.getEtapeEpreuve().getIdEtapeEpreuve());
        }

        if (match.getEquipes() != null) {
            dto.setEquipes(match.getEquipes().stream()
                    .map(EquipeMapper::toDTO)
                    .collect(Collectors.toList()));
        }

        if (match.getPeriode() != null) {
            PeriodeDTO periodeDTO = new PeriodeDTO();
            periodeDTO.setDateDebut(match.getPeriode().getDateDebut());
            periodeDTO.setDateFin(match.getPeriode().getDateFin());
            dto.setPeriode(periodeDTO);
        }

        if (match.getResultat() != null) {
            dto.setResultat(ResultatMapper.toDTO(match.getResultat()));
        }

        dto.setStatus(match.getStatus());
        return dto;
    }

    public static Match toEntity(MatchDTO dto) {
        if (dto == null)
            return null;

        Match match = new Match();
        match.setIdMatch(dto.getIdMatch());

        if (dto.getPeriode() != null) {
            Periode periode = new Periode(
                    new java.sql.Date(dto.getPeriode().getDateDebut().getTime()),
                    new java.sql.Date(dto.getPeriode().getDateFin().getTime()));
            match.setPeriode(periode);
        }

        if (dto.getResultat() != null) {
            match.setResultat(ResultatMapper.toEntity(dto.getResultat()));
        }

        match.setStatus(dto.getStatus());
        return match;
    }
}
