package com.glop.cibl_orga_sport.dto;

import java.util.List;
import java.util.ArrayList;

public class EquipeDTO {
    private Long idEquipe;
    private String nomEquipe;
    private List<SportifDTO> participants = new ArrayList<>();

    public EquipeDTO() {
    }

    public EquipeDTO(Long idEquipe, String nomEquipe) {
        this.idEquipe = idEquipe;
        this.nomEquipe = nomEquipe;
    }

    public Long getIdEquipe() {
        return idEquipe;
    }

    public void setIdEquipe(Long idEquipe) {
        this.idEquipe = idEquipe;
    }

    public String getNomEquipe() {
        return nomEquipe;
    }

    public void setNomEquipe(String nomEquipe) {
        this.nomEquipe = nomEquipe;
    }

    public List<SportifDTO> getParticipants() {
        return participants;
    }

    public void setParticipants(List<SportifDTO> participants) {
        this.participants = participants;
    }
}
