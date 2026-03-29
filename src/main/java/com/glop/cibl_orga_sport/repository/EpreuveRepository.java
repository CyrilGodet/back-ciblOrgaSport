package com.glop.cibl_orga_sport.repository;

import com.glop.cibl_orga_sport.data.Epreuve;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface EpreuveRepository extends JpaRepository<Epreuve, Long> {
    List<Epreuve> findByCompetitionIdCompetition(Long competitionId);
}
