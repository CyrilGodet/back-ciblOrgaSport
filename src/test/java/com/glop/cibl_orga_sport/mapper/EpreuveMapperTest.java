package com.glop.cibl_orga_sport.mapper;

import com.glop.cibl_orga_sport.data.Competition;
import com.glop.cibl_orga_sport.data.Epreuve;
import com.glop.cibl_orga_sport.data.enumType.CompetitionStatusEnum;
import com.glop.cibl_orga_sport.data.enumType.DisciplineEnum;
import com.glop.cibl_orga_sport.data.enumType.CompetitionGenreEnum;
import com.glop.cibl_orga_sport.data.enumType.CompetitionSportEnum;
import com.glop.cibl_orga_sport.dto.CompetitionDTO;
import com.glop.cibl_orga_sport.dto.EpreuveDTO;
import org.junit.jupiter.api.Test;

import java.sql.Date;

import static org.junit.jupiter.api.Assertions.*;

class EpreuveMapperTest {

    private Competition createCompetition(String name, Long id) {
        Competition competition = new Competition(name, null, new com.glop.cibl_orga_sport.data.Periode(Date.valueOf("2026-01-01"), Date.valueOf("2026-01-10")), null, null, null, null, null);
        competition.setIdCompetition(id);
        return competition;
    }

    private Epreuve createEpreuve(String name, Long id, Competition competition) {
        Epreuve epreuve = new Epreuve(name);
        epreuve.setIdEpreuve(id);
        epreuve.setCompetition(competition);
        epreuve.setDiscipline(DisciplineEnum.NAGE_LIBRE);
        epreuve.setGenre(CompetitionGenreEnum.HOMME);
        epreuve.setPeriode(new com.glop.cibl_orga_sport.data.Periode(
            new java.sql.Date(new java.util.Date().getTime()),
            new java.sql.Date(new java.util.Date().getTime())
        ));
        epreuve.setStatut(CompetitionStatusEnum.DRAFT);
        return epreuve;
    }

    private CompetitionDTO createCompetitionDTO(String name, Long id) {
        return new CompetitionDTO(
                id,
                name,
                "Description",
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

    private EpreuveDTO createEpreuveDTO(String name, Long id, CompetitionDTO competitionDTO) {
        return new EpreuveDTO(
                id,
                name,
                competitionDTO,
                DisciplineEnum.NAGE_LIBRE,
                CompetitionGenreEnum.HOMME,
                new java.sql.Date(System.currentTimeMillis()),
                new java.sql.Date(System.currentTimeMillis() + 86400000),
                CompetitionStatusEnum.DRAFT
        );
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
        assertEquals(DisciplineEnum.NAGE_LIBRE, dto.getDiscipline());
        assertEquals(CompetitionGenreEnum.HOMME, dto.getGenre());
        assertEquals(CompetitionStatusEnum.DRAFT, dto.getStatut());
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
        assertEquals(DisciplineEnum.NAGE_LIBRE, epreuve.getDiscipline());
        assertEquals(CompetitionGenreEnum.HOMME, epreuve.getGenre());
        assertEquals(CompetitionStatusEnum.DRAFT, epreuve.getStatut());
    }

    @Test
    void testToEntity_Null() {
        assertNull(EpreuveMapper.toEntity(null));
    }
}
