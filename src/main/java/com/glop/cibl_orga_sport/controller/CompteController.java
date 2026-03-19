package com.glop.cibl_orga_sport.controller;

import com.glop.cibl_orga_sport.dto.AccountCreationDTO;
import com.glop.cibl_orga_sport.dto.UtilisateurDTO;
import com.glop.cibl_orga_sport.service.CompteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/comptes")
@CrossOrigin(origins = "http://localhost:4200")
public class CompteController {

    @Autowired
    private CompteService service;

    @PostMapping
    public ResponseEntity<UtilisateurDTO> createAccount(@RequestBody AccountCreationDTO dto) {
        return ResponseEntity.ok(service.createAccount(dto));
    }

    @PostMapping("/login")
    public ResponseEntity<UtilisateurDTO> login(@RequestBody LoginRequest request) {
        return service.login(request.getUsername(), request.getPassword())
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(401).build());
    }

    // Classe interne pour la requête de login
    public static class LoginRequest {
        private String username;
        private String password;

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }
    }
}
