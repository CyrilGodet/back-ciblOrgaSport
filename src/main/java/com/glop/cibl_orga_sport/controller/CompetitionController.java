package com.glop.cibl_orga_sport.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.glop.cibl_orga_sport.data.Competition;
import com.glop.cibl_orga_sport.dto.CompetitionDTO;
import com.glop.cibl_orga_sport.dto.EpreuveDTO;
import com.glop.cibl_orga_sport.dto.PhaseDTO;
import com.glop.cibl_orga_sport.mapper.CompetitionMapper;
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
    public List<CompetitionDTO> getAllCompetitions() {
        return service.getAllCompetitions().stream()
                .map(CompetitionMapper::toDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CompetitionDTO> getCompetition(@PathVariable Long id) {
        Optional<Competition> competition = service.getCompetition(id);
        return competition.map(CompetitionMapper::toDTO)
                .map(ResponseEntity::ok)
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
    public ResponseEntity<CompetitionDTO> createCompetition(@RequestBody CompetitionDTO competitionDTO) {
        Competition competition = service.createCompetition(
                competitionDTO.getNameCompetition(),
                competitionDTO.getDescription(),
                competitionDTO.getSport(),
                competitionDTO.getDateDebut(),
                competitionDTO.getDateFin(),
                competitionDTO.getPays(),
                competitionDTO.isEstEnFrance(),
                competitionDTO.getAdresse(),
                competitionDTO.getCodePostal(),
                competitionDTO.getVille(),
                competitionDTO.getGenre(),
                competitionDTO.getAgeMin(),
                competitionDTO.getAgeMax()
        );
        return new ResponseEntity<>(CompetitionMapper.toDTO(competition), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CompetitionDTO> updateCompetition(@PathVariable Long id, @RequestBody CompetitionDTO competitionDTO) {
        Competition updated = service.updateCompetition(
                id,
                competitionDTO.getNameCompetition(),
                competitionDTO.getDescription(),
                competitionDTO.getSport(),
                competitionDTO.getDateDebut(),
                competitionDTO.getDateFin(),
                competitionDTO.getPays(),
                competitionDTO.isEstEnFrance(),
                competitionDTO.getAdresse(),
                competitionDTO.getCodePostal(),
                competitionDTO.getVille(),
                competitionDTO.getGenre(),
                competitionDTO.getAgeMin(),
                competitionDTO.getAgeMax(),
                competitionDTO.getStatut()
        );
        if (updated != null) {
            return ResponseEntity.ok(CompetitionMapper.toDTO(updated));
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
