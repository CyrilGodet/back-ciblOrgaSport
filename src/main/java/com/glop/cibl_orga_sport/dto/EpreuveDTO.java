package com.glop.cibl_orga_sport.dto;

import java.util.Date;
import com.glop.cibl_orga_sport.data.enumType.CompetitionStatusEnum;
import com.glop.cibl_orga_sport.data.enumType.DisciplineEnum;
import com.glop.cibl_orga_sport.data.enumType.CompetitionGenreEnum;

public class EpreuveDTO {
    private Long idEpreuve;
    private String nomEpreuve;
    private CompetitionDTO competition;
    private DisciplineEnum discipline;
    private CompetitionGenreEnum genre;
    private Date dateDebut;
    private Date dateFin;
    private CompetitionStatusEnum statut;

    public EpreuveDTO() {}

    public EpreuveDTO(Long idEpreuve, String nomEpreuve, CompetitionDTO competition, DisciplineEnum discipline, CompetitionGenreEnum genre, Date dateDebut, Date dateFin, CompetitionStatusEnum statut) {
        this.idEpreuve = idEpreuve;
        this.nomEpreuve = nomEpreuve;
        this.competition = competition;
        this.discipline = discipline;
        this.genre = genre;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.statut = statut;
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

    public DisciplineEnum getDiscipline() {
        return discipline;
    }

    public void setDiscipline(DisciplineEnum discipline) {
        this.discipline = discipline;
    }

    public CompetitionGenreEnum getGenre() {
        return genre;
    }

    public void setGenre(CompetitionGenreEnum genre) {
        this.genre = genre;
    }

    public Date getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(Date dateDebut) {
        this.dateDebut = dateDebut;
    }

    public Date getDateFin() {
        return dateFin;
    }

    public void setDateFin(Date dateFin) {
        this.dateFin = dateFin;
    }

    public CompetitionStatusEnum getStatut() {
        return statut;
    }

    public void setStatut(CompetitionStatusEnum statut) {
        this.statut = statut;
    }

    
}
