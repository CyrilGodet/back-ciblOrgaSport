package com.glop.cibl_orga_sport.service;

import com.glop.cibl_orga_sport.dto.AccountCreationDTO;
import com.glop.cibl_orga_sport.dto.UtilisateurDTO;
import java.util.Optional;

public interface CompteService {
    UtilisateurDTO createAccount(AccountCreationDTO dto);
    Optional<UtilisateurDTO> login(String username, String password);
}
