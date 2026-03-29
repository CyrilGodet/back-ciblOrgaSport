package com.glop.cibl_orga_sport.repository;

import com.glop.cibl_orga_sport.data.EtapeEpreuve;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface PhaseRepository extends JpaRepository<EtapeEpreuve, Long> {
    List<EtapeEpreuve> findByEpreuveCompetitionIdCompetition(Long competitionId);
}
