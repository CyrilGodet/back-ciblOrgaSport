package com.glop.cibl_orga_sport.mapper;

import com.glop.cibl_orga_sport.data.EtapeEpreuve;
import com.glop.cibl_orga_sport.dto.PhaseDTO;

public class PhaseMapper {

    public static PhaseDTO toDTO(EtapeEpreuve phase) {
        if (phase == null)
            return null;
        return new PhaseDTO(
                phase.getIdEtapeEpreuve(),
                phase.getNomPhase(),
                phase.getDateDebut(),
                phase.getDateFin(),
                EpreuveMapper.toDTO(phase.getEpreuve()),
                LieuMapper.toDTO(phase.getLieu()));
    }

    public static EtapeEpreuve toEntity(PhaseDTO dto) {
        if (dto == null)
            return null;
        EtapeEpreuve phase = new EtapeEpreuve(dto.getNomPhase(), dto.getDateDebut(), dto.getDateFin(), null, null);
        return phase;
    }
}
