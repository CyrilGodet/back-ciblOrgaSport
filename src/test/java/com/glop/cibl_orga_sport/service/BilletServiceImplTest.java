package com.glop.cibl_orga_sport.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import com.glop.cibl_orga_sport.data.Billet;
import com.glop.cibl_orga_sport.data.Epreuve;
import com.glop.cibl_orga_sport.data.Visiteur;
import com.glop.cibl_orga_sport.dto.BilletDTO;
import com.glop.cibl_orga_sport.repository.BilletRepository;
import com.glop.cibl_orga_sport.repository.EpreuveRepository;
import com.glop.cibl_orga_sport.repository.VisiteurRepository;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class BilletServiceImplTest {

    @Mock
    private BilletRepository billetRepository;
    @Mock
    private VisiteurRepository visiteurRepository;
    @Mock
    private EpreuveRepository epreuveRepository;

    @InjectMocks
    private BilletServiceImpl billetService;

    @Test
    void createBilletShouldReturnNullWhenDtoInvalid() {
        assertNull(billetService.createBillet(null));
    }

    @Test
    void createBilletShouldReturnNullWhenNumeroAlreadyExists() {
        BilletDTO dto = new BilletDTO();
        dto.setVisiteurId(1L);
        dto.setEpreuveId(2L);
        dto.setNumeroBillet("B-001");
        dto.setCategorie("STANDARD");

        when(billetRepository.existsByNumeroBillet("B-001")).thenReturn(true);

        assertNull(billetService.createBillet(dto));
    }

    @Test
    void createBilletShouldSaveWhenValid() {
        BilletDTO dto = new BilletDTO();
        dto.setVisiteurId(1L);
        dto.setEpreuveId(2L);
        dto.setNumeroBillet("B-002");
        dto.setCategorie("VIP");

        when(billetRepository.existsByNumeroBillet("B-002")).thenReturn(false);
        when(visiteurRepository.findById(1L)).thenReturn(Optional.of(new Visiteur()));
        when(epreuveRepository.findById(2L)).thenReturn(Optional.of(new Epreuve("Sprint")));
        when(billetRepository.save(any(Billet.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Billet result = billetService.createBillet(dto);

        assertNotNull(result);
        assertEquals("B-002", result.getNumeroBillet());
        assertNotNull(result.getDateAchat());
    }

    @Test
    void updateBilletShouldReturnNullWhenBilletNotFound() {
        when(billetRepository.findById(999L)).thenReturn(Optional.empty());

        assertNull(billetService.updateBillet(999L, new BilletDTO()));
    }

    @Test
    void deleteBilletShouldDeleteWhenFound() {
        when(billetRepository.findById(10L)).thenReturn(Optional.of(new Billet()));

        boolean deleted = billetService.deleteBillet(10L);

        assertTrue(deleted);
        verify(billetRepository).deleteById(10L);
    }

    @Test
    void getAllBilletsShouldDelegateToRepository() {
        when(billetRepository.findAll()).thenReturn(List.of(new Billet(), new Billet()));

        List<Billet> result = billetService.getAllBillets();

        assertEquals(2, result.size());
    }

    @Test
    void getBilletShouldDelegateToRepository() {
        Billet billet = new Billet();
        when(billetRepository.findById(15L)).thenReturn(Optional.of(billet));

        Optional<Billet> result = billetService.getBillet(15L);

        assertTrue(result.isPresent());
    }

    @Test
    void getBilletsByVisiteurShouldDelegateToRepository() {
        when(billetRepository.findByVisiteurIdUtilisateur(2L)).thenReturn(List.of(new Billet()));

        List<Billet> result = billetService.getBilletsByVisiteur(2L);

        assertEquals(1, result.size());
    }
}
