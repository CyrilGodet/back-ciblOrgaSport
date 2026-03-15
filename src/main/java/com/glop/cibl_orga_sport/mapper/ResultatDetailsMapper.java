package com.glop.cibl_orga_sport.mapper;

import com.glop.cibl_orga_sport.data.ResultatDetails;
import com.glop.cibl_orga_sport.dto.ResultatDetailsDTO;

public class ResultatDetailsMapper {

    public static ResultatDetailsDTO toDTO(ResultatDetails entity) {
        if (entity == null) {
            return null;
        }

        ResultatDetailsDTO dto = new ResultatDetailsDTO();
        dto.setIdResultatDetails(entity.getIdResultatDetails());
        dto.setParticipant(ParticipantMapper.toDTO(entity.getParticipant()));
        dto.setRang(entity.getRang());
        dto.setStatus(entity.getStatus());

        return dto;
    }

    public static ResultatDetails toEntity(ResultatDetailsDTO dto) {
        if (dto == null) {
            return null;
        }

        ResultatDetails entity = new ResultatDetails();
        entity.setIdResultatDetails(dto.getIdResultatDetails());
        entity.setParticipant(ParticipantMapper.toEntity(dto.getParticipant()));
        entity.setRang(dto.getRang());
        entity.setStatus(dto.getStatus());

        return entity;
    }
}
