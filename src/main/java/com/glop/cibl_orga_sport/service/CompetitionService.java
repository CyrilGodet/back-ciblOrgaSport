package com.glop.cibl_orga_sport.service;

import com.glop.cibl_orga_sport.data.Competition;
import com.glop.cibl_orga_sport.dto.CompetitionDTO;

import java.util.List;
import java.util.Optional;

public interface CompetitionService {

    Competition createCompetition(CompetitionDTO competitionDTO);

    Competition updateCompetition(Long id, CompetitionDTO competitionDTO);

    boolean deleteCompetition(Long id);

    List<Competition> getAllCompetitions();

    List<Competition> getPublishedCompetitions();

    Optional<Competition> getCompetition(Long id);

    List<com.glop.cibl_orga_sport.data.Match> getOngoingMatches(Long id);

    Competition publishCompetition(Long id);

    Competition unpublishCompetition(Long id);

    Competition startCompetition(Long id);

    Competition finishCompetition(Long id);

    List<Competition> getCompetitionsByCommissaireId(Long commissaireId);
}
