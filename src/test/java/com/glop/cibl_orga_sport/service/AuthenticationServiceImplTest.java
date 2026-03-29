package com.glop.cibl_orga_sport.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import com.glop.cibl_orga_sport.data.Roles;
import com.glop.cibl_orga_sport.data.Utilisateur;
import com.glop.cibl_orga_sport.data.auth.request.SignUpRequest;
import com.glop.cibl_orga_sport.data.auth.request.SigninRequest;
import com.glop.cibl_orga_sport.data.auth.response.JwtAuthenticationResponse;
import com.glop.cibl_orga_sport.dto.UtilisateurDTO;
import com.glop.cibl_orga_sport.exception.InvalidEntityException;
import com.glop.cibl_orga_sport.repository.UtilisateurRepository;
import com.glop.cibl_orga_sport.service.auth.JwtService;
import com.glop.cibl_orga_sport.service.auth.impl.AuthenticationServiceImpl;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;

@ExtendWith(MockitoExtension.class)
class AuthenticationServiceImplTest {

    @Mock
    private UtilisateurRepository userDao;
    @Mock
    private RolesService rolesService;
    @Mock
    private PasswordEncoder passwordEncoder;
    @Mock
    private JwtService jwtService;
    @Mock
    private AuthenticationManager authenticationManager;
    @Mock
    private HistoryService historyService;
    @Mock
    private UtilisateurService userService;

    @InjectMocks
    private AuthenticationServiceImpl service;

    @Test
    void signupShouldThrowWhenEmailAlreadyExists() {
        SignUpRequest request = SignUpRequest.builder().login("mail@test.com").build();
        when(userDao.existsByEmail("mail@test.com")).thenReturn(true);

        assertThrows(InvalidEntityException.class, () -> service.signup(request));
    }

    @Test
    void signupShouldThrowWhenPasswordsMismatch() {
        SignUpRequest request = SignUpRequest.builder()
                .name("A")
                .lastname("B")
                .login("mail@test.com")
                .mdp("a")
                .verifyMdp("b")
                .build();

        when(userDao.existsByEmail("mail@test.com")).thenReturn(false);

        assertThrows(InvalidEntityException.class, () -> service.signup(request));
    }

    @Test
    void signupShouldReturnJwtWhenValid() {
        SignUpRequest request = SignUpRequest.builder()
                .name("A")
                .lastname("B")
                .login("mail@test.com")
                .mdp("pwd")
                .verifyMdp("pwd")
                .build();

        Roles role = new Roles();
        role.setIdRoles(2);

        Utilisateur saved = new Utilisateur();
        saved.setEmail("mail@test.com");

        when(userDao.existsByEmail("mail@test.com")).thenReturn(false);
        when(rolesService.findById(2)).thenReturn(role);
        when(passwordEncoder.encode("pwd")).thenReturn("encoded");
        when(userDao.save(any(Utilisateur.class))).thenReturn(saved);
        when(jwtService.generateToken(saved)).thenReturn("jwt-token");

        JwtAuthenticationResponse response = service.signup(request);

        assertEquals("jwt-token", response.getToken());
        assertEquals("mail@test.com", response.getLogin());
    }

    @Test
    void signinShouldThrowWhenStateNotApproved() {
        SigninRequest request = SigninRequest.builder().login("u@test.com").mdp("pwd").build();
        UtilisateurDTO dto = new UtilisateurDTO();
        dto.setState(0);

        when(userService.findByEmail("u@test.com")).thenReturn(dto);

        assertThrows(InvalidEntityException.class, () -> service.signin(request));
    }

    @Test
    void signinShouldReturnJwtWhenValid() {
        SigninRequest request = SigninRequest.builder().login("u@test.com").mdp("pwd").build();

        UtilisateurDTO dto = new UtilisateurDTO();
        dto.setState(10);

        Utilisateur user = new Utilisateur();
        user.setEmail("u@test.com");

        when(userService.findByEmail("u@test.com")).thenReturn(dto);
        when(userDao.findByEmail("u@test.com")).thenReturn(Optional.of(user));
        when(jwtService.generateToken(user)).thenReturn("jwt-ok");

        JwtAuthenticationResponse response = service.signin(request);

        assertEquals("jwt-ok", response.getToken());
        assertEquals("u@test.com", response.getLogin());
    }
}
