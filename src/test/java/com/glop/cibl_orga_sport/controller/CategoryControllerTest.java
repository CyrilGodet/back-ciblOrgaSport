package com.glop.cibl_orga_sport.controller;

import com.glop.cibl_orga_sport.data.Category;
import com.glop.cibl_orga_sport.data.Competition;
import com.glop.cibl_orga_sport.data.Epreuve;
import com.glop.cibl_orga_sport.service.CategoryService;
import com.glop.cibl_orga_sport.service.EpreuveService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.sql.Date;
import java.util.Arrays;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CategoryController.class)
class CategoryControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CategoryService categoryService;

    @MockBean
    private EpreuveService epreuveService;

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

    @Test
    void testGetAllCategories() throws Exception {
        Competition competition = createCompetition("Championnats du monde de natation", 1L);
        Epreuve epreuve = createEpreuve("100m nage libre", 1L, competition);
        Category category1 = createCategory("Hommes", 1L, epreuve);
        Category category2 = createCategory("Femmes", 2L, epreuve);

        when(categoryService.getAllCategories()).thenReturn(Arrays.asList(category1, category2));

        mockMvc.perform(get("/api/categories"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].idCategory").value(1))
                .andExpect(jsonPath("$[0].nameCategory").value("Hommes"))
                .andExpect(jsonPath("$[0].epreuve.nomEpreuve").value("100m nage libre"))
                .andExpect(jsonPath("$[0].epreuve.competition.nameCompetition").value("Championnats du monde de natation"))
                .andExpect(jsonPath("$[1].idCategory").value(2))
                .andExpect(jsonPath("$[1].nameCategory").value("Femmes"));
    }

    @Test
    void testGetCategory() throws Exception {
        Competition competition = createCompetition("Championnats du monde de natation", 1L);
        Epreuve epreuve = createEpreuve("100m nage libre", 1L, competition);
        Category category = createCategory("Hommes", 1L, epreuve);

        when(categoryService.getCategory(1L)).thenReturn(Optional.of(category));

        mockMvc.perform(get("/api/categories/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.idCategory").value(1))
                .andExpect(jsonPath("$.nameCategory").value("Hommes"))
                .andExpect(jsonPath("$.epreuve.nomEpreuve").value("100m nage libre"));
    }

    @Test
    void testGetCategory_NotFound() throws Exception {
        when(categoryService.getCategory(999L)).thenReturn(Optional.empty());

        mockMvc.perform(get("/api/categories/999"))
                .andExpect(status().isNotFound());
    }

    @Test
    void testCreateCategory() throws Exception {
        Competition competition = createCompetition("Championnats du monde de natation", 1L);
        Epreuve epreuve = createEpreuve("100m nage libre", 1L, competition);
        Category category = createCategory("Hommes", 1L, epreuve);

        when(epreuveService.getEpreuve(1L)).thenReturn(Optional.of(epreuve));
        when(categoryService.createCategory("Hommes", epreuve)).thenReturn(category);

        String categoryJson = "{\"nameCategory\":\"Hommes\",\"epreuve\":{\"idEpreuve\":1}}";

        mockMvc.perform(post("/api/categories")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(categoryJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.idCategory").value(1))
                .andExpect(jsonPath("$.nameCategory").value("Hommes"))
                .andExpect(jsonPath("$.epreuve.idEpreuve").value(1));
    }

    @Test
    void testUpdateCategory() throws Exception {
        Competition competition = createCompetition("Championnats du monde de natation", 1L);
        Epreuve epreuve = createEpreuve("100m nage libre", 1L, competition);
        Category updatedCategory = createCategory("Hommes Modified", 1L, epreuve);

        when(epreuveService.getEpreuve(1L)).thenReturn(Optional.of(epreuve));
        when(categoryService.updateCategory(1L, "Hommes Modified", epreuve)).thenReturn(updatedCategory);

        String categoryJson = "{\"nameCategory\":\"Hommes Modified\",\"epreuve\":{\"idEpreuve\":1}}";

        mockMvc.perform(put("/api/categories/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(categoryJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nameCategory").value("Hommes Modified"));
    }

    @Test
    void testUpdateCategory_NotFound() throws Exception {
        when(categoryService.updateCategory(anyLong(), anyString(), any())).thenReturn(null);

        String categoryJson = "{\"nameCategory\":\"Hommes\"}";

        mockMvc.perform(put("/api/categories/999")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(categoryJson))
                .andExpect(status().isNotFound());
    }

    @Test
    void testDeleteCategory() throws Exception {
        when(categoryService.deleteCategory(1L)).thenReturn(true);

        mockMvc.perform(delete("/api/categories/1"))
                .andExpect(status().isNoContent());
    }

    @Test
    void testDeleteCategory_NotFound() throws Exception {
        when(categoryService.deleteCategory(999L)).thenReturn(false);

        mockMvc.perform(delete("/api/categories/999"))
                .andExpect(status().isNotFound());
    }
}
