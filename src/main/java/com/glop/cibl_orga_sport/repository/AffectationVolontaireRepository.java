package com.glop.cibl_orga_sport.repository;

import com.glop.cibl_orga_sport.data.AffectationVolontaire;
import com.glop.cibl_orga_sport.data.Volontaire;
import com.glop.cibl_orga_sport.data.Epreuve;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface AffectationVolontaireRepository extends JpaRepository<AffectationVolontaire, Long> {
    List<AffectationVolontaire> findByVolontaire(Volontaire volontaire);
    List<AffectationVolontaire> findByEpreuve(Epreuve epreuve);
    List<AffectationVolontaire> findByVolontaireAndDateAffectation(Volontaire volontaire, LocalDate date);
    List<AffectationVolontaire> findByVolontaireAndDateAffectationGreaterThanEqual(Volontaire volontaire, LocalDate date);
    
    @Query("SELECT a FROM AffectationVolontaire a WHERE a.epreuve.competition.idCompetition = :competitionId")
    List<AffectationVolontaire> findByCompetitionId(@Param("competitionId") Long competitionId);
}
