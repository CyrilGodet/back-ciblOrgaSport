package com.glop.cibl_orga_sport.service;

import com.glop.cibl_orga_sport.data.Competition;
import com.glop.cibl_orga_sport.data.enumType.CompetitionStatusEnum;
import com.glop.cibl_orga_sport.data.enumType.CompetitionGenreEnum;
import com.glop.cibl_orga_sport.data.enumType.CompetitionSportEnum;
import java.sql.Date;
import java.util.List;
import java.util.Optional;

public interface CompetitionService {

    Competition createCompetition(String name, String description, CompetitionSportEnum sport, Date dateDebut, Date dateFin, 
                                 String pays, boolean estEnFrance, String adresse, String codePostal, String ville, 
                                 CompetitionGenreEnum genre, int ageMin, int ageMax);
    Competition updateCompetition(Long id, String name, String description, CompetitionSportEnum sport, Date dateDebut, Date dateFin, 
                                 String pays, boolean estEnFrance, String adresse, String codePostal, String ville, 
                                 CompetitionGenreEnum genre, int ageMin, int ageMax, CompetitionStatusEnum statut);
    boolean deleteCompetition(Long id);
    List<Competition> getAllCompetitions();
    Optional<Competition> getCompetition(Long id);
}
