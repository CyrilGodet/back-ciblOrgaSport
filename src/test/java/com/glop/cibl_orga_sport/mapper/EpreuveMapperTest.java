package com.glop.cibl_orga_sport.mapper;

import com.glop.cibl_orga_sport.data.Competition;
import com.glop.cibl_orga_sport.data.Epreuve;
import com.glop.cibl_orga_sport.dto.CompetitionDTO;
import com.glop.cibl_orga_sport.dto.EpreuveDTO;
import org.junit.jupiter.api.Test;

import java.sql.Date;

import static org.junit.jupiter.api.Assertions.*;

class EpreuveMapperTest {

    private Competition createCompetition(String name, Long id) {
        Competition competition = new Competition(name, Date.valueOf("2026-01-01"), Date.valueOf("2026-01-10"));
        competition.setIdCompetition(id);
        return competition;
    }

    private Epreuve createEpreuve(String name, Long id, Competition competition) {
        Epreuve epreuve = new Epreuve(name);
        epreuve.setIdEpreuve(id);
        epreuve.setCompetition(competition);
        return epreuve;
    }

    private CompetitionDTO createCompetitionDTO(String name, Long id) {
        return new CompetitionDTO(id, name, Date.valueOf("2026-01-01"), Date.valueOf("2026-01-10"));
    }

    private EpreuveDTO createEpreuveDTO(String name, Long id, CompetitionDTO competitionDTO) {
        return new EpreuveDTO(id, name, competitionDTO);
    }

    @Test
    void testToDTO() {
        Competition competition = createCompetition("Championnats du monde de natation", 1L);
        Epreuve epreuve = createEpreuve("100m nage libre", 2L, competition);

        EpreuveDTO dto = EpreuveMapper.toDTO(epreuve);

        assertNotNull(dto);
        assertEquals(2L, dto.getIdEpreuve());
        assertEquals("100m nage libre", dto.getNomEpreuve());
        assertNotNull(dto.getCompetition());
        assertEquals("Championnats du monde de natation", dto.getCompetition().getNameCompetition());
    }

    @Test
    void testToDTO_Null() {
        assertNull(EpreuveMapper.toDTO(null));
    }

    @Test
    void testToEntity() {
        CompetitionDTO competitionDTO = createCompetitionDTO("Championnats du monde de natation", 1L);
        EpreuveDTO dto = createEpreuveDTO("100m nage libre", 2L, competitionDTO);

        Epreuve epreuve = EpreuveMapper.toEntity(dto);

        assertNotNull(epreuve);
        assertEquals("100m nage libre", epreuve.getNomEpreuve());
    }

    @Test
    void testToEntity_Null() {
        assertNull(EpreuveMapper.toEntity(null));
    }
}
