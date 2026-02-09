package com.glop.cibl_orga_sport.mapper;

import com.glop.cibl_orga_sport.data.Competition;
import com.glop.cibl_orga_sport.data.Periode;
import com.glop.cibl_orga_sport.data.Lieu;
import com.glop.cibl_orga_sport.data.ConditionAge;
import com.glop.cibl_orga_sport.data.enumType.CompetitionStatusEnum;
import com.glop.cibl_orga_sport.data.enumType.CompetitionGenreEnum;
import com.glop.cibl_orga_sport.data.enumType.CompetitionSportEnum;
import com.glop.cibl_orga_sport.dto.CompetitionDTO;
import org.junit.jupiter.api.Test;

import java.sql.Date;

import static org.junit.jupiter.api.Assertions.*;

class CompetitionMapperTest {

    private Competition createCompetition(String name, Long id) {
        Periode periode = new Periode(Date.valueOf("2026-01-01"), Date.valueOf("2026-01-10"));
        Lieu lieu = new Lieu(null, "Ville", "1 rue test");
        ConditionAge conditionAge = new ConditionAge(18, 99);
        
        Competition competition = new Competition(
            name,
            "Description test",
            periode,
            lieu,
            conditionAge,
            CompetitionGenreEnum.HOMME,
            CompetitionStatusEnum.DRAFT,
            CompetitionSportEnum.NATATION
        );
        competition.setIdCompetition(id);
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
        assertEquals("Ville", dto.getVille());
        assertEquals(18, dto.getAgeMin());
        assertEquals(99, dto.getAgeMax());
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
        assertNotNull(competition.getPeriode());
        assertEquals(Date.valueOf("2026-01-01"), competition.getPeriode().getDateDebut());
        assertEquals(Date.valueOf("2026-01-10"), competition.getPeriode().getDateFin());
        assertEquals("Description test", competition.getDescription());
        assertEquals(CompetitionSportEnum.NATATION, competition.getSport());
        assertNotNull(competition.getLieu());
        assertEquals("Ville", competition.getLieu().getVille());
        assertNotNull(competition.getConditionAge());
        assertEquals(18, competition.getConditionAge().getAgeMin());
        assertEquals(99, competition.getConditionAge().getAgeMax());
        assertEquals(CompetitionStatusEnum.DRAFT, competition.getStatut());
    }

    @Test
    void testToEntity_Null() {
        assertNull(CompetitionMapper.toEntity(null));
    }
}
