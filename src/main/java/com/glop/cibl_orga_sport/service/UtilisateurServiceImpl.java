package com.glop.cibl_orga_sport.service;

import com.glop.cibl_orga_sport.data.Sportif;
import com.glop.cibl_orga_sport.data.Visiteur;
import com.glop.cibl_orga_sport.data.Commissaire;
import com.glop.cibl_orga_sport.data.Lieu;
import com.glop.cibl_orga_sport.dto.SportifDTO;
import com.glop.cibl_orga_sport.dto.UtilisateurDTO;
import com.glop.cibl_orga_sport.dto.VisiteurDTO;
import com.glop.cibl_orga_sport.exception.EntityNotFoundException;
import com.glop.cibl_orga_sport.exception.ErrorCodes;
import com.glop.cibl_orga_sport.repository.UtilisateurRepository;
import com.glop.cibl_orga_sport.repository.LieuRepository;
import com.glop.cibl_orga_sport.mapper.SportifMapper;
import com.glop.cibl_orga_sport.mapper.VisiteurMapper;
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

    @Override
    public Sportif createSportif(SportifDTO dto) {
        Sportif sportif = SportifMapper.toEntity(dto);
        if (dto.getLieu() != null && dto.getLieu().getIdLieu() != null) {
            Optional<Lieu> lieu = lieuRepository.findById(dto.getLieu().getIdLieu());
            lieu.ifPresent(sportif::setLieu);
        }
        return repository.save(sportif);
    }

    @Override
    public Visiteur createVisiteur(VisiteurDTO dto) {
        Visiteur visiteur = VisiteurMapper.toEntity(dto);
        if (dto.getLieu() != null && dto.getLieu().getIdLieu() != null) {
            Optional<Lieu> lieu = lieuRepository.findById(dto.getLieu().getIdLieu());
            lieu.ifPresent(visiteur::setLieu);
        }
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
