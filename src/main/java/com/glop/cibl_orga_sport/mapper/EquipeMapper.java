package com.glop.cibl_orga_sport.mapper;

import com.glop.cibl_orga_sport.data.Equipe;
import com.glop.cibl_orga_sport.dto.EquipeDTO;
import java.util.stream.Collectors;

public class EquipeMapper {

    public static EquipeDTO toDTO(Equipe equipe) {
        if (equipe == null) {
            return null;
        }
        EquipeDTO dto = new EquipeDTO(
                equipe.getIdEquipe(),
                equipe.getNomEquipe());
        if (equipe.getParticipants() != null) {
            dto.setParticipants(equipe.getParticipants().stream()
                    .map(SportifMapper::toDTO)
                    .collect(Collectors.toList()));
        }
        return dto;
    }

    public static Equipe toEntity(EquipeDTO dto) {
        if (dto == null) {
            return null;
        }
        Equipe equipe = new Equipe(dto.getNomEquipe());
        equipe.setIdEquipe(dto.getIdEquipe());
        if (dto.getParticipants() != null) {
            equipe.setParticipants(dto.getParticipants().stream()
                    .map(SportifMapper::toEntity)
                    .collect(Collectors.toList()));
        }
        return equipe;
    }
}
