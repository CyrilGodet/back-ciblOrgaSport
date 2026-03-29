package com.glop.cibl_orga_sport.utils;

import com.glop.cibl_orga_sport.data.Roles;
import com.glop.cibl_orga_sport.data.Utilisateur;
import com.glop.cibl_orga_sport.repository.RolesDao;
import com.glop.cibl_orga_sport.repository.UtilisateurRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final RolesDao rolesDao;
    private final UtilisateurRepository utilisateurDao;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) {
        // 1. Créer les rôles
        Roles admin = createRole("admin");
        Roles commissaire = createRole("commissaire");
        Roles volontaire = createRole("volontaire");
        Roles sportif = createRole("sportif");
        Roles spectateur = createRole("spectateur");

        String defaultPassword = passwordEncoder.encode("string");

        // 2. Créer les utilisateurs (state=10 = approuvé)
        utilisateurDao.saveAll(List.of(
                createUser("Mina", "Solo", "admin@a.com", defaultPassword, admin),
                createUser("Tiana", "Rabe", "commi@c.com", defaultPassword, commissaire),
                createUser("Jean", "Dupont", "volontaire@v.com", defaultPassword, volontaire),
                createUser("Paul", "Martin", "sportif@s.com", defaultPassword, sportif),
                createUser("Lucie", "Bernard", "spectateur@s.com", defaultPassword, spectateur)
        ));

        System.out.println(" 5 rôles et 5 utilisateurs initialisés");
    }

    private Roles createRole(String designation) {
        Roles role = new Roles();
        role.setDesignation(designation);
        return rolesDao.save(role);
    }

    private Utilisateur createUser(String prenom, String nom, String email, String mdp, Roles roles) {
        Utilisateur user = new Utilisateur();
        user.setPrenom(prenom);
        user.setNom(nom);
        user.setEmail(email);
        user.setMdp(mdp);
        user.setRoles(roles);
        user.setState(10);
        return user;
    }
}
