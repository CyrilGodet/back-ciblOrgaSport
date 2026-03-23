package com.glop.cibl_orga_sport.service;

import com.glop.cibl_orga_sport.dto.AffectationVolontaireDTO;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface AffectationVolontaireService {
    List<AffectationVolontaireDTO> getAllAffectations();
    Optional<AffectationVolontaireDTO> getAffectationById(Long id);
    List<AffectationVolontaireDTO> getAffectationsByVolontaire(Long volontaireId);
    List<AffectationVolontaireDTO> getAffectationsByCompetition(Long competitionId);
    List<AffectationVolontaireDTO> getProgrammeVolontaire(Long volontaireId, LocalDate date);
    List<AffectationVolontaireDTO> getProgrammeVolontaireFutur(Long volontaireId);
    AffectationVolontaireDTO createAffectation(AffectationVolontaireDTO affectationDTO);
    AffectationVolontaireDTO updateAffectation(Long id, AffectationVolontaireDTO affectationDTO);
    void deleteAffectation(Long id);
}
