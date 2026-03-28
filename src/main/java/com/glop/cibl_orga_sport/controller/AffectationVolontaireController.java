package com.glop.cibl_orga_sport.controller;

import com.glop.cibl_orga_sport.dto.AffectationVolontaireDTO;
import com.glop.cibl_orga_sport.service.AffectationVolontaireService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/affectations")
@CrossOrigin(origins = "http://localhost:4200")
public class AffectationVolontaireController {
    
    @Autowired
    private AffectationVolontaireService affectationService;
    
    @GetMapping
    public List<AffectationVolontaireDTO> getAllAffectations() {
        return affectationService.getAllAffectations();
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<AffectationVolontaireDTO> getAffectationById(@PathVariable Long id) {
        Optional<AffectationVolontaireDTO> affectation = affectationService.getAffectationById(id);
        return affectation.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    
    @GetMapping("/volontaire/{volontaireId}")
    public List<AffectationVolontaireDTO> getAffectationsByVolontaire(@PathVariable Long volontaireId) {
        return affectationService.getAffectationsByVolontaire(volontaireId);
    }
    
    @GetMapping("/competition/{competitionId}")
    public List<AffectationVolontaireDTO> getAffectationsByCompetition(@PathVariable Long competitionId) {
        return affectationService.getAffectationsByCompetition(competitionId);
    }
    
    @GetMapping("/volontaire/{volontaireId}/programme")
    public List<AffectationVolontaireDTO> getProgrammeVolontaire(
            @PathVariable Long volontaireId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        return affectationService.getProgrammeVolontaire(volontaireId, date);
    }
    
    @GetMapping("/volontaire/{volontaireId}/programme/futur")
    public List<AffectationVolontaireDTO> getProgrammeVolontaireFutur(@PathVariable Long volontaireId) {
        return affectationService.getProgrammeVolontaireFutur(volontaireId);
    }
    
    @PostMapping
    public ResponseEntity<AffectationVolontaireDTO> createAffectation(@RequestBody AffectationVolontaireDTO affectationDTO) {
        try {
            AffectationVolontaireDTO created = affectationService.createAffectation(affectationDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(created);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<AffectationVolontaireDTO> updateAffectation(
            @PathVariable Long id, 
            @RequestBody AffectationVolontaireDTO affectationDTO) {
        AffectationVolontaireDTO updated = affectationService.updateAffectation(id, affectationDTO);
        if (updated != null) {
            return ResponseEntity.ok(updated);
        }
        return ResponseEntity.notFound().build();
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAffectation(@PathVariable Long id) {
        affectationService.deleteAffectation(id);
        return ResponseEntity.noContent().build();
    }
}
