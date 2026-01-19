package com.glop.cibl_orga_sport.controller;

import com.glop.cibl_orga_sport.data.Competition;
import com.glop.cibl_orga_sport.data.Epreuve;
import com.glop.cibl_orga_sport.data.Lieu;
import com.glop.cibl_orga_sport.data.Phase;
import com.glop.cibl_orga_sport.service.EpreuveService;
import com.glop.cibl_orga_sport.service.LieuService;
import com.glop.cibl_orga_sport.service.PhaseService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.sql.Date;
import java.util.Arrays;
import java.util.Optional;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(PhaseController.class)
class PhaseControllerTest {

        @Autowired
        private MockMvc mockMvc;

        @MockBean
        private PhaseService phaseService;

        @MockBean
        private EpreuveService epreuveService;

        @MockBean
        private LieuService lieuService;

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

        private Lieu createLieu(String nom, String ville, String adresse, Long id) {
                Lieu lieu = new Lieu(nom, ville, adresse);
                lieu.setIdLieu(id);
                return lieu;
        }

        private Phase createPhase(String name, Long id, Epreuve epreuve, Lieu lieu) {
                Phase phase = new Phase(name, Date.valueOf("2026-01-05"), Date.valueOf("2026-01-06"), epreuve, lieu);
                phase.setIdPhase(id);
                return phase;
        }

        @Test
        void testGetAllPhases() throws Exception {
                Competition competition = createCompetition("Championnats du monde de natation", 1L);
                Epreuve epreuve = createEpreuve("100m nage libre", 1L, competition);
                Lieu lieu = createLieu("Centre Aquatique Olympique Métropole du Grand Saint-Denis", "Saint-Denis",
                                "361-363, Av. du Président Wilson", 1L);
                Phase phase1 = createPhase("Finale", 1L, epreuve, lieu);
                Phase phase2 = createPhase("Demi-finale", 2L, epreuve, lieu);

                when(phaseService.getAllPhases()).thenReturn(Arrays.asList(phase1, phase2));

                mockMvc.perform(get("/api/phases"))
                                .andExpect(status().isOk())
                                .andExpect(jsonPath("$[0].idPhase").value(1))
                                .andExpect(jsonPath("$[0].nomPhase").value("Finale"))
                                .andExpect(jsonPath("$[0].epreuve.nomEpreuve").value("100m nage libre"))
                                .andExpect(
                                                jsonPath("$[0].epreuve.competition.nameCompetition")
                                                                .value("Championnats du monde de natation"))
                                .andExpect(jsonPath("$[0].lieu.nom")
                                                .value("Centre Aquatique Olympique Métropole du Grand Saint-Denis"))
                                .andExpect(jsonPath("$[1].idPhase").value(2))
                                .andExpect(jsonPath("$[1].nomPhase").value("Demi-finale"));
        }

        @Test
        void testGetPhase() throws Exception {
                Competition competition = createCompetition("Championnats du monde de natation", 1L);
                Epreuve epreuve = createEpreuve("100m nage libre", 1L, competition);
                Lieu lieu = createLieu("Centre Aquatique Olympique Métropole du Grand Saint-Denis", "Saint-Denis",
                                "361-363, Av. du Président Wilson", 1L);
                Phase phase = createPhase("Finale", 1L, epreuve, lieu);

                when(phaseService.getPhase(1L)).thenReturn(Optional.of(phase));

                mockMvc.perform(get("/api/phases/1"))
                                .andExpect(status().isOk())
                                .andExpect(jsonPath("$.idPhase").value(1))
                                .andExpect(jsonPath("$.nomPhase").value("Finale"))
                                .andExpect(jsonPath("$.epreuve.nomEpreuve").value("100m nage libre"))
                                .andExpect(jsonPath("$.lieu.nom")
                                                .value("Centre Aquatique Olympique Métropole du Grand Saint-Denis"));
        }

        @Test
        void testGetPhase_NotFound() throws Exception {
                when(phaseService.getPhase(999L)).thenReturn(Optional.empty());

                mockMvc.perform(get("/api/phases/999"))
                                .andExpect(status().isNotFound());
        }

        @Test
        void testCreatePhase() throws Exception {
                Competition competition = createCompetition("Championnats du monde de natation", 1L);
                Epreuve epreuve = createEpreuve("100m nage libre", 1L, competition);
                Lieu lieu = createLieu("Centre Aquatique Olympique Métropole du Grand Saint-Denis", "Saint-Denis",
                                "361-363, Av. du Président Wilson", 1L);
                Phase phase = createPhase("Finale", 1L, epreuve, lieu);

                // Given Jackson ignores epreuve and lieu because of @JsonBackReference,
                // the controller will likely pass null if they are not found or not mapped.
                // We use any() to be safe and avoid mismatch.
                when(phaseService.createPhase(eq("Finale"), any(), any(), any(), any())).thenReturn(phase);

                String phaseJson = "{\"nomPhase\":\"Finale\",\"dateDebut\":\"2026-01-05\",\"dateFin\":\"2026-01-06\",\"epreuve\":{\"idEpreuve\":1},\"lieu\":{\"idLieu\":1}}";

                mockMvc.perform(post("/api/phases")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(phaseJson))
                                .andExpect(status().isOk())
                                .andExpect(jsonPath("$.idPhase").value(1))
                                .andExpect(jsonPath("$.nomPhase").value("Finale"));
        }

        @Test
        void testUpdatePhase() throws Exception {
                Competition competition = createCompetition("Championnats du monde de natation", 1L);
                Epreuve epreuve = createEpreuve("100m nage libre", 1L, competition);
                Lieu lieu = createLieu("Centre Aquatique Olympique Métropole du Grand Saint-Denis", "Saint-Denis",
                                "361-363, Av. du Président Wilson", 1L);
                Phase updatedPhase = createPhase("Finale modifiée", 1L, epreuve, lieu);
                updatedPhase.setDateDebut(Date.valueOf("2026-01-07"));
                updatedPhase.setDateFin(Date.valueOf("2026-01-08"));

                when(phaseService.updatePhase(eq(1L), eq("Finale modifiée"), any(), any(), any(), any()))
                                .thenReturn(updatedPhase);

                String phaseJson = "{\"nomPhase\":\"Finale modifiée\",\"dateDebut\":\"2026-01-07\",\"dateFin\":\"2026-01-08\",\"epreuve\":{\"idEpreuve\":1},\"lieu\":{\"idLieu\":1}}";

                mockMvc.perform(put("/api/phases/1")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(phaseJson))
                                .andExpect(status().isOk())
                                .andExpect(jsonPath("$.nomPhase").value("Finale modifiée"));
        }

        @Test
        void testDeletePhase() throws Exception {
                when(phaseService.deletePhase(1L)).thenReturn(true);

                mockMvc.perform(delete("/api/phases/1"))
                                .andExpect(status().isNoContent());
        }

        @Test
        void testDeletePhase_NotFound() throws Exception {
                when(phaseService.deletePhase(999L)).thenReturn(false);

                mockMvc.perform(delete("/api/phases/999"))
                                .andExpect(status().isNotFound());
        }
}
