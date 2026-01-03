package com.glop.cibl_orga_sport.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.glop.cibl_orga_sport.data.Lieu;
import com.glop.cibl_orga_sport.service.LieuService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/lieux")
@CrossOrigin(origins = "http://localhost:4200")
public class LieuController {
    
    @Autowired
    private LieuService lieuService;

    @GetMapping
    public List<Lieu> getAllLieux() {
        return lieuService.getAllLieux();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Lieu> getLieu(@PathVariable Long id) {
        Optional<Lieu> lieu = lieuService.getLieu(id);
        return lieu.map(ResponseEntity::ok)
                   .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Lieu createLieu(@RequestBody Lieu lieu) {
        return lieuService.createLieu(lieu.getNom(), lieu.getVille(), lieu.getAdresse());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Lieu> updateLieu(@PathVariable Long id, @RequestBody Lieu lieu) {
        Lieu updated = lieuService.updateLieu(id, lieu.getNom(), lieu.getVille(), lieu.getAdresse());
        if (updated != null) {
            return ResponseEntity.ok(updated);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLieu(@PathVariable Long id) {
        boolean deleted = lieuService.deleteLieu(id);
        if (deleted) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
