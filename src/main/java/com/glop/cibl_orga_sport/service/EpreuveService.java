package com.glop.cibl_orga_sport.service;

import com.glop.cibl_orga_sport.data.Competition;
import com.glop.cibl_orga_sport.data.Epreuve;
import com.glop.cibl_orga_sport.data.enumType.CompetitionStatusEnum;
import com.glop.cibl_orga_sport.data.enumType.DisciplineEnum;
import com.glop.cibl_orga_sport.data.enumType.CompetitionGenreEnum;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface EpreuveService {

    Epreuve createEpreuve(String nomEpreuve, DisciplineEnum discipline, CompetitionGenreEnum genre, 
                         Date dateDebut, Date dateFin, Competition competition);

    Epreuve updateEpreuve(Long id, String nomEpreuve, DisciplineEnum discipline, CompetitionGenreEnum genre, 
                         Date dateDebut, Date dateFin, CompetitionStatusEnum statut, Competition competition);

    boolean deleteEpreuve(Long id);

    List<Epreuve> getAllEpreuves();

    Optional<Epreuve> getEpreuve(Long id);

    List<Epreuve> getEpreuvesByCompetitionId(Long competitionId);
}
