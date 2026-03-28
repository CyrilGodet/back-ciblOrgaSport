package com.glop.cibl_orga_sport.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import com.glop.cibl_orga_sport.data.AffectationVolontaire;
import com.glop.cibl_orga_sport.data.Epreuve;
import com.glop.cibl_orga_sport.data.Lieu;
import com.glop.cibl_orga_sport.data.Volontaire;
import com.glop.cibl_orga_sport.dto.AffectationVolontaireDTO;
import com.glop.cibl_orga_sport.repository.AffectationVolontaireRepository;
import com.glop.cibl_orga_sport.repository.EpreuveRepository;
import com.glop.cibl_orga_sport.repository.LieuRepository;
import com.glop.cibl_orga_sport.repository.VolontaireRepository;
import com.glop.cibl_orga_sport.service.impl.AffectationVolontaireServiceImpl;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class AffectationVolontaireServiceImplTest {

    @Mock
    private AffectationVolontaireRepository affectationRepository;
    @Mock
    private VolontaireRepository volontaireRepository;
    @Mock
    private EpreuveRepository epreuveRepository;
    @Mock
    private LieuRepository lieuRepository;

    @InjectMocks
    private AffectationVolontaireServiceImpl service;

    @Test
    void getAffectationsByVolontaireShouldReturnEmptyWhenVolontaireNotFound() {
        when(volontaireRepository.findById(1L)).thenReturn(Optional.empty());

        List<AffectationVolontaireDTO> result = service.getAffectationsByVolontaire(1L);

        assertTrue(result.isEmpty());
    }

    @Test
    void getAllAffectationsShouldReturnMappedList() {
        when(affectationRepository.findAll()).thenReturn(List.of(new AffectationVolontaire()));

        List<AffectationVolontaireDTO> result = service.getAllAffectations();

        assertEquals(1, result.size());
    }

    @Test
    void getAffectationByIdShouldReturnOptionalWhenFound() {
        when(affectationRepository.findById(5L)).thenReturn(Optional.of(new AffectationVolontaire()));

        Optional<AffectationVolontaireDTO> result = service.getAffectationById(5L);

        assertTrue(result.isPresent());
    }

    @Test
    void getAffectationsByCompetitionShouldReturnMappedList() {
        when(affectationRepository.findByCompetitionId(9L)).thenReturn(List.of(new AffectationVolontaire()));

        List<AffectationVolontaireDTO> result = service.getAffectationsByCompetition(9L);

        assertEquals(1, result.size());
    }

    @Test
    void getProgrammeVolontaireShouldReturnEmptyWhenVolontaireMissing() {
        when(volontaireRepository.findById(10L)).thenReturn(Optional.empty());

        List<AffectationVolontaireDTO> result = service.getProgrammeVolontaire(10L, LocalDate.now());

        assertTrue(result.isEmpty());
    }

    @Test
    void getProgrammeVolontaireShouldReturnMappedListWhenFound() {
        Volontaire volontaire = new Volontaire();
        LocalDate date = LocalDate.now();
        when(volontaireRepository.findById(11L)).thenReturn(Optional.of(volontaire));
        when(affectationRepository.findByVolontaireAndDateAffectation(volontaire, date))
                .thenReturn(List.of(new AffectationVolontaire()));

        List<AffectationVolontaireDTO> result = service.getProgrammeVolontaire(11L, date);

        assertEquals(1, result.size());
    }

    @Test
    void getProgrammeVolontaireFuturShouldReturnEmptyWhenVolontaireMissing() {
        when(volontaireRepository.findById(12L)).thenReturn(Optional.empty());

        List<AffectationVolontaireDTO> result = service.getProgrammeVolontaireFutur(12L);

        assertTrue(result.isEmpty());
    }

    @Test
    void getProgrammeVolontaireFuturShouldReturnMappedListWhenFound() {
        Volontaire volontaire = new Volontaire();
        when(volontaireRepository.findById(13L)).thenReturn(Optional.of(volontaire));
        when(affectationRepository.findByVolontaireAndDateAffectationGreaterThanEqual(eq(volontaire), any(LocalDate.class)))
                .thenReturn(List.of(new AffectationVolontaire()));

        List<AffectationVolontaireDTO> result = service.getProgrammeVolontaireFutur(13L);

        assertEquals(1, result.size());
    }

    @Test
    void createAffectationShouldSave() {
        AffectationVolontaireDTO dto = new AffectationVolontaireDTO();
        dto.setIdVolontaire(1L);
        dto.setIdEpreuve(2L);
        dto.setIdLieuRdv(3L);

        when(volontaireRepository.findById(1L)).thenReturn(Optional.of(new Volontaire()));
        when(epreuveRepository.findById(2L)).thenReturn(Optional.of(new Epreuve("Epreuve1")));
        when(lieuRepository.findById(3L)).thenReturn(Optional.of(new Lieu()));
        when(affectationRepository.save(any(AffectationVolontaire.class))).thenAnswer(invocation -> invocation.getArgument(0));

        AffectationVolontaireDTO result = service.createAffectation(dto);

        assertNotNull(result);
    }

    @Test
    void updateAffectationShouldReturnNullWhenMissing() {
        when(affectationRepository.findById(10L)).thenReturn(Optional.empty());

        assertNull(service.updateAffectation(10L, new AffectationVolontaireDTO()));
    }

    @Test
    void updateAffectationShouldSaveWhenFound() {
        AffectationVolontaire existing = new AffectationVolontaire();
        when(affectationRepository.findById(14L)).thenReturn(Optional.of(existing));
        when(affectationRepository.save(existing)).thenReturn(existing);

        AffectationVolontaireDTO dto = new AffectationVolontaireDTO();
        dto.setIdLieuRdv(3L);
        dto.setIdEpreuve(2L);
        when(lieuRepository.findById(3L)).thenReturn(Optional.of(new Lieu()));
        when(epreuveRepository.findById(2L)).thenReturn(Optional.of(new Epreuve("Epreuve2")));

        AffectationVolontaireDTO result = service.updateAffectation(14L, dto);

        assertNotNull(result);
        verify(affectationRepository).save(existing);
    }

    @Test
    void deleteAffectationShouldDelegate() {
        service.deleteAffectation(77L);

        verify(affectationRepository).deleteById(77L);
    }
}
