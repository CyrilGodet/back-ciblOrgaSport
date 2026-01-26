package com.glop.cibl_orga_sport.service;

import com.glop.cibl_orga_sport.data.Competition;
import com.glop.cibl_orga_sport.data.enumType.CompetitionStatusEnum;
import com.glop.cibl_orga_sport.data.enumType.GenreEnum;
import com.glop.cibl_orga_sport.data.enumType.SportEnum;
import java.sql.Date;
import java.util.List;
import java.util.Optional;

public interface CompetitionService {

    Competition createCompetition(String name, String description, SportEnum sport, Date dateDebut, Date dateFin, 
                                 String pays, boolean estEnFrance, String adresse, String codePostal, String ville, 
                                 GenreEnum genre, int ageMin, int ageMax);
    Competition updateCompetition(Long id, String name, String description, SportEnum sport, Date dateDebut, Date dateFin, 
                                 String pays, boolean estEnFrance, String adresse, String codePostal, String ville, 
                                 GenreEnum genre, int ageMin, int ageMax, CompetitionStatusEnum statut);
    boolean deleteCompetition(Long id);
    List<Competition> getAllCompetitions();
    Optional<Competition> getCompetition(Long id);
}
