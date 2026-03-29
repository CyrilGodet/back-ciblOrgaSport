package com.glop.cibl_orga_sport.controller;

import com.glop.cibl_orga_sport.data.enumType.DocumentStatusEnum;
import com.glop.cibl_orga_sport.data.Sportif;
import com.glop.cibl_orga_sport.data.UserDtoJson;
import com.glop.cibl_orga_sport.data.Visiteur;
import com.glop.cibl_orga_sport.dto.SportifDTO;
import com.glop.cibl_orga_sport.dto.VisiteurDTO;
import com.glop.cibl_orga_sport.dto.CommissaireDTO;
import com.glop.cibl_orga_sport.mapper.SportifMapper;
import com.glop.cibl_orga_sport.mapper.VisiteurMapper;
import com.glop.cibl_orga_sport.mapper.CommissaireMapper;
import com.glop.cibl_orga_sport.dto.ParticipantDTO;
import com.glop.cibl_orga_sport.dto.UtilisateurDTO;
import com.glop.cibl_orga_sport.mapper.ParticipantMapper;
import com.glop.cibl_orga_sport.service.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/utilisateurs")
public class UtilisateurController {

    @Autowired
    private UtilisateurService service;

    @PostMapping("/sportif")
    public SportifDTO createSportif(@RequestBody SportifDTO dto) {
        Sportif sportif = service.createSportif(dto);
        return SportifMapper.toDTO(sportif);
    }

    @PostMapping("/visiteur")
    public VisiteurDTO createVisiteur(@RequestBody VisiteurDTO dto) {
        Visiteur visiteur = service.createVisiteur(dto);
        return VisiteurMapper.toDTO(visiteur);
    }

    @GetMapping("/visiteurs")
    public List<VisiteurDTO> getAllVisiteurs() {
        return service.getAllVisiteurs().stream()
                .map(v -> (VisiteurDTO) VisiteurMapper.toDTO(v))
                .collect(Collectors.toList());
    }

    @GetMapping("/commissaires")
    public List<CommissaireDTO> getAllCommissaires() {
        return service.getAllCommissaires().stream()
                .map(c -> (CommissaireDTO) CommissaireMapper.toDTO(c))
                .collect(Collectors.toList());
    }

    @GetMapping("/sportifs")
    public List<SportifDTO> getAllSportifs() {
        return service.getAllSportifs().stream()
                .map(s -> (SportifDTO) SportifMapper.toDTO(s))
                .collect(Collectors.toList());
    }

    @GetMapping("/search")
    public List<SportifDTO> searchSportifs(@RequestParam("q") String query) {
        return service.searchSportifs(query).stream()
                .map(s -> (SportifDTO) SportifMapper.toDTO(s))
                .collect(Collectors.toList());
    }

    @GetMapping("/search/participant")
    public List<ParticipantDTO> searchParticipantSportifs(@RequestParam("q") String query) {
        return service.searchParticipantSportifs(query).stream()
                .map(p -> (ParticipantDTO) ParticipantMapper.toDTO(p))
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public UtilisateurDTO findById(@PathVariable Long id) {
        return service.findById(id);
    }

    @GetMapping("/sportif/{id}/participations")
    public ResponseEntity<com.glop.cibl_orga_sport.dto.SportifParticipationsDTO> getSportifParticipations(
            @PathVariable Long id) {
        return ResponseEntity.ok(service.getSportifParticipations(id));
    }

    @GetMapping("/sportif/{id}/competitions")
    public ResponseEntity<List<com.glop.cibl_orga_sport.dto.CompetitionDTO>> getSportifCompetitions(
            @PathVariable Long id) {
        return ResponseEntity.ok(service.getCompetitionsBySportif(id));
    }

    @PutMapping(value = "/approval/{id}", produces = "application/json")
    public ResponseEntity<UserDtoJson> approavalUser(@PathVariable("id") Long id) {
        UserDtoJson approved = service.approval(id);
        return ResponseEntity.ok(approved);
    }

    @GetMapping
    public ResponseEntity<List<UserDtoJson>> findAll() {
        List<UserDtoJson> users = service.findAll();
        return ResponseEntity.ok(users);
    }

    @PostMapping("/sportif/{id}/certificat")
    public ResponseEntity<?> uploadCertificat(@PathVariable Long id, @RequestParam("file") MultipartFile file)
            throws IOException {
        service.updateCertificatMedical(id, file.getBytes());
        return ResponseEntity.ok().build();
    }

    @PostMapping("/sportif/{id}/passeport")
    public ResponseEntity<?> uploadPasseport(@PathVariable Long id, @RequestParam("file") MultipartFile file)
            throws IOException {
        service.updatePasseport(id, file.getBytes());
        return ResponseEntity.ok().build();
    }

    @PutMapping("/sportif/{id}/charte-conformite")
    public ResponseEntity<?> updateCharte(
            @PathVariable Long id,
            @RequestParam boolean value) {
        service.updateCharteConformite(id, value);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/sportif/{id}/certificat-status")
    public ResponseEntity<?> updateCertificatStatus(
            @PathVariable Long id,
            @RequestParam DocumentStatusEnum status) {
        service.updateCertificatMedicalStatus(id, status);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/sportif/{id}/passeport-status")
    public ResponseEntity<?> updatePasseportStatus(
            @PathVariable Long id,
            @RequestParam DocumentStatusEnum status) {
        service.updatePasseportStatus(id, status);
        return ResponseEntity.ok().build();
    }
}