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
                                 CompetitionGenreEnum genre, int ageMin, int ageMax, Long lieuId);
    Competition updateCompetition(Long id, String name, String description, CompetitionSportEnum sport, Date dateDebut, Date dateFin, 
                                 CompetitionGenreEnum genre, int ageMin, int ageMax, CompetitionStatusEnum statut, Long lieuId);
    boolean deleteCompetition(Long id);
    List<Competition> getAllCompetitions();
    Optional<Competition> getCompetition(Long id);
    
    Competition publishCompetition(Long id);
    Competition startCompetition(Long id);
    Competition finishCompetition(Long id);
}
