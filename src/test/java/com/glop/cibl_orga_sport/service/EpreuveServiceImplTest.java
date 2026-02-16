package com.glop.cibl_orga_sport.service;

import com.glop.cibl_orga_sport.data.Competition;
import com.glop.cibl_orga_sport.data.Epreuve;
import com.glop.cibl_orga_sport.data.enumType.CompetitionStatusEnum;
import com.glop.cibl_orga_sport.data.enumType.DisciplineEnum;
import com.glop.cibl_orga_sport.data.enumType.CompetitionGenreEnum;
import com.glop.cibl_orga_sport.repository.EpreuveRepository;
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
class EpreuveServiceImplTest {

    @Mock
    private EpreuveRepository epreuveRepository;

    @InjectMocks
    private EpreuveServiceImpl epreuveService;

    @Test
    void testCreateEpreuve() {
        com.glop.cibl_orga_sport.data.Periode periode = new com.glop.cibl_orga_sport.data.Periode(Date.valueOf("2026-01-01"), Date.valueOf("2026-01-10"));
        Competition competition = new Competition("Championnats du monde de natation", null, periode, null, null, null, null, null);
        competition.setIdCompetition(1L);

        Epreuve epreuve = new Epreuve("100m nage libre");
        epreuve.setIdEpreuve(1L);
        epreuve.setCompetition(competition);
        epreuve.setStatut(CompetitionStatusEnum.DRAFT);

        when(epreuveRepository.save(any(Epreuve.class))).thenReturn(epreuve);

        Epreuve result = epreuveService.createEpreuve(
                "100m nage libre",
                DisciplineEnum.NAGE_LIBRE,
                CompetitionGenreEnum.HOMME,
                new java.sql.Date(System.currentTimeMillis()),
                new java.sql.Date(System.currentTimeMillis() + 86400000),
                competition
        );

        assertNotNull(result);
        assertEquals("100m nage libre", result.getNomEpreuve());
        assertEquals(competition, result.getCompetition());
    }

    @Test
    void testGetAllEpreuves() {
        Epreuve epreuve1 = new Epreuve("100m nage libre");
        epreuve1.setIdEpreuve(1L);

        Epreuve epreuve2 = new Epreuve("200m nage libre");
        epreuve2.setIdEpreuve(2L);

        when(epreuveRepository.findAll()).thenReturn(Arrays.asList(epreuve1, epreuve2));

        List<Epreuve> result = epreuveService.getAllEpreuves();

        assertEquals(2, result.size());
        assertEquals("100m nage libre", result.get(0).getNomEpreuve());
        assertEquals("200m nage libre", result.get(1).getNomEpreuve());
    }

    @Test
    void testGetEpreuve() {
        Epreuve epreuve = new Epreuve("100m nage libre");
        epreuve.setIdEpreuve(1L);

        when(epreuveRepository.findById(1L)).thenReturn(Optional.of(epreuve));

        Optional<Epreuve> result = epreuveService.getEpreuve(1L);

        assertTrue(result.isPresent());
        assertEquals("100m nage libre", result.get().getNomEpreuve());
    }

    @Test
    void testUpdateEpreuve() {
        com.glop.cibl_orga_sport.data.Periode periode = new com.glop.cibl_orga_sport.data.Periode(Date.valueOf("2026-01-01"), Date.valueOf("2026-01-10"));
        Competition competition = new Competition("Championnats du monde de natation", null, periode, null, null, null, null, null);
        competition.setIdCompetition(1L);

        Epreuve existingEpreuve = new Epreuve("100m nage libre");
        existingEpreuve.setIdEpreuve(1L);
        existingEpreuve.setStatut(CompetitionStatusEnum.DRAFT);

        when(epreuveRepository.findById(1L)).thenReturn(Optional.of(existingEpreuve));
        when(epreuveRepository.save(any(Epreuve.class))).thenReturn(existingEpreuve);

        Epreuve result = epreuveService.updateEpreuve(
                1L,
                "200m nage libre",
                DisciplineEnum.NAGE_LIBRE,
                CompetitionGenreEnum.FEMME,
                new java.sql.Date(System.currentTimeMillis()),
                new java.sql.Date(System.currentTimeMillis() + 86400000),
                CompetitionStatusEnum.IN_PROGRESS,
                competition
        );

        assertNotNull(result);
        assertEquals("200m nage libre", result.getNomEpreuve());
        assertEquals(competition, result.getCompetition());
    }

    @Test
    void testUpdateEpreuve_NotFound() {
        when(epreuveRepository.findById(999L)).thenReturn(Optional.empty());

        Epreuve result = epreuveService.updateEpreuve(
                999L,
                "100m nage libre",
                DisciplineEnum.NAGE_LIBRE,
                CompetitionGenreEnum.HOMME,
                null,
                null,
                CompetitionStatusEnum.DRAFT,
                null
        );

        assertNull(result);
    }

    @Test
    void testDeleteEpreuve() {
        Epreuve epreuve = new Epreuve("100m nage libre");
        epreuve.setIdEpreuve(1L);
        epreuve.setStatut(CompetitionStatusEnum.DRAFT);

        when(epreuveRepository.findById(1L)).thenReturn(Optional.of(epreuve));

        boolean result = epreuveService.deleteEpreuve(1L);

        assertTrue(result);
    }

    @Test
    void testDeleteEpreuve_NotFound() {
        when(epreuveRepository.findById(999L)).thenReturn(Optional.empty());

        boolean result = epreuveService.deleteEpreuve(999L);

        assertFalse(result);
    }
}
