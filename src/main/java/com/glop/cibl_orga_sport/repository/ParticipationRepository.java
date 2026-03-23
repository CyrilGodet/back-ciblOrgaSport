package com.glop.cibl_orga_sport.repository;

import com.glop.cibl_orga_sport.data.Participation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ParticipationRepository extends JpaRepository<Participation, Long> {
    List<Participation> findByEpreuve_IdEpreuve(Long idEpreuve);

    List<Participation> findByEquipe_IdEquipe(Long idEquipe);
}
