package com.glop.cibl_orga_sport.mapper;

import com.glop.cibl_orga_sport.data.Equipe;
import com.glop.cibl_orga_sport.data.Competition;
import com.glop.cibl_orga_sport.data.Periode;
import com.glop.cibl_orga_sport.dto.EquipeDTO;
import org.junit.jupiter.api.Test;

import java.sql.Date;

import static org.junit.jupiter.api.Assertions.*;

class EquipeMapperTest {

    @Test
    void testToDTO() {
        Periode periode = new Periode(Date.valueOf("2026-01-01"), Date.valueOf("2026-01-10"));
        Competition competition = new Competition("Championnats du monde", null, periode, null, null, null, null, null);
        competition.setIdCompetition(1L);

        Equipe equipe = new Equipe("Équipe de France");
        equipe.setIdEquipe(1L);
        equipe.setCompetition(competition);

        EquipeDTO dto = EquipeMapper.toDTO(equipe);

        assertNotNull(dto);
        assertEquals(1L, dto.getIdEquipe());
        assertEquals("Équipe de France", dto.getNomEquipe());
        assertEquals(1L, dto.getCompetitionId());
    }

    @Test
    void testToDTO_WithNullCompetition() {
        Equipe equipe = new Equipe("Équipe de France");
        equipe.setIdEquipe(1L);

        EquipeDTO dto = EquipeMapper.toDTO(equipe);

        assertNotNull(dto);
        assertEquals(1L, dto.getIdEquipe());
        assertEquals("Équipe de France", dto.getNomEquipe());
        assertNull(dto.getCompetitionId());
    }

    @Test
    void testToDTO_Null() {
        assertNull(EquipeMapper.toDTO(null));
    }

    @Test
    void testToEntity() {
        EquipeDTO dto = new EquipeDTO(1L, "Équipe de France", 1L);

        Equipe equipe = EquipeMapper.toEntity(dto);

        assertNotNull(equipe);
        assertEquals(1L, equipe.getIdEquipe());
        assertEquals("Équipe de France", equipe.getNomEquipe());
    }

    @Test
    void testToEntity_Null() {
        assertNull(EquipeMapper.toEntity(null));
    }
}
