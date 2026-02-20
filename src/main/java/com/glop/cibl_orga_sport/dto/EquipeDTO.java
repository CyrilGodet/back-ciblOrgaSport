package com.glop.cibl_orga_sport.dto;

public class EquipeDTO {
    private Long idEquipe;
    private String nomEquipe;
    private Long competitionId;

    public EquipeDTO() {
    }

    public EquipeDTO(Long idEquipe, String nomEquipe, Long competitionId) {
        this.idEquipe = idEquipe;
        this.nomEquipe = nomEquipe;
        this.competitionId = competitionId;
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

    public Long getCompetitionId() {
        return competitionId;
    }

    public void setCompetitionId(Long competitionId) {
        this.competitionId = competitionId;
    }
}
