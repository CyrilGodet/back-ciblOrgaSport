package com.glop.cibl_orga_sport.service;

import com.glop.cibl_orga_sport.dto.ConformiteCharteEuropeenneDTO;
import com.glop.cibl_orga_sport.dto.SportifInscritDTO;

import java.util.List;

public interface CommissaireService {

    List<SportifInscritDTO> listerSportifsInscrits(Long idCompetition);

    ConformiteCharteEuropeenneDTO verifierConformiteCharteEuropeenne(Long idCompetition);

    void validerSaisieResultats(Long idCompetition, Long idEtapeEpreuve, Long idCommissaire, boolean saisieManuelle);
}
