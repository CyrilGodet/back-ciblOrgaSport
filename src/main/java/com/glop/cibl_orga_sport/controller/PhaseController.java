package com.glop.cibl_orga_sport.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.glop.cibl_orga_sport.data.Phase;
import com.glop.cibl_orga_sport.data.Epreuve;
import com.glop.cibl_orga_sport.data.Lieu;
import com.glop.cibl_orga_sport.dto.PhaseDTO;
import com.glop.cibl_orga_sport.mapper.PhaseMapper;
import com.glop.cibl_orga_sport.service.PhaseService;
import com.glop.cibl_orga_sport.service.EpreuveService;
import com.glop.cibl_orga_sport.service.LieuService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/phases")
@CrossOrigin(origins = "http://localhost:4200")
public class PhaseController {
    
    @Autowired
    private PhaseService phaseService;

    @Autowired
    private EpreuveService epreuveService;

    @Autowired
    private LieuService lieuService;

    @GetMapping
    public List<PhaseDTO> getAllPhases() {
        return phaseService.getAllPhases().stream()
                .map(PhaseMapper::toDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PhaseDTO> getPhase(@PathVariable Long id) {
        Optional<Phase> phase = phaseService.getPhase(id);
        return phase.map(PhaseMapper::toDTO)
                    .map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public PhaseDTO createPhase(@RequestBody Phase phase) {
        Epreuve epreuve = null;
        if (phase.getEpreuve() != null && phase.getEpreuve().getIdEpreuve() != null) {
            Optional<Epreuve> epreuveOpt = epreuveService.getEpreuve(phase.getEpreuve().getIdEpreuve());
            if (epreuveOpt.isPresent()) {
                epreuve = epreuveOpt.get();
            }
        }

        Lieu lieu = null;
        if (phase.getLieu() != null && phase.getLieu().getIdLieu() != null) {
            Optional<Lieu> lieuOpt = lieuService.getLieu(phase.getLieu().getIdLieu());
            if (lieuOpt.isPresent()) {
                lieu = lieuOpt.get();
            }
        }

        Phase created = phaseService.createPhase(phase.getNomPhase(), epreuve, lieu);
        return PhaseMapper.toDTO(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PhaseDTO> updatePhase(@PathVariable Long id, @RequestBody Phase phase) {
        Epreuve epreuve = null;
        if (phase.getEpreuve() != null && phase.getEpreuve().getIdEpreuve() != null) {
            Optional<Epreuve> epreuveOpt = epreuveService.getEpreuve(phase.getEpreuve().getIdEpreuve());
            if (epreuveOpt.isPresent()) {
                epreuve = epreuveOpt.get();
            }
        }

        Lieu lieu = null;
        if (phase.getLieu() != null && phase.getLieu().getIdLieu() != null) {
            Optional<Lieu> lieuOpt = lieuService.getLieu(phase.getLieu().getIdLieu());
            if (lieuOpt.isPresent()) {
                lieu = lieuOpt.get();
            }
        }

        Phase updated = phaseService.updatePhase(id, phase.getNomPhase(), epreuve, lieu);
        if (updated != null) {
            return ResponseEntity.ok(PhaseMapper.toDTO(updated));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePhase(@PathVariable Long id) {
        boolean deleted = phaseService.deletePhase(id);
        if (deleted) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
