package com.glop.cibl_orga_sport.mapper;

import com.glop.cibl_orga_sport.data.Lieu;
import com.glop.cibl_orga_sport.dto.LieuDTO;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LieuMapperTest {

    private Lieu createLieu(String nom, String ville, String adresse, Long id) {
        Lieu lieu = new Lieu(nom, ville, adresse);
        lieu.setIdLieu(id);
        return lieu;
    }

    private LieuDTO createLieuDTO(String nom, String ville, String adresse, Long id) {
        return new LieuDTO(id, nom, ville, adresse);
    }

    @Test
    void testToDTO() {
        Lieu lieu = createLieu("Centre Aquatique Olympique Métropole du Grand Saint-Denis", "Saint-Denis", "361-363, Av. du Président Wilson", 1L);

        LieuDTO dto = LieuMapper.toDTO(lieu);

        assertNotNull(dto);
        assertEquals(1L, dto.getIdLieu());
        assertEquals("Centre Aquatique Olympique Métropole du Grand Saint-Denis", dto.getNom());
        assertEquals("Saint-Denis", dto.getVille());
        assertEquals("361-363, Av. du Président Wilson", dto.getAdresse());
    }

    @Test
    void testToDTO_Null() {
        assertNull(LieuMapper.toDTO(null));
    }

    @Test
    void testToEntity() {
        LieuDTO dto = createLieuDTO("Centre Aquatique Olympique Métropole du Grand Saint-Denis", "Saint-Denis", "361-363, Av. du Président Wilson", 1L);

        Lieu lieu = LieuMapper.toEntity(dto);

        assertNotNull(lieu);
        assertEquals("Centre Aquatique Olympique Métropole du Grand Saint-Denis", lieu.getNomLieu());
        assertEquals("Saint-Denis", lieu.getVille());
        assertEquals("361-363, Av. du Président Wilson", lieu.getAdresse());
    }

    @Test
    void testToEntity_Null() {
        assertNull(LieuMapper.toEntity(null));
    }
}
