package com.glop.cibl_orga_sport.controller;

import com.glop.cibl_orga_sport.data.Equipe;
import com.glop.cibl_orga_sport.dto.EquipeDTO;
import com.glop.cibl_orga_sport.mapper.EquipeMapper;
import com.glop.cibl_orga_sport.service.EquipeService;
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
public class EquipeController {

    @Autowired
    private EquipeService equipeService;

    @GetMapping
    public List<EquipeDTO> getAllEquipes() {
        return equipeService.getAllEquipes().stream()
                .map(EquipeMapper::toDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EquipeDTO> getEquipe(@PathVariable Long id) {
        Optional<Equipe> equipe = equipeService.getEquipe(id);
        return equipe.map(EquipeMapper::toDTO)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<EquipeDTO> createEquipe(@RequestBody EquipeDTO equipeDTO) {
        Equipe created = equipeService.createEquipe(
                equipeDTO.getNomEquipe());
        return new ResponseEntity<>(EquipeMapper.toDTO(created), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EquipeDTO> updateEquipe(@PathVariable Long id, @RequestBody EquipeDTO equipeDTO) {
        Equipe updated = equipeService.updateEquipe(
                id,
                equipeDTO.getNomEquipe());
        if (updated != null) {
            return ResponseEntity.ok(EquipeMapper.toDTO(updated));
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
