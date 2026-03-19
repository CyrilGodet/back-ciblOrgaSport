package com.glop.cibl_orga_sport.service;

import com.glop.cibl_orga_sport.dto.VolontaireDTO;

import java.util.List;
import java.util.Optional;

public interface VolontaireService {
    List<VolontaireDTO> getAllVolontaires();
    Optional<VolontaireDTO> getVolontaireById(Long id);
    Optional<VolontaireDTO> getVolontaireByEmail(String email);
    VolontaireDTO createVolontaire(VolontaireDTO volontaireDTO);
    VolontaireDTO updateVolontaire(Long id, VolontaireDTO volontaireDTO);
    void deleteVolontaire(Long id);
}
