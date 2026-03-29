package com.glop.cibl_orga_sport.controller;

import com.glop.cibl_orga_sport.dto.IncidentDTO;
import com.glop.cibl_orga_sport.service.IncidentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/incidents")
public class IncidentController {
    
    @Autowired
    private IncidentService incidentService;
    
    @GetMapping
    public List<IncidentDTO> getAllIncidents() {
        return incidentService.getAllIncidents();
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<IncidentDTO> getIncidentById(@PathVariable Long id) {
        Optional<IncidentDTO> incident = incidentService.getIncidentById(id);
        return incident.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    
    @GetMapping("/competition/{competitionId}")
    public List<IncidentDTO> getIncidentsByCompetition(@PathVariable Long competitionId) {
        return incidentService.getIncidentsByCompetition(competitionId);
    }
    
    @GetMapping("/non-resolus")
    public List<IncidentDTO> getIncidentsNonResolus() {
        return incidentService.getIncidentsNonResolus();
    }
    
    @GetMapping("/competition/{competitionId}/volontaire")
    public List<IncidentDTO> getIncidentsForVolontaire(@PathVariable Long competitionId) {
        return incidentService.getIncidentsForVolontaire(competitionId);
    }
    
    @GetMapping("/competition/{competitionId}/sportif")
    public List<IncidentDTO> getIncidentsForSportif(@PathVariable Long competitionId) {
        return incidentService.getIncidentsForSportif(competitionId);
    }
    
    @PostMapping
    public ResponseEntity<IncidentDTO> createIncident(@RequestBody IncidentDTO incidentDTO) {
        try {
            IncidentDTO created = incidentService.createIncident(incidentDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(created);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<IncidentDTO> updateIncident(@PathVariable Long id, @RequestBody IncidentDTO incidentDTO) {
        IncidentDTO updated = incidentService.updateIncident(id, incidentDTO);
        if (updated != null) {
            return ResponseEntity.ok(updated);
        }
        return ResponseEntity.notFound().build();
    }
    
    @PutMapping("/{id}/resoudre")
    public ResponseEntity<IncidentDTO> resoudreIncident(@PathVariable Long id) {
        IncidentDTO resolved = incidentService.resoudreIncident(id);
        if (resolved != null) {
            return ResponseEntity.ok(resolved);
        }
        return ResponseEntity.notFound().build();
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteIncident(@PathVariable Long id) {
        incidentService.deleteIncident(id);
        return ResponseEntity.noContent().build();
    }
}
