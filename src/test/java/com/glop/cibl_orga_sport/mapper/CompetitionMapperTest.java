package com.glop.cibl_orga_sport.mapper;

import com.glop.cibl_orga_sport.data.Competition;
import com.glop.cibl_orga_sport.data.enumType.CompetitionStatusEnum;
import com.glop.cibl_orga_sport.data.enumType.CompetitionGenreEnum;
import com.glop.cibl_orga_sport.data.enumType.CompetitionSportEnum;
import com.glop.cibl_orga_sport.dto.CompetitionDTO;
import org.junit.jupiter.api.Test;

import java.sql.Date;

import static org.junit.jupiter.api.Assertions.*;

class CompetitionMapperTest {

    private Competition createCompetition(String name, Long id) {
        Competition competition = new Competition(name, Date.valueOf("2026-01-01"), Date.valueOf("2026-01-10"));
        competition.setIdCompetition(id);
        competition.setDescription("Description test");
        competition.setSport(CompetitionSportEnum.NATATION);
        competition.setPays("France");
        competition.setEstEnFrance(true);
        competition.setAdresse("1 rue test");
        competition.setCodePostal("01000");
        competition.setVille("Ville");
        competition.setGenre(CompetitionGenreEnum.HOMME);
        competition.setAgeMin(18);
        competition.setAgeMax(99);
        competition.setStatut(CompetitionStatusEnum.DRAFT);
        return competition;
    }

    private CompetitionDTO createCompetitionDTO(String name, Long id) {
        return new CompetitionDTO(
                id,
                name,
                "Description test",
                CompetitionSportEnum.NATATION,
                Date.valueOf("2026-01-01"),
                Date.valueOf("2026-01-10"),
                "France",
                true,
                "1 rue test",
                "01000",
                "Ville",
                CompetitionGenreEnum.HOMME,
                18,
                99,
                CompetitionStatusEnum.DRAFT
        );
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
        assertEquals("Description test", dto.getDescription());
        assertEquals(CompetitionSportEnum.NATATION, dto.getSport());
        assertEquals("France", dto.getPays());
        assertTrue(dto.isEstEnFrance());
        assertEquals(CompetitionStatusEnum.DRAFT, dto.getStatut());
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
        assertEquals("Description test", competition.getDescription());
        assertEquals(CompetitionSportEnum.NATATION, competition.getSport());
        assertEquals("France", competition.getPays());
        assertTrue(competition.isEstEnFrance());
        assertEquals(CompetitionStatusEnum.DRAFT, competition.getStatut());
    }

    @Test
    void testToEntity_Null() {
        assertNull(CompetitionMapper.toEntity(null));
    }
}
