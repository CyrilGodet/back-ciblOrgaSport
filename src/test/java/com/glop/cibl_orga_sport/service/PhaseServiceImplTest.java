package com.glop.cibl_orga_sport.service;

import com.glop.cibl_orga_sport.data.Epreuve;
import com.glop.cibl_orga_sport.data.Lieu;
import com.glop.cibl_orga_sport.data.Phase;
import com.glop.cibl_orga_sport.repository.PhaseRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

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

        Lieu lieu = new Lieu("Centre Aquatique Olympique Métropole du Grand Saint-Denis", "Saint-Denis", "361-363, Av. du Président Wilson");
        lieu.setIdLieu(1L);

        Phase phase = new Phase("Finale", epreuve, lieu);
        phase.setIdPhase(1L);

        when(phaseRepository.save(any(Phase.class))).thenReturn(phase);

        Phase result = phaseService.createPhase("Finale", epreuve, lieu);

        assertNotNull(result);
        assertEquals("Finale", result.getNomPhase());
        assertEquals(epreuve, result.getEpreuve());
        assertEquals(lieu, result.getLieu());
    }

    @Test
    void testGetAllPhases() {
        Phase phase1 = new Phase("Finale", null, null);
        phase1.setIdPhase(1L);

        Phase phase2 = new Phase("Demi-finale", null, null);
        phase2.setIdPhase(2L);

        when(phaseRepository.findAll()).thenReturn(Arrays.asList(phase1, phase2));

        List<Phase> result = phaseService.getAllPhases();

        assertEquals(2, result.size());
        assertEquals("Finale", result.get(0).getNomPhase());
        assertEquals("Demi-finale", result.get(1).getNomPhase());
    }

    @Test
    void testGetPhase() {
        Phase phase = new Phase("Finale", null, null);
        phase.setIdPhase(1L);

        when(phaseRepository.findById(1L)).thenReturn(Optional.of(phase));

        Optional<Phase> result = phaseService.getPhase(1L);

        assertTrue(result.isPresent());
        assertEquals("Finale", result.get().getNomPhase());
    }

    @Test
    void testUpdatePhase() {
        Epreuve epreuve = new Epreuve("100m nage libre");
        epreuve.setIdEpreuve(1L);

        Lieu lieu = new Lieu("Centre Aquatique Olympique Métropole du Grand Saint-Denis", "Saint-Denis", "361-363, Av. du Président Wilson");
        lieu.setIdLieu(1L);

        Phase existingPhase = new Phase("Finale", null, null);
        existingPhase.setIdPhase(1L);

        when(phaseRepository.findById(1L)).thenReturn(Optional.of(existingPhase));
        when(phaseRepository.save(any(Phase.class))).thenReturn(existingPhase);

        Phase result = phaseService.updatePhase(1L, "Finale modifiée", epreuve, lieu);

        assertNotNull(result);
        assertEquals("Finale modifiée", result.getNomPhase());
        assertEquals(epreuve, result.getEpreuve());
        assertEquals(lieu, result.getLieu());
    }

    @Test
    void testUpdatePhase_NotFound() {
        when(phaseRepository.findById(999L)).thenReturn(Optional.empty());

        Phase result = phaseService.updatePhase(999L, "Finale", null, null);

        assertNull(result);
    }

    @Test
    void testDeletePhase() {
        Phase phase = new Phase("Finale", null, null);
        phase.setIdPhase(1L);

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
