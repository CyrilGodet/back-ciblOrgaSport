package com.glop.cibl_orga_sport.repository;

import com.glop.cibl_orga_sport.data.ParticipantSportif;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

@Repository
public interface ParticipantSportifRepository extends JpaRepository<ParticipantSportif, Long> {
    @Query("SELECT ps FROM ParticipantSportif ps JOIN ps.sportif s WHERE LOWER(s.nom) LIKE LOWER(CONCAT('%', :query, '%')) OR LOWER(s.prenom) LIKE LOWER(CONCAT('%', :query, '%')) OR LOWER(s.email) LIKE LOWER(CONCAT('%', :query, '%'))")
    List<ParticipantSportif> searchParticipantSportifs(@Param("query") String query);

    @Query("SELECT ps FROM ParticipantSportif ps WHERE ps.sportif.idUtilisateur = :idSportif")
    java.util.Optional<ParticipantSportif> findBySportifId(@Param("idSportif") Long idSportif);
}
