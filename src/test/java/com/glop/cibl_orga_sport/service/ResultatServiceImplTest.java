package com.glop.cibl_orga_sport.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import com.glop.cibl_orga_sport.data.EtapeEpreuve;
import com.glop.cibl_orga_sport.data.Match;
import com.glop.cibl_orga_sport.data.Resultat;
import com.glop.cibl_orga_sport.data.enumType.ResultatStatusEnum;
import com.glop.cibl_orga_sport.dto.ResultatDTO;
import com.glop.cibl_orga_sport.repository.EpreuveRepository;
import com.glop.cibl_orga_sport.repository.EtapeEpreuveRepository;
import com.glop.cibl_orga_sport.repository.MatchRepository;
import com.glop.cibl_orga_sport.repository.ParticipantEquipeRepository;
import com.glop.cibl_orga_sport.repository.ParticipationRepository;
import com.glop.cibl_orga_sport.repository.ResultatRepository;
import java.util.ArrayList;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ResultatServiceImplTest {

    @Mock
    private MatchRepository matchRepository;
    @Mock
    private EtapeEpreuveRepository etapeEpreuveRepository;
    @Mock
    private EpreuveRepository epreuveRepository;
    @Mock
    private ResultatRepository resultatRepository;
    @Mock
    private ParticipantEquipeRepository equipeRepository;
    @Mock
    private ParticipationRepository participationRepository;

    @InjectMocks
    private ResultatServiceImpl service;

    @Test
    void saveDraftResultatShouldThrowWhenMatchMissing() {
        when(matchRepository.findById(10L)).thenReturn(Optional.empty());

        assertThrows(IllegalArgumentException.class, () -> service.saveDraftResultat(10L, new ResultatDTO()));
    }

    @Test
    void saveDraftResultatShouldSaveDraftAndFinishMatch() {
        Match match = new Match();
        match.setResultat(null);

        when(matchRepository.findById(1L)).thenReturn(Optional.of(match));
        when(resultatRepository.save(any(Resultat.class))).thenAnswer(invocation -> invocation.getArgument(0));
        when(matchRepository.save(any(Match.class))).thenAnswer(invocation -> invocation.getArgument(0));

        ResultatDTO dto = new ResultatDTO();
        dto.setStatus(ResultatStatusEnum.DRAFT);

        ResultatDTO result = service.saveDraftResultat(1L, dto);

        assertNotNull(result);
        assertEquals(ResultatStatusEnum.DRAFT, result.getStatus());
    }

    @Test
    void completeResultatShouldThrowWhenNoResultat() {
        Match match = new Match();
        match.setResultat(null);
        when(matchRepository.findById(2L)).thenReturn(Optional.of(match));

        assertThrows(IllegalStateException.class, () -> service.completeResultat(2L));
    }

    @Test
    void completeResultatShouldSetCompletedStatus() {
        Match match = new Match();
        Resultat resultat = new Resultat();
        resultat.setStatus(ResultatStatusEnum.DRAFT);
        match.setResultat(resultat);

        when(matchRepository.findById(3L)).thenReturn(Optional.of(match));
        when(resultatRepository.save(any(Resultat.class))).thenAnswer(invocation -> invocation.getArgument(0));

        ResultatDTO result = service.completeResultat(3L);

        assertEquals(ResultatStatusEnum.COMPLETED, result.getStatus());
    }

    @Test
    void publishResultatsForEtapeShouldThrowWhenNoMatches() {
        EtapeEpreuve etape = new EtapeEpreuve();
        etape.setMatches(new ArrayList<>());

        when(etapeEpreuveRepository.findById(4L)).thenReturn(Optional.of(etape));

        assertThrows(IllegalStateException.class, () -> service.publishResultatsForEtape(4L));
    }
}
