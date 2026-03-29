package com.glop.cibl_orga_sport.mapper;

import com.glop.cibl_orga_sport.data.Participation;
import com.glop.cibl_orga_sport.dto.ParticipationDTO;

public class ParticipationMapper {

    public static ParticipationDTO toDTO(Participation participation) {
        if (participation == null)
            return null;

        ParticipationDTO dto = new ParticipationDTO();
        dto.setIdParticipation(participation.getIdParticipation());
        if (participation.getCompetition() != null) {
            dto.setCompetitionId(participation.getCompetition().getIdCompetition());
        }
        dto.setParticipant(ParticipantMapper.toDTO(participation.getParticipant()));
        dto.setStatut(participation.getStatut());
        return dto;
    }

    public static Participation toEntity(ParticipationDTO dto) {
        if (dto == null)
            return null;

        Participation participation = new Participation();
        participation.setIdParticipation(dto.getIdParticipation());
        // Competition association is handled by the service during create/update
        // but for general mapping, we use ParticipantMapper
        if (dto.getParticipant() != null) {
            participation.setParticipant(ParticipantMapper.toEntity(dto.getParticipant()));
        }
        participation.setStatut(dto.getStatut());
        return participation;
    }
}
