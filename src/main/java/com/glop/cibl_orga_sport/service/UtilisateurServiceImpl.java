package com.glop.cibl_orga_sport.service;

import com.glop.cibl_orga_sport.data.*;
import com.glop.cibl_orga_sport.dto.SportifDTO;
import com.glop.cibl_orga_sport.dto.UtilisateurDTO;
import com.glop.cibl_orga_sport.dto.VisiteurDTO;
import com.glop.cibl_orga_sport.exception.EntityAlreadyExistException;
import com.glop.cibl_orga_sport.exception.EntityNotFoundException;
import com.glop.cibl_orga_sport.exception.ErrorCodes;
import com.glop.cibl_orga_sport.repository.RolesDao;
import com.glop.cibl_orga_sport.repository.UtilisateurRepository;
import com.glop.cibl_orga_sport.repository.LieuRepository;
import com.glop.cibl_orga_sport.mapper.SportifMapper;
import com.glop.cibl_orga_sport.mapper.VisiteurMapper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class UtilisateurServiceImpl implements UtilisateurService {

    @Autowired
    private UtilisateurRepository repository;

    @Autowired
    private LieuRepository lieuRepository;

    @Autowired
    private RolesDao rolesRepository;  // 🔥 AJOUTEZ CET AUTOWIRE

    @Override
    @Transactional
    public Sportif createSportif(SportifDTO dto) {
        if (repository.existsByEmail(dto.getEmail())) {
            throw new EntityAlreadyExistException("Email déjà utilisé");
        }

        Lieu lieu = null;
        if (dto.getLieu() != null && dto.getLieu().getIdLieu() != null) {
            lieu = lieuRepository.findById(dto.getLieu().getIdLieu())
                    .orElseThrow(() -> new EntityNotFoundException("Lieu introuvable"));
        }

        Roles role = rolesRepository.findById(dto.getRoles().getId())  // ou dto.getIdRoles()
                .orElseThrow(() -> new EntityNotFoundException("Role introuvable"));

        Sportif sportif = new Sportif();
        sportif.setNom(dto.getNom());
        sportif.setPrenom(dto.getPrenom());
        sportif.setEmail(dto.getEmail());
        sportif.setAge(dto.getAge());
        sportif.setMdp(dto.getMdp());
        sportif.setLieu(lieu);
        sportif.setRoles(role);
        return repository.save(sportif);
    }


    @Override
    @Transactional
    public Visiteur createVisiteur(VisiteurDTO dto) {
        if (repository.existsByEmail(dto.getEmail())) {
            throw new EntityAlreadyExistException("Email déjà utilisé");
        }

        Lieu lieu = null;
        if (dto.getLieu() != null && dto.getLieu().getIdLieu() != null) {
            lieu = lieuRepository.findById(dto.getLieu().getIdLieu())
                    .orElseThrow(() -> new EntityNotFoundException("Lieu introuvable"));
        }

        Roles role = rolesRepository.findById(dto.getRoles().getId())
                .orElseThrow(() -> new EntityNotFoundException("Role introuvable"));

        Visiteur visiteur = new Visiteur();
        visiteur.setNom(dto.getNom());
        visiteur.setPrenom(dto.getPrenom());
        visiteur.setEmail(dto.getEmail());
        visiteur.setRoles(role);
        visiteur.setLieu(lieu);

        return repository.save(visiteur);
    }


    @Override
    public List<Sportif> getAllSportifs() {
        return repository.findAllSportifs();
    }

    @Override
    public List<Visiteur> getAllVisiteurs() {
        return repository.findAllVisiteurs();
    }

    @Override
    public List<Commissaire> getAllCommissaires() {
        return repository.findAllCommissaires();
    }

    @Override
    public UtilisateurDTO findByEmail(String email) {
        if (email == null) {
            log.error("Dear customer, login is null");
            return null;
        }
        return repository.findByEmail(email)
                .map(utilisateur -> UtilisateurDTO.fromEntity(utilisateur))  // ✅ Lambda
                .orElseThrow(() -> new EntityNotFoundException(
                        "Aucun utilisateur avec l'email = " + email + " n'a été trouvé dans la BDD",
                        ErrorCodes.USER_NOT_FOUND));
    }

    @Override
    public UserDetailsService userDetailsService() {
        return new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String username) {
                return repository.findByEmail(username)
                        .orElseThrow(() -> new UsernameNotFoundException("User not found"));
            }
        };
    }

}
