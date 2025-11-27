package com.glop.cibl_orga_sport.service;

import com.glop.cibl_orga_sport.data.Epreuve;
import java.util.List;
import java.util.Optional;

public interface EpreuveService {

    Epreuve createEpreuve(String nomEpreuve);
    Epreuve updateEpreuve(Long id, String nomEpreuve);
    boolean deleteEpreuve(Long id);
    List<Epreuve> getAllEpreuves();
    Optional<Epreuve> getEpreuve(Long id);
}
