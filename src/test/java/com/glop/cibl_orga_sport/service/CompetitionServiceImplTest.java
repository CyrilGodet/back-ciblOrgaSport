package com.glop.cibl_orga_sport.service;

import com.glop.cibl_orga_sport.data.Competition;
import com.glop.cibl_orga_sport.data.enumType.CompetitionStatusEnum;
import com.glop.cibl_orga_sport.data.enumType.CompetitionGenreEnum;
import com.glop.cibl_orga_sport.data.enumType.CompetitionSportEnum;
import com.glop.cibl_orga_sport.repository.CompetitionRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Date;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CompetitionServiceImplTest {

    @Mock
    private CompetitionRepository competitionRepository;

    @InjectMocks
    private CompetitionServiceImpl competitionService;

    @Test
    void testCreateCompetition() {
        Competition competition = new Competition("Championnats du monde de natation", Date.valueOf("2026-01-01"), Date.valueOf("2026-01-10"));
        competition.setIdCompetition(1L);
        competition.setDescription("Description test");
        competition.setSport(CompetitionSportEnum.NATATION);
        competition.setStatut(CompetitionStatusEnum.DRAFT);

        when(competitionRepository.save(any(Competition.class))).thenReturn(competition);

        Competition result = competitionService.createCompetition(
                "Championnats du monde de natation",
                "Description test",
                CompetitionSportEnum.NATATION,
                Date.valueOf("2026-01-01"),
                Date.valueOf("2026-01-10"),
                "France",
                true,
                "test",
                "01000",
                "Ville",
                CompetitionGenreEnum.HOMME,
                18,
                99
        );

        assertNotNull(result);
        assertEquals("Championnats du monde de natation", result.getNameCompetition());
        assertEquals(Date.valueOf("2026-01-01"), result.getDateDebut());
        assertEquals(Date.valueOf("2026-01-10"), result.getDateFin());
    }

    @Test
    void testGetAllCompetitions() {
        Competition competition1 = new Competition("Championnats du monde de natation", Date.valueOf("2026-01-01"), Date.valueOf("2026-01-10"));
        competition1.setIdCompetition(1L);

        Competition competition2 = new Competition("Jeux Olympiques", Date.valueOf("2026-07-01"), Date.valueOf("2026-08-01"));
        competition2.setIdCompetition(2L);

        when(competitionRepository.findAll()).thenReturn(Arrays.asList(competition1, competition2));

        List<Competition> result = competitionService.getAllCompetitions();

        assertEquals(2, result.size());
        assertEquals("Championnats du monde de natation", result.get(0).getNameCompetition());
        assertEquals("Jeux Olympiques", result.get(1).getNameCompetition());
    }

    @Test
    void testGetCompetition() {
        Competition competition = new Competition("Championnats du monde de natation", Date.valueOf("2026-01-01"), Date.valueOf("2026-01-10"));
        competition.setIdCompetition(1L);

        when(competitionRepository.findById(1L)).thenReturn(Optional.of(competition));

        Optional<Competition> result = competitionService.getCompetition(1L);

        assertTrue(result.isPresent());
        assertEquals("Championnats du monde de natation", result.get().getNameCompetition());
    }

    @Test
    void testUpdateCompetition() {
        Competition existingCompetition = new Competition("Championnats du monde de natation", Date.valueOf("2026-01-01"), Date.valueOf("2026-01-10"));
        existingCompetition.setIdCompetition(1L);

        when(competitionRepository.findById(1L)).thenReturn(Optional.of(existingCompetition));
        when(competitionRepository.save(any(Competition.class))).thenReturn(existingCompetition);

        Competition result = competitionService.updateCompetition(
                1L,
                "Championnats du monde de natation Modifié",
                "Description modifiée",
                CompetitionSportEnum.NATATION,
                Date.valueOf("2026-02-01"),
                Date.valueOf("2026-02-10"),
                "France",
                true,
                "1 rue test",
                "01000",
                "Ville",
                CompetitionGenreEnum.MIXTE,
                18,
                99,
                CompetitionStatusEnum.IN_PROGRESS
        );

        assertNotNull(result);
        assertEquals("Championnats du monde de natation Modifié", result.getNameCompetition());
        assertEquals(Date.valueOf("2026-02-01"), result.getDateDebut());
    }

    @Test
    void testUpdateCompetition_NotFound() {
        when(competitionRepository.findById(999L)).thenReturn(Optional.empty());

        Competition result = competitionService.updateCompetition(
                999L,
                "Championnats du monde de natation",
                "Description",
                CompetitionSportEnum.NATATION,
                Date.valueOf("2026-01-01"),
                Date.valueOf("2026-01-10"),
                "France",
                true,
                "1 rue test",
                "01000",
                "Ville",
                CompetitionGenreEnum.HOMME,
                18,
                99,
                CompetitionStatusEnum.DRAFT
        );

        assertNull(result);
    }

    @Test
    void testDeleteCompetition() {
        Competition competition = new Competition("Championnats du monde de natation", Date.valueOf("2026-01-01"), Date.valueOf("2026-01-10"));
        competition.setIdCompetition(1L);

        when(competitionRepository.findById(1L)).thenReturn(Optional.of(competition));

        boolean result = competitionService.deleteCompetition(1L);

        assertTrue(result);
    }

    @Test
    void testDeleteCompetition_NotFound() {
        when(competitionRepository.findById(999L)).thenReturn(Optional.empty());

        boolean result = competitionService.deleteCompetition(999L);

        assertFalse(result);
    }
}
