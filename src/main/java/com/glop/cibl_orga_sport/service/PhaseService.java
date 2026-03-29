package com.glop.cibl_orga_sport.service;

import com.glop.cibl_orga_sport.data.EtapeEpreuve;
import com.glop.cibl_orga_sport.data.Epreuve;
import com.glop.cibl_orga_sport.data.Resultat;
import com.glop.cibl_orga_sport.data.enumType.EtapeEpreuveEnum;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

public interface PhaseService {

    EtapeEpreuve createPhase(Epreuve epreuve, Date dateDebut, Date dateFin, 
                            EtapeEpreuveEnum etapeEnum, Resultat resultat);

    EtapeEpreuve updatePhase(Long id, Epreuve epreuve, Date dateDebut, Date dateFin,
                            EtapeEpreuveEnum etapeEnum, Resultat resultat);

    boolean deletePhase(Long id);

    List<EtapeEpreuve> getAllPhases();

    Optional<EtapeEpreuve> getPhase(Long id);

    List<EtapeEpreuve> getPhasesByCompetitionId(Long competitionId);
}
