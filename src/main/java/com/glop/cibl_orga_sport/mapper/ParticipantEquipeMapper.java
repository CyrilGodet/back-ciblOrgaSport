package com.glop.cibl_orga_sport.mapper;

import com.glop.cibl_orga_sport.data.ParticipantEquipe;
import com.glop.cibl_orga_sport.dto.ParticipantEquipeDTO;
import java.util.stream.Collectors;

public class ParticipantEquipeMapper {

    public static ParticipantEquipeDTO toDTO(ParticipantEquipe equipe) {
        if (equipe == null) {
            return null;
        }
        ParticipantEquipeDTO dto = new ParticipantEquipeDTO(
                equipe.getIdParticipant(),
                equipe.getNomEquipe());
        if (equipe.getSportifs() != null) {
            dto.setSportifs(equipe.getSportifs().stream()
                    .map(SportifMapper::toDTO)
                    .collect(Collectors.toList()));
        }
        return dto;
    }

    public static ParticipantEquipe toEntity(ParticipantEquipeDTO dto) {
        if (dto == null) {
            return null;
        }
        ParticipantEquipe equipe = new ParticipantEquipe(dto.getNomEquipe());
        equipe.setIdParticipant(dto.getIdParticipant());
        if (dto.getSportifs() != null) {
            equipe.setSportifs(dto.getSportifs().stream()
                    .map(SportifMapper::toEntity)
                    .collect(Collectors.toList()));
        }
        return equipe;
    }
}
