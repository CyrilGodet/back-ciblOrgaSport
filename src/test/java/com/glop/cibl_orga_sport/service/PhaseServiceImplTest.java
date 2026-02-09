package com.glop.cibl_orga_sport.service;

import com.glop.cibl_orga_sport.data.Epreuve;
import com.glop.cibl_orga_sport.data.Lieu;
import com.glop.cibl_orga_sport.data.EtapeEpreuve;
import com.glop.cibl_orga_sport.repository.PhaseRepository;
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

        Lieu lieu = new Lieu("Centre Aquatique Olympique Métropole du Grand Saint-Denis", "Saint-Denis",
                "361-363, Av. du Président Wilson");
        lieu.setIdLieu(1L);

        EtapeEpreuve phase = new EtapeEpreuve("Finale", Date.valueOf("2026-01-05"), Date.valueOf("2026-01-06"), epreuve, lieu);
        phase.setIdEtapeEpreuve(1L);

        when(phaseRepository.save(any(EtapeEpreuve.class))).thenReturn(phase);

        EtapeEpreuve result = phaseService.createPhase("Finale", Date.valueOf("2026-01-05"), Date.valueOf("2026-01-06"),
                epreuve, lieu);

        assertNotNull(result);
        assertEquals("Finale", result.getNomPhase());
        assertEquals(epreuve, result.getEpreuve());
        assertEquals(lieu, result.getLieu());
    }

    @Test
    void testGetAllPhases() {
        EtapeEpreuve phase1 = new EtapeEpreuve("Finale", Date.valueOf("2026-01-05"), Date.valueOf("2026-01-06"), null, null);
        phase1.setIdEtapeEpreuve(1L);

        EtapeEpreuve phase2 = new EtapeEpreuve("Demi-finale", Date.valueOf("2026-01-03"), Date.valueOf("2026-01-04"), null, null);
        phase2.setIdEtapeEpreuve(2L);

        when(phaseRepository.findAll()).thenReturn(Arrays.asList(phase1, phase2));

        List<EtapeEpreuve> result = phaseService.getAllPhases();

        assertEquals(2, result.size());
        assertEquals("Finale", result.get(0).getNomPhase());
        assertEquals("Demi-finale", result.get(1).getNomPhase());
    }

    @Test
    void testGetPhase() {
        EtapeEpreuve phase = new EtapeEpreuve("Finale", Date.valueOf("2026-01-05"), Date.valueOf("2026-01-06"), null, null);
        phase.setIdEtapeEpreuve(1L);

        when(phaseRepository.findById(1L)).thenReturn(Optional.of(phase));

        Optional<EtapeEpreuve> result = phaseService.getPhase(1L);

        assertTrue(result.isPresent());
        assertEquals("Finale", result.get().getNomPhase());
    }

    @Test
    void testUpdatePhase() {
        Epreuve epreuve = new Epreuve("100m nage libre");
        epreuve.setIdEpreuve(1L);

        Lieu lieu = new Lieu("Centre Aquatique Olympique Métropole du Grand Saint-Denis", "Saint-Denis",
                "361-363, Av. du Président Wilson");
        lieu.setIdLieu(1L);

        EtapeEpreuve existingPhase = new EtapeEpreuve("Finale", Date.valueOf("2026-01-05"), Date.valueOf("2026-01-06"), null, null);
        existingPhase.setIdEtapeEpreuve(1L);

        when(phaseRepository.findById(1L)).thenReturn(Optional.of(existingPhase));
        when(phaseRepository.save(any(EtapeEpreuve.class))).thenReturn(existingPhase);

        EtapeEpreuve result = phaseService.updatePhase(1L, "Finale modifiée", Date.valueOf("2026-01-07"),
                Date.valueOf("2026-01-08"), epreuve, lieu);

        assertNotNull(result);
        assertEquals("Finale modifiée", result.getNomPhase());
        assertEquals(Date.valueOf("2026-01-07"), result.getDateDebut());
        assertEquals(Date.valueOf("2026-01-08"), result.getDateFin());
        assertEquals(epreuve, result.getEpreuve());
        assertEquals(lieu, result.getLieu());
    }

    @Test
    void testUpdatePhase_NotFound() {
        when(phaseRepository.findById(999L)).thenReturn(Optional.empty());

        EtapeEpreuve result = phaseService.updatePhase(999L, "Finale", null, null, null, null);

        assertNull(result);
    }

    @Test
    void testDeletePhase() {
        EtapeEpreuve phase = new EtapeEpreuve("Finale", null, null);
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
}
