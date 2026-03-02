package com.glop.cibl_orga_sport.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.glop.cibl_orga_sport.data.Lieu;
import com.glop.cibl_orga_sport.dto.LieuDTO;
import com.glop.cibl_orga_sport.mapper.LieuMapper;
import com.glop.cibl_orga_sport.service.LieuService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/lieux")
@CrossOrigin(origins = "http://localhost:4200")
public class LieuController {
    
    @Autowired
    private LieuService lieuService;

    @GetMapping
    public List<LieuDTO> getAllLieux() {
        return lieuService.getAllLieux().stream()
                .map(LieuMapper::toDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<LieuDTO> getLieu(@PathVariable Long id) {
        Optional<Lieu> lieu = lieuService.getLieu(id);
        return lieu.map(LieuMapper::toDTO)
                   .map(ResponseEntity::ok)
                   .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<LieuDTO> createLieu(@RequestBody LieuDTO lieuDTO) {
        Lieu lieu = lieuService.createLieu(lieuDTO.getNomLieu(), lieuDTO.getVille(), lieuDTO.getAdresse());
        return ResponseEntity.status(HttpStatus.CREATED).body(LieuMapper.toDTO(lieu));
    }

    @PutMapping("/{id}")
    public ResponseEntity<LieuDTO> updateLieu(@PathVariable Long id, @RequestBody LieuDTO lieuDTO) {
        Lieu updated = lieuService.updateLieu(id, lieuDTO.getNomLieu(), lieuDTO.getVille(), lieuDTO.getAdresse());
        if (updated != null) {
            return ResponseEntity.ok(LieuMapper.toDTO(updated));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteLieu(@PathVariable Long id) {
        try {
            boolean deleted = lieuService.deleteLieu(id);
            if (deleted) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.notFound().build();
        } catch (IllegalStateException e) {
            return ResponseEntity.status(409).body("{\"error\":\"" + e.getMessage() + "\"}");
        }
    }
}
