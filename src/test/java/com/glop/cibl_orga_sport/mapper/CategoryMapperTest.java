package com.glop.cibl_orga_sport.mapper;

import com.glop.cibl_orga_sport.data.Category;
import com.glop.cibl_orga_sport.data.Competition;
import com.glop.cibl_orga_sport.data.Epreuve;
import com.glop.cibl_orga_sport.data.enumType.SportEnum;
import com.glop.cibl_orga_sport.data.enumType.CompetitionStatusEnum;
import com.glop.cibl_orga_sport.data.enumType.DisciplineEnum;
import com.glop.cibl_orga_sport.data.enumType.GenreEnum;
import com.glop.cibl_orga_sport.dto.CategoryDTO;
import com.glop.cibl_orga_sport.dto.CompetitionDTO;
import com.glop.cibl_orga_sport.dto.EpreuveDTO;
import org.junit.jupiter.api.Test;

import java.sql.Date;

import static org.junit.jupiter.api.Assertions.*;

class CategoryMapperTest {

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

    private Category createCategory(String name, Long id, Epreuve epreuve) {
        Category category = new Category(name);
        category.setIdCategory(id);
        category.setEpreuve(epreuve);
        return category;
    }

    private CompetitionDTO createCompetitionDTO(String name, Long id) {
        return new CompetitionDTO(
                id,
                name,
                "Description",
                SportEnum.NATATION,
                Date.valueOf("2026-01-01"),
                Date.valueOf("2026-01-10"),
                "France",
                true,
                "1 rue test",
                "01000",
                "Ville",
                GenreEnum.HOMME,
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
                GenreEnum.HOMME,
                new java.util.Date(),
                new java.util.Date(),
                CompetitionStatusEnum.DRAFT
        );
    }

    private CategoryDTO createCategoryDTO(String name, Long id, EpreuveDTO epreuveDTO) {
        return new CategoryDTO(id, name, epreuveDTO);
    }

    @Test
    void testToDTO() {
        Competition competition = createCompetition("Championnats du monde de natation", 1L);
        Epreuve epreuve = createEpreuve("100m nage libre", 2L, competition);
        Category category = createCategory("Hommes", 3L, epreuve);

        CategoryDTO dto = CategoryMapper.toDTO(category);

        assertNotNull(dto);
        assertEquals(3L, dto.getIdCategory());
        assertEquals("Hommes", dto.getNameCategory());
        assertNotNull(dto.getEpreuve());
        assertEquals("100m nage libre", dto.getEpreuve().getNomEpreuve());
        assertEquals("Championnats du monde de natation", dto.getEpreuve().getCompetition().getNameCompetition());
    }

    @Test
    void testToDTO_Null() {
        assertNull(CategoryMapper.toDTO(null));
    }

    @Test
    void testToEntity() {
        CompetitionDTO competitionDTO = createCompetitionDTO("Championnats du monde de natation", 1L);
        EpreuveDTO epreuveDTO = createEpreuveDTO("100m nage libre", 2L, competitionDTO);
        CategoryDTO dto = createCategoryDTO("Hommes", 3L, epreuveDTO);

        Category category = CategoryMapper.toEntity(dto);

        assertNotNull(category);
        assertEquals("Hommes", category.getNameCategory());
    }

    @Test
    void testToEntity_Null() {
        assertNull(CategoryMapper.toEntity(null));
    }
}
