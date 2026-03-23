package com.glop.cibl_orga_sport.mapper;

import com.glop.cibl_orga_sport.data.EtapeEpreuve;
import com.glop.cibl_orga_sport.data.Periode;
import com.glop.cibl_orga_sport.dto.EtapeEpreuveDTO;
import com.glop.cibl_orga_sport.dto.PeriodeDTO;
import java.util.stream.Collectors;

public class EtapeEpreuveMapper {

    public static EtapeEpreuveDTO toDTO(EtapeEpreuve etape) {
        if (etape == null)
            return null;

        EtapeEpreuveDTO dto = new EtapeEpreuveDTO();
        dto.setIdEtapeEpreuve(etape.getIdEtapeEpreuve());
        if (etape.getEpreuve() != null) {
            dto.setEpreuveId(etape.getEpreuve().getIdEpreuve());
        }

        if (etape.getPeriode() != null) {
            PeriodeDTO periodeDTO = new PeriodeDTO();
            periodeDTO.setDateDebut(etape.getPeriode().getDateDebut());
            periodeDTO.setDateFin(etape.getPeriode().getDateFin());
            dto.setPeriode(periodeDTO);
        }

        if (etape.getMatches() != null) {
            dto.setMatches(etape.getMatches().stream()
                    .map(MatchMapper::toDTO)
                    .collect(Collectors.toList()));
        }

        if (etape.getEquipes() != null) {
            dto.setEquipes(etape.getEquipes().stream()
                    .map(EquipeMapper::toDTO)
                    .collect(Collectors.toList()));
        }

        dto.setEtapeEpreuveEnum(etape.getEtapeEpreuveEnum());
        return dto;
    }

    public static EtapeEpreuve toEntity(EtapeEpreuveDTO dto) {
        if (dto == null)
            return null;

        EtapeEpreuve etape = new EtapeEpreuve();
        etape.setIdEtapeEpreuve(dto.getIdEtapeEpreuve());

        if (dto.getPeriode() != null) {
            Periode periode = new Periode(
                    new java.sql.Date(dto.getPeriode().getDateDebut().getTime()),
                    new java.sql.Date(dto.getPeriode().getDateFin().getTime()));
            etape.setPeriode(periode);
        }

        etape.setEtapeEpreuveEnum(dto.getEtapeEpreuveEnum());
        return etape;
    }
}
