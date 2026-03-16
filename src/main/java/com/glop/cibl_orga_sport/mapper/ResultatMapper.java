package com.glop.cibl_orga_sport.mapper;

import com.glop.cibl_orga_sport.data.Resultat;
import com.glop.cibl_orga_sport.dto.ResultatDTO;
import java.util.stream.Collectors;
import java.util.ArrayList;

public class ResultatMapper {

    public static ResultatDTO toDTO(Resultat entity) {
        if (entity == null) {
            return null;
        }

        ResultatDTO dto = new ResultatDTO();
        dto.setIdResultat(entity.getIdResultat());
        dto.setStatus(entity.getStatus());

        if (entity.getDetails() != null) {
            dto.setDetails(entity.getDetails().stream()
                    .map(ResultatDetailsMapper::toDTO)
                    .collect(Collectors.toList()));
        } else {
            dto.setDetails(new ArrayList<>());
        }

        return dto;
    }

    public static Resultat toEntity(ResultatDTO dto) {
        if (dto == null) {
            return null;
        }

        Resultat entity = new Resultat();
        entity.setIdResultat(dto.getIdResultat());
        entity.setStatus(dto.getStatus());

        if (dto.getDetails() != null) {
            entity.setDetails(dto.getDetails().stream()
                    .map(ResultatDetailsMapper::toEntity)
                    .collect(Collectors.toList()));
            // Assure the bidirectional relation is set
            entity.getDetails().forEach(detail -> detail.setResultat(entity));
        } else {
            entity.setDetails(new ArrayList<>());
        }

        return entity;
    }
}
