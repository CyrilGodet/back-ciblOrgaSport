package com.glop.cibl_orga_sport.dto;

import java.util.Date;
import com.glop.cibl_orga_sport.data.enumType.CompetitionStatusEnum;
import com.glop.cibl_orga_sport.data.enumType.DisciplineEnum;
import com.glop.cibl_orga_sport.data.enumType.CompetitionGenreEnum;
import java.util.List;
import java.util.ArrayList;

public class EpreuveDTO {
    private Long idEpreuve;
    private String nomEpreuve;
    private String description;
    private Long competitionId;
    private DisciplineEnum discipline;
    private CompetitionGenreEnum genre;
    private Date dateDebut;
    private Date dateFin;
    private int ageMin;
    private int ageMax;
    private CompetitionStatusEnum statut;
    private List<ParticipationDTO> participations = new ArrayList<>();

    public EpreuveDTO() {
    }

    public EpreuveDTO(Long idEpreuve, String nomEpreuve, String description, Long competitionId,
            DisciplineEnum discipline, CompetitionGenreEnum genre, Date dateDebut, Date dateFin, int ageMin, int ageMax,
            CompetitionStatusEnum statut, List<ParticipationDTO> participations) {
        this.idEpreuve = idEpreuve;
        this.nomEpreuve = nomEpreuve;
        this.description = description;
        this.competitionId = competitionId;
        this.discipline = discipline;
        this.genre = genre;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.ageMin = ageMin;
        this.ageMax = ageMax;
        this.statut = statut;
        this.participations = participations != null ? participations : new ArrayList<>();
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getCompetitionId() {
        return competitionId;
    }

    public void setCompetitionId(Long competitionId) {
        this.competitionId = competitionId;
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

    public List<ParticipationDTO> getParticipations() {
        return participations;
    }

    public void setParticipations(List<ParticipationDTO> participations) {
        this.participations = participations;
    }

}
