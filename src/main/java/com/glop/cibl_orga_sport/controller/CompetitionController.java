package com.glop.cibl_orga_sport.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.glop.cibl_orga_sport.data.Competition;
import com.glop.cibl_orga_sport.dto.EpreuveDTO;
import com.glop.cibl_orga_sport.dto.PhaseDTO;
import com.glop.cibl_orga_sport.mapper.EpreuveMapper;
import com.glop.cibl_orga_sport.mapper.PhaseMapper;
import com.glop.cibl_orga_sport.service.CompetitionService;
import com.glop.cibl_orga_sport.service.EpreuveService;
import com.glop.cibl_orga_sport.service.PhaseService;

@RestController
@RequestMapping("/api/competitions")
@CrossOrigin(origins = "http://localhost:4200")
public class CompetitionController {

    @Autowired
    private CompetitionService service;

    @Autowired
    private EpreuveService epreuveService;

    @Autowired
    private PhaseService phaseService;

    @GetMapping
    public List<Competition> getAllCompetitions() {
        return service.getAllCompetitions();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Competition> getCompetition(@PathVariable Long id) {
        Optional<Competition> competition = service.getCompetition(id);
        return competition.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/{id}/epreuves")
    public List<EpreuveDTO> getEpreuvesByCompetition(@PathVariable Long id) {
        return epreuveService.getEpreuvesByCompetitionId(id).stream()
                .map(EpreuveMapper::toDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}/phases")
    public List<PhaseDTO> getPhasesByCompetition(@PathVariable Long id) {
        return phaseService.getPhasesByCompetitionId(id).stream()
                .map(PhaseMapper::toDTO)
                .collect(Collectors.toList());
    }

    @PostMapping
    public Competition createCompetition(@RequestBody Competition competition) {
        return service.createCompetition(
                competition.getNameCompetition(),
                competition.getDateDebut(),
                competition.getDateFin());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Competition> updateCompetition(@PathVariable Long id, @RequestBody Competition competition) {
        Competition updated = service.updateCompetition(
                id,
                competition.getNameCompetition(),
                competition.getDateDebut(),
                competition.getDateFin());
        if (updated != null) {
            return ResponseEntity.ok(updated);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCompetition(@PathVariable Long id) {
        try {
            boolean deleted = service.deleteCompetition(id);
            if (deleted) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.notFound().build();
        } catch (IllegalStateException e) {
            return ResponseEntity.status(409).body("{\"error\":\"" + e.getMessage() + "\"}");
        }
    }
}
