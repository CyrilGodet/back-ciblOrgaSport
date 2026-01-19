package com.glop.cibl_orga_sport.controller;

import com.glop.cibl_orga_sport.data.Competition;
import com.glop.cibl_orga_sport.data.Epreuve;
import com.glop.cibl_orga_sport.service.CompetitionService;
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

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(EpreuveController.class)
class EpreuveControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EpreuveService epreuveService;

    @MockBean
    private CompetitionService competitionService;

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

    @Test
    void testGetAllEpreuves() throws Exception {
        Competition competition = createCompetition("Championnats du monde de natation", 1L);
        Epreuve epreuve1 = createEpreuve("100m nage libre", 1L, competition);
        Epreuve epreuve2 = createEpreuve("200m nage libre", 2L, competition);

        when(epreuveService.getAllEpreuves()).thenReturn(Arrays.asList(epreuve1, epreuve2));

        mockMvc.perform(get("/api/epreuves"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].idEpreuve").value(1))
                .andExpect(jsonPath("$[0].nomEpreuve").value("100m nage libre"))
                .andExpect(jsonPath("$[0].competition.nameCompetition").value("Championnats du monde de natation"))
                .andExpect(jsonPath("$[1].idEpreuve").value(2))
                .andExpect(jsonPath("$[1].nomEpreuve").value("200m nage libre"));
    }

    @Test
    void testGetEpreuve() throws Exception {
        Competition competition = createCompetition("Championnats du monde de natation", 1L);
        Epreuve epreuve = createEpreuve("100m nage libre", 1L, competition);

        when(epreuveService.getEpreuve(1L)).thenReturn(Optional.of(epreuve));

        mockMvc.perform(get("/api/epreuves/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.idEpreuve").value(1))
                .andExpect(jsonPath("$.nomEpreuve").value("100m nage libre"))
                .andExpect(jsonPath("$.competition.nameCompetition").value("Championnats du monde de natation"));
    }

    @Test
    void testGetEpreuve_NotFound() throws Exception {
        when(epreuveService.getEpreuve(999L)).thenReturn(Optional.empty());

        mockMvc.perform(get("/api/epreuves/999"))
                .andExpect(status().isNotFound());
    }

    @Test
    void testCreateEpreuve() throws Exception {
        Competition competition = createCompetition("Championnats du monde de natation", 1L);
        Epreuve epreuve = createEpreuve("100m nage libre", 1L, competition);

        when(competitionService.getCompetition(1L)).thenReturn(Optional.of(competition));
        when(epreuveService.createEpreuve("100m nage libre", competition)).thenReturn(epreuve);

        String epreuveJson = "{\"nomEpreuve\":\"100m nage libre\",\"competition\":{\"idCompetition\":1}}";

        mockMvc.perform(post("/api/epreuves")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(epreuveJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.idEpreuve").value(1))
                .andExpect(jsonPath("$.nomEpreuve").value("100m nage libre"))
                .andExpect(jsonPath("$.competition.idCompetition").value(1));
    }

    @Test
    void testUpdateEpreuve() throws Exception {
        Competition competition = createCompetition("Championnats du monde de natation", 1L);
        Epreuve updatedEpreuve = createEpreuve("100m nage libre modifié", 1L, competition);

        when(competitionService.getCompetition(1L)).thenReturn(Optional.of(competition));
        when(epreuveService.updateEpreuve(1L, "100m nage libre modifié", competition)).thenReturn(updatedEpreuve);

        String epreuveJson = "{\"nomEpreuve\":\"100m nage libre modifié\",\"competition\":{\"idCompetition\":1}}";

        mockMvc.perform(put("/api/epreuves/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(epreuveJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nomEpreuve").value("100m nage libre modifié"));
    }

    @Test
    void testDeleteEpreuve() throws Exception {
        when(epreuveService.deleteEpreuve(1L)).thenReturn(true);

        mockMvc.perform(delete("/api/epreuves/1"))
                .andExpect(status().isNoContent());
    }

    @Test
    void testDeleteEpreuve_NotFound() throws Exception {
        when(epreuveService.deleteEpreuve(999L)).thenReturn(false);

        mockMvc.perform(delete("/api/epreuves/999"))
                .andExpect(status().isNotFound());
    }
}
