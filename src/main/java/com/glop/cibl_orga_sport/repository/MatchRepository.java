package com.glop.cibl_orga_sport.repository;

import com.glop.cibl_orga_sport.data.Match;
import com.glop.cibl_orga_sport.data.enumType.MatchStatusEnum;
import com.glop.cibl_orga_sport.data.enumType.EtapeEpreuveEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface MatchRepository extends JpaRepository<Match, Long> {
    List<Match> findByEtapeEpreuveEpreuveCompetitionIdCompetitionAndStatus(Long competitionId, MatchStatusEnum status);
    List<Match> findByEtapeEpreuveEpreuveCompetitionIdCompetitionAndEtapeEpreuveEtapeEpreuveEnum(Long competitionId, EtapeEpreuveEnum etapeEnum);
    List<Match> findByEtapeEpreuveEpreuveIdEpreuveAndEtapeEpreuveEtapeEpreuveEnum(Long epreuveId, EtapeEpreuveEnum etapeEnum);
}
