package com.glop.cibl_orga_sport.service;

import com.glop.cibl_orga_sport.data.*;
import com.glop.cibl_orga_sport.dto.SportifDTO;
import com.glop.cibl_orga_sport.dto.UtilisateurDTO;
import com.glop.cibl_orga_sport.dto.VisiteurDTO;
import org.springframework.security.core.userdetails.UserDetailsService;

import com.glop.cibl_orga_sport.data.enumType.DocumentStatusEnum;
import java.util.List;

public interface UtilisateurService {
    Sportif createSportif(SportifDTO dto);
    Visiteur createVisiteur(VisiteurDTO dto);
    List<Sportif> getAllSportifs();
    List<Visiteur> getAllVisiteurs();
    List<Commissaire> getAllCommissaires();
    List<Sportif> searchSportifs(String query);
    List<ParticipantSportif> searchParticipantSportifs(String query);
    UtilisateurDTO findByEmail(String email);

    UserDetailsService userDetailsService();

    UtilisateurDTO findById(Long id);

    UserDtoJson updateNoMdp(Long id, UserDtoJson userDto);
    UserDtoJson approval(Long id);

    void updateCertificatMedical(Long id, byte[] content);
    void updatePasseport(Long id, byte[] content);
    void updateCharteConformite(Long id, boolean value);
    void updateCertificatMedicalStatus(Long id, DocumentStatusEnum status);
    void updatePasseportStatus(Long id, DocumentStatusEnum status);


    List<UserDtoJson> findAll();
}
