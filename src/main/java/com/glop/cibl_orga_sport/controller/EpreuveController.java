package com.glop.cibl_orga_sport.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.glop.cibl_orga_sport.data.Competition;
import com.glop.cibl_orga_sport.data.Epreuve;
import com.glop.cibl_orga_sport.dto.EpreuveDTO;
import com.glop.cibl_orga_sport.mapper.EpreuveMapper;
import com.glop.cibl_orga_sport.service.CompetitionService;
import com.glop.cibl_orga_sport.service.EpreuveService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/epreuves")
@CrossOrigin(origins = "http://localhost:4200")
public class EpreuveController {
    
    @Autowired
    private EpreuveService epreuveService;
    
    @Autowired
    private CompetitionService competitionService;

    @GetMapping
    public List<EpreuveDTO> getAllEpreuves() {
        return epreuveService.getAllEpreuves().stream()
                .map(EpreuveMapper::toDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EpreuveDTO> getEpreuve(@PathVariable Long id) {
        Optional<Epreuve> epreuve = epreuveService.getEpreuve(id);
        return epreuve.map(EpreuveMapper::toDTO)
                      .map(ResponseEntity::ok)
                      .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<EpreuveDTO> createEpreuve(@RequestBody EpreuveDTO epreuveDTO) {
        Competition competition = null;
        if (epreuveDTO.getCompetition() != null && epreuveDTO.getCompetition().getIdCompetition() != null) {
            Optional<Competition> competitionOpt = competitionService.getCompetition(epreuveDTO.getCompetition().getIdCompetition());
            if (competitionOpt.isPresent()) {
                competition = competitionOpt.get();
            }
        }
        Epreuve created = epreuveService.createEpreuve(
                epreuveDTO.getNomEpreuve(),
                epreuveDTO.getDiscipline(),
                epreuveDTO.getGenre(),
                epreuveDTO.getDateDebut(),
                epreuveDTO.getDateFin(),
                competition
        );
        return ResponseEntity.status(201).body(EpreuveMapper.toDTO(created));
    }

    @PutMapping("/{id}")
    public ResponseEntity<EpreuveDTO> updateEpreuve(@PathVariable Long id, @RequestBody EpreuveDTO epreuveDTO) {
        Competition competition = null;
        if (epreuveDTO.getCompetition() != null && epreuveDTO.getCompetition().getIdCompetition() != null) {
            Optional<Competition> competitionOpt = competitionService.getCompetition(epreuveDTO.getCompetition().getIdCompetition());
            if (competitionOpt.isPresent()) {
                competition = competitionOpt.get();
            }
        }
        Epreuve updated = epreuveService.updateEpreuve(
                id,
                epreuveDTO.getNomEpreuve(),
                epreuveDTO.getDiscipline(),
                epreuveDTO.getGenre(),
                epreuveDTO.getDateDebut(),
                epreuveDTO.getDateFin(),
                epreuveDTO.getStatut(),
                competition
        );
        if (updated != null) {
            return ResponseEntity.ok(EpreuveMapper.toDTO(updated));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteEpreuve(@PathVariable Long id) {
        try {
            boolean deleted = epreuveService.deleteEpreuve(id);
            if (deleted) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.notFound().build();
        } catch (IllegalStateException e) {
            return ResponseEntity.status(409).body("{\"error\":\"" + e.getMessage() + "\"}");
        }
    }

    @PatchMapping("/{id}/publish")
    public ResponseEntity<EpreuveDTO> publishEpreuve(@PathVariable Long id) {
        Epreuve published = epreuveService.publishEpreuve(id);
        if (published != null) {
            return ResponseEntity.ok(EpreuveMapper.toDTO(published));
        }
        return ResponseEntity.badRequest().build();
    }

    @PatchMapping("/{id}/start")
    public ResponseEntity<EpreuveDTO> startEpreuve(@PathVariable Long id) {
        Epreuve started = epreuveService.startEpreuve(id);
        if (started != null) {
            return ResponseEntity.ok(EpreuveMapper.toDTO(started));
        }
        return ResponseEntity.badRequest().build();
    }

    @PatchMapping("/{id}/finish")
    public ResponseEntity<EpreuveDTO> finishEpreuve(@PathVariable Long id) {
        Epreuve finished = epreuveService.finishEpreuve(id);
        if (finished != null) {
            return ResponseEntity.ok(EpreuveMapper.toDTO(finished));
        }
        return ResponseEntity.badRequest().build();
    }
}
