package com.glop.cibl_orga_sport.mapper;

import com.glop.cibl_orga_sport.data.Phase;
import com.glop.cibl_orga_sport.dto.PhaseDTO;

public class PhaseMapper {
    
    public static PhaseDTO toDTO(Phase phase) {
        if (phase == null) return null;
        return new PhaseDTO(
            phase.getIdPhase(),
            phase.getNomPhase(),
            EpreuveMapper.toDTO(phase.getEpreuve()),
            LieuMapper.toDTO(phase.getLieu())
        );
    }

    public static Phase toEntity(PhaseDTO dto) {
        if (dto == null) return null;
        Phase phase = new Phase(dto.getNomPhase(), null);
        return phase;
    }
}
