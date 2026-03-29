package com.glop.cibl_orga_sport.controller;

import com.glop.cibl_orga_sport.dto.AccountCreationDTO;
import com.glop.cibl_orga_sport.dto.UtilisateurDTO;
import com.glop.cibl_orga_sport.repository.UtilisateurRepository;
import com.glop.cibl_orga_sport.service.CompteService;
import com.glop.cibl_orga_sport.service.auth.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/comptes")
public class CompteController {

    @Autowired
    private CompteService service;

    @Autowired
    private UtilisateurRepository utilisateurRepository;

    @Autowired
    private JwtService jwtService;

    @PostMapping
    public ResponseEntity<UtilisateurDTO> createAccount(@RequestBody AccountCreationDTO dto) {
        return ResponseEntity.ok(service.createAccount(dto));
    }

    @PostMapping("/login")
    public ResponseEntity<UtilisateurDTO> login(@RequestBody LoginRequest request) {
        return service.login(request.getUsername(), request.getPassword())
                .map(userDto -> {
                    HttpHeaders headers = new HttpHeaders();
                    if (userDto.getEmail() != null) {
                        utilisateurRepository.findByEmail(userDto.getEmail()).ifPresent(user -> {
                            String token = jwtService.generateToken(user);
                            headers.add(HttpHeaders.AUTHORIZATION, "Bearer " + token);
                        });
                    }
                    return ResponseEntity.ok().headers(headers).body(userDto);
                })
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
