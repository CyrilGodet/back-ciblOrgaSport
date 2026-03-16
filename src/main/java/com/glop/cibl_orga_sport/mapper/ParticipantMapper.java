package com.glop.cibl_orga_sport.mapper;

import com.glop.cibl_orga_sport.data.ParticipantSportif;
import com.glop.cibl_orga_sport.data.ParticipantEquipe;
import com.glop.cibl_orga_sport.data.Participant;
import com.glop.cibl_orga_sport.dto.ParticipantEquipeDTO;
import com.glop.cibl_orga_sport.dto.ParticipantDTO;
import com.glop.cibl_orga_sport.dto.ParticipantSportifDTO;

public class ParticipantMapper {

    public static ParticipantDTO toDTO(Participant participant) {
        if (participant == null)
            return null;
        if (participant instanceof ParticipantSportif) {
            ParticipantSportif ps = (ParticipantSportif) participant;
            ParticipantSportifDTO dto = new ParticipantSportifDTO();
            dto.setIdParticipant(ps.getIdParticipant());
            dto.setSportif(SportifMapper.toDTO(ps.getSportif()));
            return dto;
        } else if (participant instanceof ParticipantEquipe) {
            return ParticipantEquipeMapper.toDTO((ParticipantEquipe) participant);
        }
        return null;
    }

    public static Participant toEntity(ParticipantDTO dto) {
        if (dto == null)
            return null;
        if (dto instanceof ParticipantSportifDTO) {
            ParticipantSportifDTO psDto = (ParticipantSportifDTO) dto;
            ParticipantSportif entity = new ParticipantSportif();
            entity.setIdParticipant(psDto.getIdParticipant());
            entity.setSportif(SportifMapper.toEntity(psDto.getSportif()));
            return entity;
        } else if (dto instanceof ParticipantEquipeDTO) {
            return ParticipantEquipeMapper.toEntity((ParticipantEquipeDTO) dto);
        }
        return null;
    }
}
