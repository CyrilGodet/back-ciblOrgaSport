package com.glop.cibl_orga_sport.repository;

import com.glop.cibl_orga_sport.data.Incident;
import com.glop.cibl_orga_sport.data.Competition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IncidentRepository extends JpaRepository<Incident, Long> {
    List<Incident> findByCompetition(Competition competition);
    List<Incident> findByEstResoluFalse();
    List<Incident> findByCompetitionAndEstResoluFalse(Competition competition);
}
