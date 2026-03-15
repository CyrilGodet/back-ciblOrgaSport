package com.glop.cibl_orga_sport.controller;

import com.glop.cibl_orga_sport.data.Sportif;
import com.glop.cibl_orga_sport.data.UserDtoJson;
import com.glop.cibl_orga_sport.data.Visiteur;
import com.glop.cibl_orga_sport.data.Commissaire;
import com.glop.cibl_orga_sport.dto.SportifDTO;
import com.glop.cibl_orga_sport.dto.UtilisateurDTO;
import com.glop.cibl_orga_sport.dto.VisiteurDTO;
import com.glop.cibl_orga_sport.dto.CommissaireDTO;
import com.glop.cibl_orga_sport.mapper.SportifMapper;
import com.glop.cibl_orga_sport.mapper.VisiteurMapper;
import com.glop.cibl_orga_sport.mapper.CommissaireMapper;
import com.glop.cibl_orga_sport.service.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/utilisateurs")
@CrossOrigin(origins = "http://localhost:4200")
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
                .map(VisiteurMapper::toDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/commissaires")
    public List<CommissaireDTO> getAllCommissaires() {
        return service.getAllCommissaires().stream()
                .map(CommissaireMapper::toDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/sportifs")
    public List<SportifDTO> getAllSportifs() {
        return service.getAllSportifs().stream()
                .map(SportifMapper::toDTO)
                .collect(Collectors.toList());
    }

    @PutMapping(value = "/user/{id}", consumes = "application/json", produces = "application/json")
    public ResponseEntity<UserDtoJson> updateUser(@PathVariable("id") Long id, @RequestBody UserDtoJson dto) {
        UserDtoJson updated = service.updateNoMdp(id, dto);
        return ResponseEntity.ok(updated);
    }

    @GetMapping
    public ResponseEntity<List<UserDtoJson>> findAll() {
        List<UserDtoJson> users = service.findAll();
        return ResponseEntity.ok(users);
    }


}
