package com.glop.cibl_orga_sport.service;

import com.glop.cibl_orga_sport.data.ParticipantSportif;
import com.glop.cibl_orga_sport.data.Sportif;
import com.glop.cibl_orga_sport.data.UserDtoJson;
import com.glop.cibl_orga_sport.data.Visiteur;
import com.glop.cibl_orga_sport.data.Commissaire;
import com.glop.cibl_orga_sport.dto.SportifDTO;
import com.glop.cibl_orga_sport.dto.UtilisateurDTO;
import com.glop.cibl_orga_sport.dto.VisiteurDTO;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UtilisateurService {
    Sportif createSportif(SportifDTO dto);
    Visiteur createVisiteur(VisiteurDTO dto);
    List<Sportif> getAllSportifs();
    List<Visiteur> getAllVisiteurs();
    List<Commissaire> getAllCommissaires();
    UtilisateurDTO findByEmail(String email);

    UserDetailsService userDetailsService();

    UtilisateurDTO findById(Integer id);

    UserDtoJson updateNoMdp(Long id, UserDtoJson userDto);
    UserDtoJson approval(Long id);


    List<UserDtoJson> findAll();

    List<Sportif> searchSportifs(String query);
    List<ParticipantSportif> searchParticipantSportifs(String query);
}
