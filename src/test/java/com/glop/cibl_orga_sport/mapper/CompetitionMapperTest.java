package com.glop.cibl_orga_sport.mapper;

import com.glop.cibl_orga_sport.data.Competition;
import com.glop.cibl_orga_sport.dto.CompetitionDTO;
import org.junit.jupiter.api.Test;

import java.sql.Date;

import static org.junit.jupiter.api.Assertions.*;

class CompetitionMapperTest {

    private Competition createCompetition(String name, Long id) {
        Competition competition = new Competition(name, Date.valueOf("2026-01-01"), Date.valueOf("2026-01-10"));
        competition.setIdCompetition(id);
        return competition;
    }

    private CompetitionDTO createCompetitionDTO(String name, Long id) {
        return new CompetitionDTO(id, name, Date.valueOf("2026-01-01"), Date.valueOf("2026-01-10"));
    }

    @Test
    void testToDTO() {
        Competition competition = createCompetition("Championnats du monde de natation", 1L);

        CompetitionDTO dto = CompetitionMapper.toDTO(competition);

        assertNotNull(dto);
        assertEquals(1L, dto.getIdCompetition());
        assertEquals("Championnats du monde de natation", dto.getNameCompetition());
        assertEquals(Date.valueOf("2026-01-01"), dto.getDateDebut());
        assertEquals(Date.valueOf("2026-01-10"), dto.getDateFin());
    }

    @Test
    void testToDTO_Null() {
        assertNull(CompetitionMapper.toDTO(null));
    }

    @Test
    void testToEntity() {
        CompetitionDTO dto = createCompetitionDTO("Championnats du monde de natation", 1L);

        Competition competition = CompetitionMapper.toEntity(dto);

        assertNotNull(competition);
        assertEquals("Championnats du monde de natation", competition.getNameCompetition());
        assertEquals(Date.valueOf("2026-01-01"), competition.getDateDebut());
        assertEquals(Date.valueOf("2026-01-10"), competition.getDateFin());
    }

    @Test
    void testToEntity_Null() {
        assertNull(CompetitionMapper.toEntity(null));
    }
}
