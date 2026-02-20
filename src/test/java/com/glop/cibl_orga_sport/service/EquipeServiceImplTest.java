package com.glop.cibl_orga_sport.service;

import com.glop.cibl_orga_sport.data.Competition;
import com.glop.cibl_orga_sport.data.Equipe;
import com.glop.cibl_orga_sport.data.Periode;
import com.glop.cibl_orga_sport.repository.CompetitionRepository;
import com.glop.cibl_orga_sport.repository.EquipeRepository;
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
class EquipeServiceImplTest {

    @Mock
    private EquipeRepository equipeRepository;

    @Mock
    private CompetitionRepository competitionRepository;

    @InjectMocks
    private EquipeServiceImpl equipeService;

    @Test
    void testCreateEquipe() {
        Periode periode = new Periode(Date.valueOf("2026-01-01"), Date.valueOf("2026-01-10"));
        Competition competition = new Competition("Championnats du monde", null, periode, null, null, null, null, null);
        competition.setIdCompetition(1L);

        Equipe equipe = new Equipe("Équipe de France");
        equipe.setIdEquipe(1L);
        equipe.setCompetition(competition);

        when(competitionRepository.findById(1L)).thenReturn(Optional.of(competition));
        when(equipeRepository.save(any(Equipe.class))).thenReturn(equipe);

        Equipe result = equipeService.createEquipe("Équipe de France", 1L);

        assertNotNull(result);
        assertEquals("Équipe de France", result.getNomEquipe());
        assertEquals(competition, result.getCompetition());
    }

    @Test
    void testCreateEquipe_WithoutCompetition() {
        Equipe equipe = new Equipe("Équipe de France");
        equipe.setIdEquipe(1L);

        when(equipeRepository.save(any(Equipe.class))).thenReturn(equipe);

        Equipe result = equipeService.createEquipe("Équipe de France", null);

        assertNotNull(result);
        assertEquals("Équipe de France", result.getNomEquipe());
    }

    @Test
    void testGetAllEquipes() {
        Equipe equipe1 = new Equipe("Équipe de France");
        equipe1.setIdEquipe(1L);

        Equipe equipe2 = new Equipe("Équipe d'Espagne");
        equipe2.setIdEquipe(2L);

        when(equipeRepository.findAll()).thenReturn(Arrays.asList(equipe1, equipe2));

        List<Equipe> result = equipeService.getAllEquipes();

        assertEquals(2, result.size());
        assertEquals("Équipe de France", result.get(0).getNomEquipe());
        assertEquals("Équipe d'Espagne", result.get(1).getNomEquipe());
    }

    @Test
    void testGetEquipe() {
        Equipe equipe = new Equipe("Équipe de France");
        equipe.setIdEquipe(1L);

        when(equipeRepository.findById(1L)).thenReturn(Optional.of(equipe));

        Optional<Equipe> result = equipeService.getEquipe(1L);

        assertTrue(result.isPresent());
        assertEquals("Équipe de France", result.get().getNomEquipe());
    }

    @Test
    void testGetEquipesByCompetitionId() {
        Equipe equipe1 = new Equipe("Équipe de France");
        equipe1.setIdEquipe(1L);

        Equipe equipe2 = new Equipe("Équipe d'Espagne");
        equipe2.setIdEquipe(2L);

        when(equipeRepository.findByCompetition_IdCompetition(1L))
                .thenReturn(Arrays.asList(equipe1, equipe2));

        List<Equipe> result = equipeService.getEquipesByCompetitionId(1L);

        assertEquals(2, result.size());
        assertEquals("Équipe de France", result.get(0).getNomEquipe());
        assertEquals("Équipe d'Espagne", result.get(1).getNomEquipe());
    }

    @Test
    void testUpdateEquipe() {
        Periode periode = new Periode(Date.valueOf("2026-01-01"), Date.valueOf("2026-01-10"));
        Competition competition = new Competition("Championnats du monde", null, periode, null, null, null, null, null);
        competition.setIdCompetition(1L);

        Equipe existingEquipe = new Equipe("Équipe de France");
        existingEquipe.setIdEquipe(1L);

        when(equipeRepository.findById(1L)).thenReturn(Optional.of(existingEquipe));
        when(competitionRepository.findById(1L)).thenReturn(Optional.of(competition));
        when(equipeRepository.save(any(Equipe.class))).thenReturn(existingEquipe);

        Equipe result = equipeService.updateEquipe(1L, "Équipe de France Modifiée", 1L);

        assertNotNull(result);
        assertEquals("Équipe de France Modifiée", result.getNomEquipe());
        assertEquals(competition, result.getCompetition());
    }

    @Test
    void testUpdateEquipe_NotFound() {
        when(equipeRepository.findById(999L)).thenReturn(Optional.empty());

        Equipe result = equipeService.updateEquipe(999L, "Équipe test", 1L);

        assertNull(result);
    }

    @Test
    void testDeleteEquipe() {
        when(equipeRepository.existsById(1L)).thenReturn(true);

        boolean result = equipeService.deleteEquipe(1L);

        assertTrue(result);
    }

    @Test
    void testDeleteEquipe_NotFound() {
        when(equipeRepository.existsById(999L)).thenReturn(false);

        boolean result = equipeService.deleteEquipe(999L);

        assertFalse(result);
    }
}
