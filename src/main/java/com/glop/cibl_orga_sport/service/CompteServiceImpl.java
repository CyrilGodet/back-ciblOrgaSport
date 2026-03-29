package com.glop.cibl_orga_sport.service;

import com.glop.cibl_orga_sport.data.Compte;
import com.glop.cibl_orga_sport.data.Lieu;
import com.glop.cibl_orga_sport.data.Utilisateur;
import com.glop.cibl_orga_sport.data.Visiteur;
import com.glop.cibl_orga_sport.dto.AccountCreationDTO;
import com.glop.cibl_orga_sport.dto.UtilisateurDTO;
import com.glop.cibl_orga_sport.mapper.UtilisateurMapper;
import com.glop.cibl_orga_sport.repository.CompteRepository;
import com.glop.cibl_orga_sport.repository.LieuRepository;
import com.glop.cibl_orga_sport.repository.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class CompteServiceImpl implements CompteService {

    @Autowired
    private CompteRepository repository;

    @Autowired
    private UtilisateurRepository utilisateurRepository;

    @Autowired
    private LieuRepository lieuRepository;

    @Override
    public UtilisateurDTO createAccount(AccountCreationDTO dto) {
        // Pour l'instant on ne crée que des visiteurs
        Visiteur visiteur = new Visiteur();
        UtilisateurMapper.mapToEntity(dto, visiteur);

        if (dto.getLieu() != null && dto.getLieu().getIdLieu() != null) {
            Lieu lieu = lieuRepository.findById(dto.getLieu().getIdLieu()).orElse(null);
            visiteur.setLieu(lieu);
        }

       Utilisateur savedUser = utilisateurRepository.save(visiteur);

        Compte compte = new Compte();
        compte.setUtilisateur(savedUser);
        compte.setUsername(dto.getUsername());
        compte.setPassword(dto.getPassword());
        compte.setType("VISITEUR");
        compte.setDateCreation(LocalDateTime.now());
        compte.setActive(true);

        repository.save(compte);

        return UtilisateurMapper.toSpecificDTO(savedUser);
    }

    @Override
    public Optional<UtilisateurDTO> login(String username, String password) {
        return repository.findByUsername(username)
                .or(() -> repository.findByUtilisateurEmail(username))
                .filter(c -> c.getPassword().equals(password))
                .map(c -> UtilisateurMapper.toSpecificDTO(c.getUtilisateur()));
    }
}
