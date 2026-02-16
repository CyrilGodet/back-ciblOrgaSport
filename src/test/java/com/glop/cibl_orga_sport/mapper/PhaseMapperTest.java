package com.glop.cibl_orga_sport.mapper;

import com.glop.cibl_orga_sport.data.Competition;
import com.glop.cibl_orga_sport.data.Epreuve;
import com.glop.cibl_orga_sport.data.Lieu;
import com.glop.cibl_orga_sport.data.Periode;
import com.glop.cibl_orga_sport.data.enumType.CompetitionGenreEnum;
import com.glop.cibl_orga_sport.data.enumType.CompetitionSportEnum;
import com.glop.cibl_orga_sport.data.enumType.CompetitionStatusEnum;
import com.glop.cibl_orga_sport.data.enumType.DisciplineEnum;
import com.glop.cibl_orga_sport.data.enumType.EtapeEpreuveEnum;
import com.glop.cibl_orga_sport.data.EtapeEpreuve;
import com.glop.cibl_orga_sport.dto.CompetitionDTO;
import com.glop.cibl_orga_sport.dto.EpreuveDTO;
import com.glop.cibl_orga_sport.dto.PhaseDTO;
import org.junit.jupiter.api.Test;

import java.sql.Date;

import static org.junit.jupiter.api.Assertions.*;

class PhaseMapperTest {

    private Competition createCompetition(String name, Long id) {
        Competition competition = new Competition(name, null, new Periode(Date.valueOf("2026-01-01"), Date.valueOf("2026-01-10")), null, null, null, null, null);
        competition.setIdCompetition(id);
        return competition;
    }

    private Epreuve createEpreuve(String name, Long id, Competition competition) {
        Epreuve epreuve = new Epreuve(name);
        epreuve.setIdEpreuve(id);
        epreuve.setCompetition(competition);
        return epreuve;
    }

    private EtapeEpreuve createPhase(String name, Long id, Epreuve epreuve, Lieu lieu) {
        Periode periode = new Periode(Date.valueOf("2026-01-05"), Date.valueOf("2026-01-06"));
        EtapeEpreuve phase = new EtapeEpreuve(epreuve, periode, null, EtapeEpreuveEnum.FINALE);
        phase.setIdEtapeEpreuve(id);
        return phase;
    }

    private CompetitionDTO createCompetitionDTO(String name, Long id) {
        return new CompetitionDTO(
                id,
                name,
                "Description",
                CompetitionSportEnum.NATATION,
                Date.valueOf("2026-01-01"),
                Date.valueOf("2026-01-10"),
                CompetitionGenreEnum.HOMME,
                18,
                99,
                CompetitionStatusEnum.DRAFT,
                null
        );
    }

    private EpreuveDTO createEpreuveDTO(String name, Long id, CompetitionDTO competitionDTO) {
        return new EpreuveDTO(
                id,
                name,
                "Description test",
                competitionDTO,
                DisciplineEnum.NAGE_LIBRE,
                CompetitionGenreEnum.HOMME,
                new java.util.Date(),
                new java.util.Date(),
                18,
                99,
                CompetitionStatusEnum.DRAFT
        );
    }

    private PhaseDTO createPhaseDTO(Long id, EpreuveDTO epreuveDTO) {
        return new PhaseDTO(id, EtapeEpreuveEnum.FINALE, Date.valueOf("2026-01-05"), Date.valueOf("2026-01-06"), epreuveDTO);
    }

    @Test
    void testToDTO() {
        Competition competition = createCompetition("Championnats du monde de natation", 1L);
        Epreuve epreuve = createEpreuve("100m nage libre", 2L, competition);
        EtapeEpreuve phase = createPhase("FINALE", 4L, epreuve, null);

        PhaseDTO dto = PhaseMapper.toDTO(phase);

        assertNotNull(dto);
        assertEquals(4L, dto.getIdPhase());
        assertEquals(EtapeEpreuveEnum.FINALE, dto.getEtapeEpreuve());
        assertEquals(Date.valueOf("2026-01-05"), dto.getDateDebut());
        assertEquals(Date.valueOf("2026-01-06"), dto.getDateFin());
        assertNotNull(dto.getEpreuve());
        assertEquals("100m nage libre", dto.getEpreuve().getNomEpreuve());
    }

    @Test
    void testToDTO_Null() {
        assertNull(PhaseMapper.toDTO(null));
    }

    @Test
    void testToEntity() {
        CompetitionDTO competitionDTO = createCompetitionDTO("Championnats du monde de natation", 1L);
        EpreuveDTO epreuveDTO = createEpreuveDTO("100m nage libre", 2L, competitionDTO);
        PhaseDTO dto = createPhaseDTO(4L, epreuveDTO);

        EtapeEpreuve phase = PhaseMapper.toEntity(dto);

        assertNotNull(phase);
        assertEquals(EtapeEpreuveEnum.FINALE, phase.getEtapeEpreuveEnum());
        assertNotNull(phase.getPeriode());
        assertEquals(Date.valueOf("2026-01-05"), phase.getPeriode().getDateDebut());
        assertEquals(Date.valueOf("2026-01-06"), phase.getPeriode().getDateFin());
    }

    @Test
    void testToEntity_Null() {
        assertNull(PhaseMapper.toEntity(null));
    }
}
