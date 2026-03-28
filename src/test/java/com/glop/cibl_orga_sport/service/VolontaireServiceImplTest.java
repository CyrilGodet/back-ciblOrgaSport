package com.glop.cibl_orga_sport.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import com.glop.cibl_orga_sport.data.Volontaire;
import com.glop.cibl_orga_sport.dto.VolontaireDTO;
import com.glop.cibl_orga_sport.repository.VolontaireRepository;
import com.glop.cibl_orga_sport.service.impl.VolontaireServiceImpl;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class VolontaireServiceImplTest {

    @Mock
    private VolontaireRepository volontaireRepository;

    @InjectMocks
    private VolontaireServiceImpl service;

    @Test
    void getVolontaireByEmailShouldReturnMappedDto() {
        Volontaire volontaire = new Volontaire();
        volontaire.setEmail("v@test.com");
        when(volontaireRepository.findByEmail("v@test.com")).thenReturn(Optional.of(volontaire));

        Optional<VolontaireDTO> result = service.getVolontaireByEmail("v@test.com");

        assertTrue(result.isPresent());
        assertEquals("v@test.com", result.get().getEmail());
    }

    @Test
    void getAllVolontairesShouldReturnMappedList() {
        when(volontaireRepository.findAll()).thenReturn(List.of(new Volontaire()));

        List<VolontaireDTO> result = service.getAllVolontaires();

        assertEquals(1, result.size());
    }

    @Test
    void getVolontaireByIdShouldReturnOptionalWhenFound() {
        when(volontaireRepository.findById(5L)).thenReturn(Optional.of(new Volontaire()));

        Optional<VolontaireDTO> result = service.getVolontaireById(5L);

        assertTrue(result.isPresent());
    }

    @Test
    void createVolontaireShouldSave() {
        Volontaire saved = new Volontaire();
        saved.setNom("Nom");
        when(volontaireRepository.save(any(Volontaire.class))).thenReturn(saved);

        VolontaireDTO dto = new VolontaireDTO();
        dto.setNom("Nom");

        VolontaireDTO result = service.createVolontaire(dto);

        assertNotNull(result);
        assertEquals("Nom", result.getNom());
    }

    @Test
    void updateVolontaireShouldReturnNullWhenMissing() {
        when(volontaireRepository.findById(20L)).thenReturn(Optional.empty());

        assertNull(service.updateVolontaire(20L, new VolontaireDTO()));
    }

    @Test
    void deleteVolontaireShouldDelegate() {
        service.deleteVolontaire(30L);

        verify(volontaireRepository).deleteById(30L);
    }
}
