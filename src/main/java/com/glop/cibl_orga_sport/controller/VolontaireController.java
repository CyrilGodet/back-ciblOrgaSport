package com.glop.cibl_orga_sport.controller;

import com.glop.cibl_orga_sport.dto.VolontaireDTO;
import com.glop.cibl_orga_sport.service.VolontaireService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/volontaires")
@CrossOrigin(origins = "http://localhost:4200")
public class VolontaireController {
    
    @Autowired
    private VolontaireService volontaireService;
    
    @GetMapping
    public List<VolontaireDTO> getAllVolontaires() {
        return volontaireService.getAllVolontaires();
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<VolontaireDTO> getVolontaireById(@PathVariable Long id) {
        Optional<VolontaireDTO> volontaire = volontaireService.getVolontaireById(id);
        return volontaire.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
        
    @PostMapping
    public ResponseEntity<VolontaireDTO> createVolontaire(@RequestBody VolontaireDTO volontaireDTO) {
        try {
            VolontaireDTO created = volontaireService.createVolontaire(volontaireDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(created);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<VolontaireDTO> updateVolontaire(@PathVariable Long id, @RequestBody VolontaireDTO volontaireDTO) {
        VolontaireDTO updated = volontaireService.updateVolontaire(id, volontaireDTO);
        if (updated != null) {
            return ResponseEntity.ok(updated);
        }
        return ResponseEntity.notFound().build();
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVolontaire(@PathVariable Long id) {
        volontaireService.deleteVolontaire(id);
        return ResponseEntity.noContent().build();
    }
}
