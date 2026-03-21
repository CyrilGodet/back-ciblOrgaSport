package com.glop.cibl_orga_sport.service;

import com.glop.cibl_orga_sport.data.Competition;
import com.glop.cibl_orga_sport.data.Epreuve;
import com.glop.cibl_orga_sport.data.enumType.CompetitionStatusEnum;
import com.glop.cibl_orga_sport.data.enumType.DisciplineEnum;
import com.glop.cibl_orga_sport.data.enumType.CompetitionGenreEnum;
import java.sql.Date;
import java.util.List;
import java.util.Optional;

public interface EpreuveService {

    Epreuve createEpreuve(String nomEpreuve, String description, DisciplineEnum discipline, CompetitionGenreEnum genre, 
                         Date dateDebut, Date dateFin, int ageMin, int ageMax, Competition competition);

    Epreuve updateEpreuve(Long id, String nomEpreuve, String description, DisciplineEnum discipline, CompetitionGenreEnum genre, 
                         Date dateDebut, Date dateFin, int ageMin, int ageMax, CompetitionStatusEnum statut, Competition competition);

    boolean deleteEpreuve(Long id);

    List<Epreuve> getAllEpreuves();

    Optional<Epreuve> getEpreuve(Long id);

    List<Epreuve> getEpreuvesByCompetitionId(Long competitionId);
    
    Epreuve publishEpreuve(Long id);
    Epreuve startEpreuve(Long id);
    Epreuve finishEpreuve(Long id);
}
