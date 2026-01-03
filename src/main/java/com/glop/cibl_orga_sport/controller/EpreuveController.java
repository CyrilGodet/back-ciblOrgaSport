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
    public EpreuveDTO createEpreuve(@RequestBody Epreuve epreuve) {
        Competition competition = null;
        if (epreuve.getCompetition() != null && epreuve.getCompetition().getIdCompetition() != null) {
            Optional<Competition> competitionOpt = competitionService.getCompetition(epreuve.getCompetition().getIdCompetition());
            if (competitionOpt.isPresent()) {
                competition = competitionOpt.get();
            }
        }
        Epreuve created = epreuveService.createEpreuve(epreuve.getNomEpreuve(), competition);
        return EpreuveMapper.toDTO(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EpreuveDTO> updateEpreuve(@PathVariable Long id, @RequestBody Epreuve epreuve) {
        Competition competition = null;
        if (epreuve.getCompetition() != null && epreuve.getCompetition().getIdCompetition() != null) {
            Optional<Competition> competitionOpt = competitionService.getCompetition(epreuve.getCompetition().getIdCompetition());
            if (competitionOpt.isPresent()) {
                competition = competitionOpt.get();
            }
        }
        Epreuve updated = epreuveService.updateEpreuve(id, epreuve.getNomEpreuve(), competition);
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
}
