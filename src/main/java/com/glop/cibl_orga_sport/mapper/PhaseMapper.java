package com.glop.cibl_orga_sport.mapper;

import com.glop.cibl_orga_sport.data.EtapeEpreuve;
import com.glop.cibl_orga_sport.data.Periode;
import com.glop.cibl_orga_sport.data.enumType.EtapeEpreuveEnum;
import com.glop.cibl_orga_sport.dto.PhaseDTO;

public class PhaseMapper {

    public static PhaseDTO toDTO(EtapeEpreuve phase) {
        if (phase == null)
            return null;
        
        PhaseDTO dto = new PhaseDTO();
        dto.setIdPhase(phase.getIdEtapeEpreuve());
        dto.setEpreuve(EpreuveMapper.toDTO(phase.getEpreuve()));
        
        if (phase.getEtapeEpreuveEnum() != null) {
            dto.setNomPhase(phase.getEtapeEpreuveEnum().name());
        }
        
        if (phase.getPeriode() != null) {
            dto.setDateDebut(phase.getPeriode().getDateDebut());
            dto.setDateFin(phase.getPeriode().getDateFin());
        }
        dto.setLieu(null);
        
        return dto;
    }

    public static EtapeEpreuve toEntity(PhaseDTO dto) {
        if (dto == null)
            return null;
        
        EtapeEpreuve phase = new EtapeEpreuve();
        
        if (dto.getDateDebut() != null && dto.getDateFin() != null) {
            Periode periode = new Periode(dto.getDateDebut(), dto.getDateFin());
            phase.setPeriode(periode);
        }
        
        if (dto.getNomPhase() != null) {
            try {
                EtapeEpreuveEnum etapeEnum = EtapeEpreuveEnum.valueOf(dto.getNomPhase());
                phase.setEtapeEpreuveEnum(etapeEnum);
            } catch (IllegalArgumentException e) {
                System.out.println("Nom de phase invalide : " + dto.getNomPhase());
            }
        }
        
        return phase;
    }
}
