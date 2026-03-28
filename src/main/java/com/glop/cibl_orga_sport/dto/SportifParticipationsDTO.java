package com.glop.cibl_orga_sport.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SportifParticipationsDTO {
    private ParticipantSportifDTO participantSportif;
    private List<ParticipantEquipeDTO> participantEquipes;
}
