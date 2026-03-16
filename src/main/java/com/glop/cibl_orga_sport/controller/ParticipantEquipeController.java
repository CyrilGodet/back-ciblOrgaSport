package com.glop.cibl_orga_sport.controller;

import com.glop.cibl_orga_sport.data.ParticipantEquipe;
import com.glop.cibl_orga_sport.dto.ParticipantEquipeDTO;
import com.glop.cibl_orga_sport.mapper.ParticipantEquipeMapper;
import com.glop.cibl_orga_sport.service.ParticipantEquipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/equipes")
@CrossOrigin(origins = "http://localhost:4200")
public class ParticipantEquipeController {

    @Autowired
    private ParticipantEquipeService equipeService;

    @GetMapping
    public List<ParticipantEquipeDTO> getAllEquipes() {
        return equipeService.getAllEquipes().stream()
                .map(ParticipantEquipeMapper::toDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ParticipantEquipeDTO> getEquipe(@PathVariable Long id) {
        Optional<ParticipantEquipe> equipe = equipeService.getEquipe(id);
        return equipe.map(ParticipantEquipeMapper::toDTO)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/search")
    public List<ParticipantEquipeDTO> searchEquipes(@RequestParam("q") String query,
            @RequestParam(value = "taille", required = false) Integer taille) {
        return equipeService.searchEquipes(query, taille).stream()
                .map(ParticipantEquipeMapper::toDTO)
                .collect(Collectors.toList());
    }

    @PostMapping
    public ResponseEntity<ParticipantEquipeDTO> createEquipe(@RequestBody ParticipantEquipeDTO equipeDTO) {
        ParticipantEquipe created = equipeService.createEquipe(equipeDTO);
        return new ResponseEntity<>(ParticipantEquipeMapper.toDTO(created), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ParticipantEquipeDTO> updateEquipe(@PathVariable Long id, @RequestBody ParticipantEquipeDTO equipeDTO) {
        ParticipantEquipe updated = equipeService.updateEquipe(
                id,
                equipeDTO.getNomEquipe());
        if (updated != null) {
            return ResponseEntity.ok(ParticipantEquipeMapper.toDTO(updated));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteEquipe(@PathVariable Long id) {
        boolean deleted = equipeService.deleteEquipe(id);
        if (deleted) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}
