package com.glop.cibl_orga_sport.controller;

import com.glop.cibl_orga_sport.dto.ConformiteCharteEuropeenneDTO;
import com.glop.cibl_orga_sport.dto.SportifInscritDTO;
import com.glop.cibl_orga_sport.dto.ValidationSaisieResultatsRequestDTO;
import com.glop.cibl_orga_sport.service.CommissaireService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/commissaires")
@CrossOrigin(origins = "http://localhost:4200")
public class CommissaireController {

    @Autowired
    private CommissaireService commissaireService;

    @GetMapping("/competitions/{idCompetition}/sportifs-inscrits")
    public List<SportifInscritDTO> listerSportifsInscrits(@PathVariable Long idCompetition) {
        return commissaireService.listerSportifsInscrits(idCompetition);
    }

    @GetMapping("/competitions/{idCompetition}/conformite-charte-europeenne")
    public ConformiteCharteEuropeenneDTO verifierConformiteCharteEuropeenne(@PathVariable Long idCompetition) {
        return commissaireService.verifierConformiteCharteEuropeenne(idCompetition);
    }

    @PostMapping("/competitions/{idCompetition}/etapes/{idEtapeEpreuve}/validation-resultats")
    public ResponseEntity<Void> validerResultats(
            @PathVariable Long idCompetition,
            @PathVariable Long idEtapeEpreuve,
            @RequestBody ValidationSaisieResultatsRequestDTO request) {

        commissaireService.validerSaisieResultats(
                idCompetition,
                idEtapeEpreuve,
                request.getIdCommissaire(),
                request.isSaisieManuelle());

        return ResponseEntity.ok().build();
    }
}
