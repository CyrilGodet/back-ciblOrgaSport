package com.glop.cibl_orga_sport.mapper;

import com.glop.cibl_orga_sport.data.Participation;
import com.glop.cibl_orga_sport.dto.ParticipationDTO;

public class ParticipationMapper {

    public static ParticipationDTO toDTO(Participation participation) {
        if (participation == null)
            return null;

        ParticipationDTO dto = new ParticipationDTO();
        dto.setIdParticipation(participation.getIdParticipation());
        if (participation.getEpreuve() != null) {
            dto.setEpreuveId(participation.getEpreuve().getIdEpreuve());
        }
        dto.setEquipe(EquipeMapper.toDTO(participation.getEquipe()));
        dto.setStatut(participation.getStatut());
        return dto;
    }

    public static Participation toEntity(ParticipationDTO dto) {
        if (dto == null)
            return null;

        Participation participation = new Participation();
        participation.setIdParticipation(dto.getIdParticipation());
        // Epreuve association will be handled by EpreuveMapper/Service
        participation.setEquipe(EquipeMapper.toEntity(dto.getEquipe()));
        participation.setStatut(dto.getStatut());
        return participation;
    }
}
