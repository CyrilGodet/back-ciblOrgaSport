package com.glop.cibl_orga_sport.service;

import com.glop.cibl_orga_sport.data.Competition;
import com.glop.cibl_orga_sport.data.enumType.CompetitionStatusEnum;
import com.glop.cibl_orga_sport.data.enumType.CompetitionGenreEnum;
import com.glop.cibl_orga_sport.data.enumType.CompetitionSportEnum;
import com.glop.cibl_orga_sport.dto.CompetitionDTO;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

public interface CompetitionService {

    Competition createCompetition(CompetitionDTO competitionDTO);

    Competition updateCompetition(Long id, CompetitionDTO competitionDTO);

    boolean deleteCompetition(Long id);

    List<Competition> getAllCompetitions();

    Optional<Competition> getCompetition(Long id);

    Competition publishCompetition(Long id);

    Competition startCompetition(Long id);

    Competition finishCompetition(Long id);
}
