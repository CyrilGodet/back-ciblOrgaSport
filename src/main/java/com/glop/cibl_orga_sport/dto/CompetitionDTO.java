package com.glop.cibl_orga_sport.dto;

import java.sql.Date;
import com.glop.cibl_orga_sport.data.enumType.CompetitionStatusEnum;
import com.glop.cibl_orga_sport.data.enumType.CompetitionGenreEnum;
import com.glop.cibl_orga_sport.data.enumType.CompetitionSportEnum;

public class CompetitionDTO {
    private Long idCompetition;
    private String nameCompetition;
    private String description;
    private CompetitionSportEnum sport;
    private Date dateDebut;
    private Date dateFin;
    private CompetitionGenreEnum genre;
    private int ageMin;
    private int ageMax;
    private CompetitionStatusEnum statut;
    private LieuDTO lieu;

    public CompetitionDTO() {}

    public CompetitionDTO(Long idCompetition, String nameCompetition, String description, CompetitionSportEnum sport, Date dateDebut, Date dateFin, CompetitionGenreEnum genre, int ageMin, int ageMax, CompetitionStatusEnum statut, LieuDTO lieu) {
        this.idCompetition = idCompetition;
        this.nameCompetition = nameCompetition;
        this.description = description;
        this.sport = sport;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.genre = genre;
        this.ageMin = ageMin;
        this.ageMax = ageMax;
        this.statut = statut;
        this.lieu = lieu;
    }

    public Long getIdCompetition() {
        return idCompetition;
    }

    public void setIdCompetition(Long idCompetition) {
        this.idCompetition = idCompetition;
    }

    public String getNameCompetition() {
        return nameCompetition;
    }

    public void setNameCompetition(String nameCompetition) {
        this.nameCompetition = nameCompetition;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public CompetitionSportEnum getSport() {
        return sport;
    }

    public void setSport(CompetitionSportEnum sport) {
        this.sport = sport;
    }

    public CompetitionGenreEnum getGenre() {
        return genre;
    }

    public void setGenre(CompetitionGenreEnum genre) {
        this.genre = genre;
    }

    public int getAgeMin() {
        return ageMin;
    }

    public void setAgeMin(int ageMin) {
        this.ageMin = ageMin;
    }

    public int getAgeMax() {
        return ageMax;
    }

    public void setAgeMax(int ageMax) {
        this.ageMax = ageMax;
    }

    public CompetitionStatusEnum getStatut() {
        return statut;
    }

    public void setStatut(CompetitionStatusEnum statut) {
        this.statut = statut;
    }

    public LieuDTO getLieu() {
        return lieu;
    }

    public void setLieu(LieuDTO lieu) {
        this.lieu = lieu;
    }
}
