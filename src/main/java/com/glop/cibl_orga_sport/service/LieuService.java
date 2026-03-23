package com.glop.cibl_orga_sport.service;

import com.glop.cibl_orga_sport.data.Lieu;
import com.glop.cibl_orga_sport.dto.LieuDTO;
import java.util.List;
import java.util.Optional;

public interface LieuService {

    Lieu updateLieu(Long id, String nom, String ville, String adresse);
    boolean deleteLieu(Long id);
    List<Lieu> getAllLieux();
    List<Lieu> getLieuxForAffectations();
    Optional<Lieu> getLieu(Long id);
    List<Lieu> searchLieux(String query);
    Lieu createLieu(LieuDTO lieuDTO);
}
