package com.glop.cibl_orga_sport.service;

import com.glop.cibl_orga_sport.data.Lieu;
import com.glop.cibl_orga_sport.data.enumType.LieuCategorieEnum;
import com.glop.cibl_orga_sport.dto.LieuDTO;
import com.glop.cibl_orga_sport.repository.LieuRepository;
import com.glop.cibl_orga_sport.service.impl.LieuServiceImpl;

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
class LieuServiceImplTest {

    @Mock
    private LieuRepository lieuRepository;

    @InjectMocks
    private LieuServiceImpl lieuService;

    @Test
    void testCreateLieu() {
        Lieu lieu = new Lieu("Centre Aquatique Olympique Métropole du Grand Saint-Denis", "Saint-Denis", "361-363, Av. du Président Wilson", null);
        lieu.setIdLieu(1L);

        when(lieuRepository.save(any(Lieu.class))).thenReturn(lieu);

        LieuDTO dto = new LieuDTO();
        dto.setNomLieu("Centre Aquatique Olympique Métropole du Grand Saint-Denis");
        dto.setVille("Saint-Denis");
        dto.setAdresse("361-363, Av. du Président Wilson");

        Lieu result = lieuService.createLieu(dto);

        assertNotNull(result);
        assertEquals("Centre Aquatique Olympique Métropole du Grand Saint-Denis", result.getNomLieu());
        assertEquals("Saint-Denis", result.getVille());
        assertEquals("361-363, Av. du Président Wilson", result.getAdresse());
    }

    @Test
    void testGetAllLieux() {
        Lieu lieu1 = new Lieu("Centre Aquatique Olympique Métropole du Grand Saint-Denis", "Saint-Denis", "361-363, Av. du Président Wilson", null);
        lieu1.setIdLieu(1L);

        Lieu lieu2 = new Lieu("Stade Nautique", "Lyon", "456 Avenue Eau", null);
        lieu2.setIdLieu(2L);

        when(lieuRepository.findAll()).thenReturn(Arrays.asList(lieu1, lieu2));

        List<Lieu> result = lieuService.getAllLieux();

        assertEquals(2, result.size());
        assertEquals("Centre Aquatique Olympique Métropole du Grand Saint-Denis", result.get(0).getNomLieu());
        assertEquals("Stade Nautique", result.get(1).getNomLieu());
    }

    @Test
    void testGetLieu() {
        Lieu lieu = new Lieu("Centre Aquatique Olympique Métropole du Grand Saint-Denis", "Saint-Denis", "361-363, Av. du Président Wilson", null);
        lieu.setIdLieu(1L);

        when(lieuRepository.findById(1L)).thenReturn(Optional.of(lieu));

        Optional<Lieu> result = lieuService.getLieu(1L);

        assertTrue(result.isPresent());
        assertEquals("Centre Aquatique Olympique Métropole du Grand Saint-Denis", result.get().getNomLieu());
    }

    @Test
    void testUpdateLieu() {
        Lieu existingLieu = new Lieu("Centre Aquatique Olympique Métropole du Grand Saint-Denis", "Saint-Denis", "361-363, Av. du Président Wilson", null);
        existingLieu.setIdLieu(1L);

        when(lieuRepository.findById(1L)).thenReturn(Optional.of(existingLieu));
        when(lieuRepository.save(any(Lieu.class))).thenReturn(existingLieu);

        Lieu result = lieuService.updateLieu(1L, "Piscine Modifiée", "Marseille", "789 Boulevard Natation", null);

        assertNotNull(result);
        assertEquals("Piscine Modifiée", result.getNomLieu());
        assertEquals("Marseille", result.getVille());
        assertEquals("789 Boulevard Natation", result.getAdresse());
    }

    @Test
    void testUpdateLieu_NotFound() {
        when(lieuRepository.findById(999L)).thenReturn(Optional.empty());

        Lieu result = lieuService.updateLieu(999L, "Piscine", "Saint-Denis", "123 Rue", null);

        assertNull(result);
    }

    @Test
    void testDeleteLieu() {
        Lieu lieu = new Lieu("Centre Aquatique Olympique Métropole du Grand Saint-Denis", "Saint-Denis", "361-363, Av. du Président Wilson", null);
        lieu.setIdLieu(1L);

        when(lieuRepository.findById(1L)).thenReturn(Optional.of(lieu));

        boolean result = lieuService.deleteLieu(1L);

        assertTrue(result);
    }

    @Test
    void testDeleteLieu_NotFound() {
        when(lieuRepository.findById(999L)).thenReturn(Optional.empty());

        boolean result = lieuService.deleteLieu(999L);

        assertFalse(result);
    }

    @Test
    void testSearchLieux() {
        when(lieuRepository.findByNomLieuContainingIgnoreCase("aquatique"))
                .thenReturn(List.of(new Lieu("Centre", "Paris", "Adr", LieuCategorieEnum.EVENEMENT)));

        List<Lieu> result = lieuService.searchLieux("aquatique");

        assertEquals(1, result.size());
    }

    @Test
    void testGetLieuxForAffectations() {
        when(lieuRepository.findByCategorieOrderByNomLieuAsc(LieuCategorieEnum.EVENEMENT))
                .thenReturn(List.of(new Lieu("Site", "Paris", "Adr", LieuCategorieEnum.EVENEMENT)));

        List<Lieu> result = lieuService.getLieuxForAffectations();

        assertEquals(1, result.size());
    }
}
