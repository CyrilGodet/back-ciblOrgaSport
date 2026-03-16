package com.glop.cibl_orga_sport.dto;

import java.util.List;
import java.util.ArrayList;

public class ParticipantEquipeDTO extends ParticipantDTO {
    private String nomEquipe;
    private List<SportifDTO> sportifs = new ArrayList<>();

    public ParticipantEquipeDTO() {
    }

    public ParticipantEquipeDTO(Long idParticipant, String nomEquipe) {
        setIdParticipant(idParticipant);
        this.nomEquipe = nomEquipe;
    }

    public String getNomEquipe() {
        return nomEquipe;
    }

    public void setNomEquipe(String nomEquipe) {
        this.nomEquipe = nomEquipe;
    }

    public List<SportifDTO> getSportifs() {
        return sportifs;
    }

    public void setSportifs(List<SportifDTO> sportifs) {
        this.sportifs = sportifs;
    }
}
