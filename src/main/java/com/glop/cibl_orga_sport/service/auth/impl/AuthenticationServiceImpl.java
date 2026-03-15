package com.glop.cibl_orga_sport.service.auth.impl;

import com.glop.cibl_orga_sport.data.Utilisateur;
import com.glop.cibl_orga_sport.data.auth.request.SignUpRequest;
import com.glop.cibl_orga_sport.data.auth.request.SigninRequest;
import com.glop.cibl_orga_sport.data.auth.response.JwtAuthenticationResponse;
import com.glop.cibl_orga_sport.dto.RolesDto;
import com.glop.cibl_orga_sport.dto.UtilisateurDTO;
import com.glop.cibl_orga_sport.exception.ErrorCodes;
import com.glop.cibl_orga_sport.exception.InvalidEntityException;
import com.glop.cibl_orga_sport.repository.UtilisateurRepository;
import com.glop.cibl_orga_sport.service.HistoryService;
import com.glop.cibl_orga_sport.service.RolesService;
import com.glop.cibl_orga_sport.service.UtilisateurService;
import com.glop.cibl_orga_sport.service.auth.AuthenticationService;
import com.glop.cibl_orga_sport.service.auth.JwtService;
import com.glop.cibl_orga_sport.validator.SignUpValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UtilisateurRepository userDao;
    private final RolesService rolesService;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final HistoryService historyService;
    private final UtilisateurService userService;

    @Override
    public JwtAuthenticationResponse signup(SignUpRequest request) {
        String email = request.getLogin();
        if (userDao.existsByEmail(email)) {
            throw new InvalidEntityException("Une autre user existe déjà avec le même login");
        }

        List<String> errors = SignUpValidator.validate(request);
        if (!errors.isEmpty()) {
            log.error("Le request signunp n'est pas valide", request);
            historyService.saveHistory("sign up", "failed");
            throw new InvalidEntityException("Le requested user n'est pas valide", ErrorCodes.USER_NOT_VALID, errors);
        }

        if (!request.getMdp().equals(request.getVerifyMdp())) {
            historyService.saveHistory("register User", "failed");
            throw new InvalidEntityException("The passwords do not match.");
        }
        Utilisateur user = new Utilisateur();
        user.setNom(request.getName());
        user.setPrenom(request.getLastname());
        user.setEmail(request.getLogin());
        user.setMdp(passwordEncoder.encode(request.getMdp()));
        user.setState(0);
        RolesDto rolesDto = rolesService.findById(2);
        UtilisateurDTO userDto = UtilisateurDTO.fromEntity(user);
        userDto.setRoles(rolesDto);
        userDto.setMdp(passwordEncoder.encode(request.getMdp()));
        System.out.println("ito ary ilay userDto = " + userDto);
        Utilisateur user1 = UtilisateurDTO.toEntity(userDto);
        System.out.println("Dia ito ary ilay user1 avec roles = " + user1);
        System.out.println("ito ilay sauvegarde = " + userDao.save(user1));
        var jwt = jwtService.generateToken(user);
        historyService.saveHistory("register User", "success", user1);
        return JwtAuthenticationResponse.builder().token(jwt).build();
    }
    //AUTHENTICATION RENVOIE ATTEMPT NEW USER IN USERCONNECTED
    /*
    @Override
    public JwtAuthenticationResponse signin(SigninRequest request) {
        authenticationManager.authenticate(
               new UsernamePasswordAuthenticationToken(request.getLogin(), request.getMdp()));
        System.out.println("Voici le login de l'user = " + request.getLogin());
        UserDto userDto = userService.findByLogin(request.getLogin());
        System.out.println("ito ilay userDto pour login" + userDto);
        if (userDto.getState() != 10) {
            throw new InvalidEntityException("The state of the user must be 10");

        }
        var user = userDao.findByLogin(request.getLogin())
                .orElseThrow(() -> new IllegalArgumentException("Invalid login or password."));
        var jwt = jwtService.generateToken(user);
        historyService.saveHistory("Authentication", "success", user);
        return JwtAuthenticationResponse.builder().token(jwt).build();
    }
     */

    @Override
    public JwtAuthenticationResponse signin(SigninRequest request) {
        System.out.println(request);
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getLogin(), request.getMdp()));
        System.out.println("Authentification réussie pour l'utilisateur : " + request.getLogin());
        UtilisateurDTO userDto = userService.findByEmail(request.getLogin());
        if (userDto.getState() != 10) {
            throw new InvalidEntityException("L'état de l'utilisateur doit être 10");
        }
        var user = userDao.findByEmail(request.getLogin())
                .orElseThrow(() -> new IllegalArgumentException("Nom d'utilisateur ou mot de passe non valide."));
        var jwt = jwtService.generateToken(user);
        historyService.saveHistoryLogin("Authentication", "success", user, request.getLogin());
        return JwtAuthenticationResponse.builder()
                .login(user.getEmail())
                .token(jwt)
                .build();
    }
}