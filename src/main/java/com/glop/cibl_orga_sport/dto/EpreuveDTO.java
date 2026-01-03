package com.glop.cibl_orga_sport.dto;

public class EpreuveDTO {
    private Long idEpreuve;
    private String nomEpreuve;
    private CompetitionDTO competition;

    public EpreuveDTO() {}

    public EpreuveDTO(Long idEpreuve, String nomEpreuve, CompetitionDTO competition) {
        this.idEpreuve = idEpreuve;
        this.nomEpreuve = nomEpreuve;
        this.competition = competition;
    }

    public Long getIdEpreuve() {
        return idEpreuve;
    }

    public void setIdEpreuve(Long idEpreuve) {
        this.idEpreuve = idEpreuve;
    }

    public String getNomEpreuve() {
        return nomEpreuve;
    }

    public void setNomEpreuve(String nomEpreuve) {
        this.nomEpreuve = nomEpreuve;
    }

    public CompetitionDTO getCompetition() {
        return competition;
    }

    public void setCompetition(CompetitionDTO competition) {
        this.competition = competition;
    }
}
