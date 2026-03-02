package com.glop.cibl_orga_sport.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.glop.cibl_orga_sport.data.EtapeEpreuve;
import com.glop.cibl_orga_sport.data.Epreuve;
import com.glop.cibl_orga_sport.dto.PhaseDTO;
import com.glop.cibl_orga_sport.mapper.PhaseMapper;
import com.glop.cibl_orga_sport.service.PhaseService;
import com.glop.cibl_orga_sport.service.EpreuveService;

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

    @GetMapping
    public List<PhaseDTO> getAllPhases() {
        return phaseService.getAllPhases().stream()
                .map(PhaseMapper::toDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PhaseDTO> getPhase(@PathVariable Long id) {
        Optional<EtapeEpreuve> phase = phaseService.getPhase(id);
        return phase.map(PhaseMapper::toDTO)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<PhaseDTO> createPhase(@RequestBody PhaseDTO phaseDTO) {
        Epreuve epreuve = null;
        if (phaseDTO.getEpreuve() != null && phaseDTO.getEpreuve().getIdEpreuve() != null) {
            Optional<Epreuve> epreuveOpt = epreuveService.getEpreuve(phaseDTO.getEpreuve().getIdEpreuve());
            if (epreuveOpt.isPresent()) {
                epreuve = epreuveOpt.get();
            }
        }

        EtapeEpreuve created = phaseService.createPhase(epreuve, phaseDTO.getDateDebut(), phaseDTO.getDateFin(), 
                                                       phaseDTO.getEtapeEpreuve(), null);
        return ResponseEntity.status(HttpStatus.CREATED).body(PhaseMapper.toDTO(created));
        
    }

    @PutMapping("/{id}")
    public ResponseEntity<PhaseDTO> updatePhase(@PathVariable Long id, @RequestBody PhaseDTO phaseDTO) {
        Epreuve epreuve = null;
        if (phaseDTO.getEpreuve() != null && phaseDTO.getEpreuve().getIdEpreuve() != null) {
            Optional<Epreuve> epreuveOpt = epreuveService.getEpreuve(phaseDTO.getEpreuve().getIdEpreuve());
            if (epreuveOpt.isPresent()) {
                epreuve = epreuveOpt.get();
            }
        }

        EtapeEpreuve updated = phaseService.updatePhase(id, epreuve, phaseDTO.getDateDebut(), phaseDTO.getDateFin(),
                                                       phaseDTO.getEtapeEpreuve(), null);
        if (updated != null) {
            return ResponseEntity.ok(PhaseMapper.toDTO(updated));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePhase(@PathVariable Long id) {
        try {
            boolean deleted = phaseService.deletePhase(id);
            if (deleted) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.notFound().build();
        } catch (IllegalStateException e) {
            return ResponseEntity.status(409).body("{\"error\":\"" + e.getMessage() + "\"}");
        }
    }
}
