package com.glop.cibl_orga_sport.service;

import com.glop.cibl_orga_sport.data.Competition;
import java.sql.Date;
import java.util.List;
import java.util.Optional;

public interface CompetitionService {

    Competition createCompetition(String name, Date date_debut, Date date_fin);
    boolean deleteCompetition(Long id);
    List<Competition> getAllCompetitions();
    Optional<Competition> getCompetition(Long id);
}
