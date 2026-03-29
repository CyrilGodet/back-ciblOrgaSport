package com.glop.cibl_orga_sport.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.glop.cibl_orga_sport.data.Billet;
import com.glop.cibl_orga_sport.dto.BilletDTO;
import com.glop.cibl_orga_sport.mapper.BilletMapper;
import com.glop.cibl_orga_sport.service.BilletService;

@RestController
@RequestMapping("/api/billets")
public class BilletController {

    @Autowired
    private BilletService billetService;

    @GetMapping
    public List<BilletDTO> getAllBillets() {
        return billetService.getAllBillets().stream()
                .map(BilletMapper::toDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<BilletDTO> getBillet(@PathVariable Long id) {
        Optional<Billet> billet = billetService.getBillet(id);
        return billet.map(BilletMapper::toDTO)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/visiteur/{visiteurId}")
    public List<BilletDTO> getBilletsByVisiteur(@PathVariable Long visiteurId) {
        return billetService.getBilletsByVisiteur(visiteurId).stream()
                .map(BilletMapper::toDTO)
                .collect(Collectors.toList());
    }

    @PostMapping
    public ResponseEntity<BilletDTO> createBillet(@RequestBody BilletDTO dto) {
        Billet created = billetService.createBillet(dto);
        if (created == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(BilletMapper.toDTO(created));
    }

    @PutMapping("/{id}")
    public ResponseEntity<BilletDTO> updateBillet(@PathVariable Long id, @RequestBody BilletDTO dto) {
        Billet updated = billetService.updateBillet(id, dto);
        if (updated == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(BilletMapper.toDTO(updated));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBillet(@PathVariable Long id) {
        boolean deleted = billetService.deleteBillet(id);
        if (deleted) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
