package com.glop.cibl_orga_sport.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.glop.cibl_orga_sport.service.ResultatService;
import com.glop.cibl_orga_sport.dto.ResultatDTO;

@RestController
@RequestMapping("/api/resultats")
@CrossOrigin(origins = "http://localhost:4200")
public class ResultatController {

    @Autowired
    private ResultatService resultatService;

    @PutMapping("/match/{matchId}")
    public ResponseEntity<ResultatDTO> saveDraftResultat(@PathVariable Long matchId,
            @RequestBody ResultatDTO resultatDTO) {
        ResultatDTO savedResult = resultatService.saveDraftResultat(matchId, resultatDTO);
        return ResponseEntity.ok(savedResult);
    }

    @PatchMapping("/match/{matchId}/complete")
    public ResponseEntity<ResultatDTO> completeResultat(@PathVariable Long matchId) {
        ResultatDTO completedResult = resultatService.completeResultat(matchId);
        return ResponseEntity.ok(completedResult);
    }

    @PostMapping("/etape-epreuve/{idEtapeEpreuve}/publish")
    public ResponseEntity<Void> publishResultatsForEtape(@PathVariable Long idEtapeEpreuve) {
        resultatService.publishResultatsForEtape(idEtapeEpreuve);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/match/{matchId}/participant/{participantId}/abandon")
    public ResponseEntity<Void> declareAbandonForMatch(@PathVariable Long matchId, @PathVariable Long participantId) {
        resultatService.declareAbandonForMatch(matchId, participantId);
        return ResponseEntity.ok().build();
    }
}
