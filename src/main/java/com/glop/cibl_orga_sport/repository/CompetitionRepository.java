package com.glop.cibl_orga_sport.repository;

import com.glop.cibl_orga_sport.data.Competition;
import com.glop.cibl_orga_sport.data.enumType.CompetitionStatusEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface CompetitionRepository extends JpaRepository<Competition, Long> {
    List<Competition> findByStatut(CompetitionStatusEnum statut);

    @org.springframework.data.jpa.repository.Query("SELECT DISTINCT c FROM Competition c JOIN c.epreuves e WHERE e.commissaireId = :commissaireId")
    List<Competition> findByCommissaireId(@org.springframework.data.repository.query.Param("commissaireId") Long commissaireId);
}
