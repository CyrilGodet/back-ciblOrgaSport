package com.glop.cibl_orga_sport.service;

import com.glop.cibl_orga_sport.data.Epreuve;
import com.glop.cibl_orga_sport.data.Periode;
import com.glop.cibl_orga_sport.data.EtapeEpreuve;
import com.glop.cibl_orga_sport.data.enumType.EtapeEpreuveEnum;
import com.glop.cibl_orga_sport.repository.PhaseRepository;
import com.glop.cibl_orga_sport.service.impl.PhaseServiceImpl;

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
class PhaseServiceImplTest {

    @Mock
    private PhaseRepository phaseRepository;

    @InjectMocks
    private PhaseServiceImpl phaseService;

    @Test
    void testCreatePhase() {
        Epreuve epreuve = new Epreuve("100m nage libre");
        epreuve.setIdEpreuve(1L);

        Periode periode = new Periode(Date.valueOf("2026-01-05"), Date.valueOf("2026-01-06"));
        EtapeEpreuve phase = new EtapeEpreuve(epreuve, periode, null, EtapeEpreuveEnum.FINALE);
        phase.setIdEtapeEpreuve(1L);

        when(phaseRepository.save(any(EtapeEpreuve.class))).thenReturn(phase);

        EtapeEpreuve result = phaseService.createPhase(epreuve, Date.valueOf("2026-01-05"), 
                Date.valueOf("2026-01-06"), EtapeEpreuveEnum.FINALE, null);

        assertNotNull(result);
        assertEquals(EtapeEpreuveEnum.FINALE, result.getEtapeEpreuveEnum());
        assertEquals(epreuve, result.getEpreuve());
    }

    @Test
    void testGetAllPhases() {
        Periode periode1 = new Periode(Date.valueOf("2026-01-05"), Date.valueOf("2026-01-06"));
        EtapeEpreuve phase1 = new EtapeEpreuve(null, periode1, null, EtapeEpreuveEnum.FINALE);
        phase1.setIdEtapeEpreuve(1L);

        Periode periode2 = new Periode(Date.valueOf("2026-01-03"), Date.valueOf("2026-01-04"));
        EtapeEpreuve phase2 = new EtapeEpreuve(null, periode2, null, EtapeEpreuveEnum.DEMI_FINALE);
        phase2.setIdEtapeEpreuve(2L);

        when(phaseRepository.findAll()).thenReturn(Arrays.asList(phase1, phase2));

        List<EtapeEpreuve> result = phaseService.getAllPhases();

        assertEquals(2, result.size());
        assertEquals(EtapeEpreuveEnum.FINALE, result.get(0).getEtapeEpreuveEnum());
        assertEquals(EtapeEpreuveEnum.DEMI_FINALE, result.get(1).getEtapeEpreuveEnum());
    }

    @Test
    void testGetPhase() {
        Periode periode = new Periode(Date.valueOf("2026-01-05"), Date.valueOf("2026-01-06"));
        EtapeEpreuve phase = new EtapeEpreuve(null, periode, null, EtapeEpreuveEnum.FINALE);
        phase.setIdEtapeEpreuve(1L);

        when(phaseRepository.findById(1L)).thenReturn(Optional.of(phase));

        Optional<EtapeEpreuve> result = phaseService.getPhase(1L);

        assertTrue(result.isPresent());
        assertEquals(EtapeEpreuveEnum.FINALE, result.get().getEtapeEpreuveEnum());
    }

    @Test
    void testUpdatePhase() {
        Epreuve epreuve = new Epreuve("100m nage libre");
        epreuve.setIdEpreuve(1L);

        Periode periode = new Periode(Date.valueOf("2026-01-05"), Date.valueOf("2026-01-06"));
        EtapeEpreuve existingPhase = new EtapeEpreuve(null, periode, null, EtapeEpreuveEnum.FINALE);
        existingPhase.setIdEtapeEpreuve(1L);

        when(phaseRepository.findById(1L)).thenReturn(Optional.of(existingPhase));
        when(phaseRepository.save(any(EtapeEpreuve.class))).thenReturn(existingPhase);

        EtapeEpreuve result = phaseService.updatePhase(1L, epreuve, Date.valueOf("2026-01-07"),
                Date.valueOf("2026-01-08"), EtapeEpreuveEnum.FINALE, null);

        assertNotNull(result);
        assertEquals(EtapeEpreuveEnum.FINALE, result.getEtapeEpreuveEnum());
        assertNotNull(result.getPeriode());
        assertEquals(Date.valueOf("2026-01-07"), result.getPeriode().getDateDebut());
        assertEquals(Date.valueOf("2026-01-08"), result.getPeriode().getDateFin());
        assertEquals(epreuve, result.getEpreuve());
    }

    @Test
    void testUpdatePhase_NotFound() {
        when(phaseRepository.findById(999L)).thenReturn(Optional.empty());

        EtapeEpreuve result = phaseService.updatePhase(999L, null, null, null, null, null);

        assertNull(result);
    }

    @Test
    void testDeletePhase() {
        Periode periode = new Periode(Date.valueOf("2026-01-05"), Date.valueOf("2026-01-06"));
        EtapeEpreuve phase = new EtapeEpreuve(null, periode, null, EtapeEpreuveEnum.FINALE);
        phase.setIdEtapeEpreuve(1L);

        when(phaseRepository.findById(1L)).thenReturn(Optional.of(phase));

        boolean result = phaseService.deletePhase(1L);

        assertTrue(result);
    }

    @Test
    void testDeletePhase_NotFound() {
        when(phaseRepository.findById(999L)).thenReturn(Optional.empty());

        boolean result = phaseService.deletePhase(999L);

        assertFalse(result);
    }

    @Test
    void testGetPhasesByCompetitionId() {
        when(phaseRepository.findByEpreuveCompetitionIdCompetition(12L))
                .thenReturn(List.of(new EtapeEpreuve(), new EtapeEpreuve()));

        List<EtapeEpreuve> result = phaseService.getPhasesByCompetitionId(12L);

        assertEquals(2, result.size());
    }
}
