package com.glop.cibl_orga_sport.service;

import static org.junit.jupiter.api.Assertions.*;

import com.glop.cibl_orga_sport.data.Utilisateur;
import com.glop.cibl_orga_sport.service.auth.impl.JwtServiceImpl;
import org.junit.jupiter.api.Test;

class JwtServiceImplTest {

    @Test
    void generateAndValidateTokenShouldWork() {
        JwtServiceImpl jwtService = new JwtServiceImpl();
        jwtService.jwtSigningKey = "dGVzdGtleXRlc3RrZXl0ZXN0a2V5dGVzdGtleXRlc3RrZXk=";

        Utilisateur user = new Utilisateur();
        user.setEmail("jwt@test.com");

        String token = jwtService.generateToken(user);

        assertNotNull(token);
        assertEquals("jwt@test.com", jwtService.extractUserName(token));
        assertTrue(jwtService.isTokenValid(token, user));
    }

    @Test
    void isTokenValidShouldFailForDifferentUser() {
        JwtServiceImpl jwtService = new JwtServiceImpl();
        jwtService.jwtSigningKey = "dGVzdGtleXRlc3RrZXl0ZXN0a2V5dGVzdGtleXRlc3RrZXk=";

        Utilisateur user1 = new Utilisateur();
        user1.setEmail("a@test.com");

        Utilisateur user2 = new Utilisateur();
        user2.setEmail("b@test.com");

        String token = jwtService.generateToken(user1);

        assertFalse(jwtService.isTokenValid(token, user2));
    }
}
