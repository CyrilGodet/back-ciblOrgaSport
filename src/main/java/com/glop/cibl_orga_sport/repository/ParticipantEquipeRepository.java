package com.glop.cibl_orga_sport.repository;

import com.glop.cibl_orga_sport.data.ParticipantEquipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ParticipantEquipeRepository extends JpaRepository<ParticipantEquipe, Long> {
    List<ParticipantEquipe> findByNomEquipeContainingIgnoreCase(String query);

    @org.springframework.data.jpa.repository.Query("SELECT pe FROM ParticipantEquipe pe WHERE LOWER(pe.nomEquipe) LIKE LOWER(CONCAT('%', :query, '%')) AND SIZE(pe.sportifs) = :size")
    List<ParticipantEquipe> findByNomEquipeContainingIgnoreCaseAndTaille(String query, int size);

    @org.springframework.data.jpa.repository.Query("SELECT pe FROM ParticipantEquipe pe JOIN pe.sportifs s WHERE s.idUtilisateur = :sportifId")
    List<ParticipantEquipe> findEquipesBySportifId(@org.springframework.data.repository.query.Param("sportifId") Long sportifId);
}
