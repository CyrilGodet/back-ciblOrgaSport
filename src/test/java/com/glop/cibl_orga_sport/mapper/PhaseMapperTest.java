package com.glop.cibl_orga_sport.mapper;

import com.glop.cibl_orga_sport.data.Competition;
import com.glop.cibl_orga_sport.data.Epreuve;
import com.glop.cibl_orga_sport.data.Lieu;
import com.glop.cibl_orga_sport.data.EtapeEpreuve;
import com.glop.cibl_orga_sport.dto.CompetitionDTO;
import com.glop.cibl_orga_sport.dto.EpreuveDTO;
import com.glop.cibl_orga_sport.dto.LieuDTO;
import com.glop.cibl_orga_sport.dto.PhaseDTO;
import org.junit.jupiter.api.Test;

import java.sql.Date;

import static org.junit.jupiter.api.Assertions.*;

class PhaseMapperTest {

    private Competition createCompetition(String name, Long id) {
        Competition competition = new Competition(name, null, new com.glop.cibl_orga_sport.data.Periode(Date.valueOf("2026-01-01"), Date.valueOf("2026-01-10")), null, null, null, null, null);
        competition.setIdCompetition(id);
        return competition;
    }

    private Epreuve createEpreuve(String name, Long id, Competition competition) {
        Epreuve epreuve = new Epreuve(name);
        epreuve.setIdEpreuve(id);
        epreuve.setCompetition(competition);
        return epreuve;
    }

    private Lieu createLieu(String nom, String ville, String adresse, Long id) {
        Lieu lieu = new Lieu(nom, ville, adresse);
        lieu.setIdLieu(id);
        return lieu;
    }

    private EtapeEpreuve createPhase(String name, Long id, Epreuve epreuve, Lieu lieu) {
        com.glop.cibl_orga_sport.data.Periode periode = new com.glop.cibl_orga_sport.data.Periode(Date.valueOf("2026-01-05"), Date.valueOf("2026-01-06"));
        EtapeEpreuve phase = new EtapeEpreuve(epreuve, periode, null, com.glop.cibl_orga_sport.data.enumType.EtapeEpreuveEnum.FINALE);
        phase.setIdEtapeEpreuve(id);
        return phase;
    }

    private CompetitionDTO createCompetitionDTO(String name, Long id) {
        return new CompetitionDTO(
                id,
                name,
                "Description",
                com.glop.cibl_orga_sport.data.enumType.CompetitionSportEnum.NATATION,
                Date.valueOf("2026-01-01"),
                Date.valueOf("2026-01-10"),
                "France",
                true,
                "123 rue test",
                "75001",
                "Paris",
                com.glop.cibl_orga_sport.data.enumType.CompetitionGenreEnum.HOMME,
                18,
                99,
                com.glop.cibl_orga_sport.data.enumType.CompetitionStatusEnum.DRAFT
        );
    }

    private EpreuveDTO createEpreuveDTO(String name, Long id, CompetitionDTO competitionDTO) {
        return new EpreuveDTO(
                id,
                name,
                competitionDTO,
                com.glop.cibl_orga_sport.data.enumType.DisciplineEnum.NAGE_LIBRE,
                com.glop.cibl_orga_sport.data.enumType.CompetitionGenreEnum.HOMME,
                new java.util.Date(),
                new java.util.Date(),
                com.glop.cibl_orga_sport.data.enumType.CompetitionStatusEnum.DRAFT
        );
    }

    private LieuDTO createLieuDTO(String nom, String ville, String adresse, Long id) {
        return new LieuDTO(id, nom, ville, adresse);
    }

    private PhaseDTO createPhaseDTO(String name, Long id, EpreuveDTO epreuveDTO, LieuDTO lieuDTO) {
        return new PhaseDTO(id, name, Date.valueOf("2026-01-05"), Date.valueOf("2026-01-06"), epreuveDTO, lieuDTO);
    }

    @Test
    void testToDTO() {
        Competition competition = createCompetition("Championnats du monde de natation", 1L);
        Epreuve epreuve = createEpreuve("100m nage libre", 2L, competition);
        Lieu lieu = createLieu("Centre Aquatique Olympique Métropole du Grand Saint-Denis", "Saint-Denis",
                "361-363, Av. du Président Wilson", 3L);
        EtapeEpreuve phase = createPhase("FINALE", 4L, epreuve, lieu);

        PhaseDTO dto = PhaseMapper.toDTO(phase);

        assertNotNull(dto);
        assertEquals(4L, dto.getIdPhase());
        assertEquals("FINALE", dto.getNomPhase());
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
        LieuDTO lieuDTO = createLieuDTO("Centre Aquatique Olympique Métropole du Grand Saint-Denis", "Saint-Denis",
                "361-363, Av. du Président Wilson", 3L);
        PhaseDTO dto = createPhaseDTO("FINALE", 4L, epreuveDTO, lieuDTO);

        EtapeEpreuve phase = PhaseMapper.toEntity(dto);

        assertNotNull(phase);
        assertEquals(com.glop.cibl_orga_sport.data.enumType.EtapeEpreuveEnum.FINALE, phase.getEtapeEpreuveEnum());
        assertNotNull(phase.getPeriode());
        assertEquals(Date.valueOf("2026-01-05"), phase.getPeriode().getDateDebut());
        assertEquals(Date.valueOf("2026-01-06"), phase.getPeriode().getDateFin());
    }

    @Test
    void testToEntity_Null() {
        assertNull(PhaseMapper.toEntity(null));
    }
}
