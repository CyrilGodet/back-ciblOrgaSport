package com.glop.cibl_orga_sport.repository;

import com.glop.cibl_orga_sport.data.Phase;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface PhaseRepository extends JpaRepository<Phase, Long> {
    List<Phase> findByEpreuveCompetitionIdCompetition(Long competitionId);
}
