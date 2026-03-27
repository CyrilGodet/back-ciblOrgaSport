package com.glop.cibl_orga_sport.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import com.glop.cibl_orga_sport.data.Competition;
import com.glop.cibl_orga_sport.data.Incident;
import com.glop.cibl_orga_sport.data.Lieu;
import com.glop.cibl_orga_sport.data.enumType.NiveauImpactEnum;
import com.glop.cibl_orga_sport.dto.IncidentDTO;
import com.glop.cibl_orga_sport.repository.CompetitionRepository;
import com.glop.cibl_orga_sport.repository.IncidentRepository;
import com.glop.cibl_orga_sport.repository.LieuRepository;
import com.glop.cibl_orga_sport.service.impl.IncidentServiceImpl;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class IncidentServiceImplTest {

    @Mock
    private IncidentRepository incidentRepository;
    @Mock
    private CompetitionRepository competitionRepository;
    @Mock
    private LieuRepository lieuRepository;

    @InjectMocks
    private IncidentServiceImpl service;

    @Test
    void getIncidentsByCompetitionShouldReturnEmptyWhenCompetitionNotFound() {
        when(competitionRepository.findById(1L)).thenReturn(Optional.empty());

        List<IncidentDTO> result = service.getIncidentsByCompetition(1L);

        assertTrue(result.isEmpty());
    }

    @Test
    void createIncidentShouldPersist() {
        IncidentDTO dto = new IncidentDTO();
        dto.setIdCompetition(1L);
        dto.setIdLieu(2L);
        dto.setTitre("Coupure");
        dto.setDescription("Description");
        dto.setNiveauImpact(NiveauImpactEnum.TOUT_LE_MONDE);

        when(competitionRepository.findById(1L)).thenReturn(Optional.of(new Competition()));
        when(lieuRepository.findById(2L)).thenReturn(Optional.of(new Lieu()));
        when(incidentRepository.save(any(Incident.class))).thenAnswer(invocation -> invocation.getArgument(0));

        IncidentDTO result = service.createIncident(dto);

        assertNotNull(result);
        assertEquals("Coupure", result.getTitre());
    }

    @Test
    void resoudreIncidentShouldReturnNullWhenMissing() {
        when(incidentRepository.findById(10L)).thenReturn(Optional.empty());

        assertNull(service.resoudreIncident(10L));
    }

    @Test
    void getIncidentsForSportifShouldFilterByImpact() {
        Competition competition = new Competition();

        Incident i1 = new Incident();
        i1.setNiveauImpact(NiveauImpactEnum.TOUT_LE_MONDE);
        Incident i2 = new Incident();
        i2.setNiveauImpact(NiveauImpactEnum.COMMISSAIRES_ET_ATHLETES);
        Incident i3 = new Incident();
        i3.setNiveauImpact(NiveauImpactEnum.COMMISSAIRES_SEULEMENT);

        when(competitionRepository.findById(3L)).thenReturn(Optional.of(competition));
        when(incidentRepository.findByCompetition(competition)).thenReturn(List.of(i1, i2, i3));

        List<IncidentDTO> result = service.getIncidentsForSportif(3L);

        assertEquals(2, result.size());
    }

    @Test
    void getAllIncidentsShouldReturnMappedList() {
        when(incidentRepository.findAll()).thenReturn(List.of(new Incident()));

        List<IncidentDTO> result = service.getAllIncidents();

        assertEquals(1, result.size());
    }

    @Test
    void getIncidentsNonResolusShouldReturnMappedList() {
        when(incidentRepository.findByEstResoluFalse()).thenReturn(List.of(new Incident()));

        List<IncidentDTO> result = service.getIncidentsNonResolus();

        assertEquals(1, result.size());
    }

    @Test
    void getIncidentByIdShouldReturnOptionalWhenFound() {
        when(incidentRepository.findById(7L)).thenReturn(Optional.of(new Incident()));

        Optional<IncidentDTO> result = service.getIncidentById(7L);

        assertTrue(result.isPresent());
    }

    @Test
    void updateIncidentShouldReturnNullWhenMissing() {
        when(incidentRepository.findById(20L)).thenReturn(Optional.empty());

        assertNull(service.updateIncident(20L, new IncidentDTO()));
    }

    @Test
    void updateIncidentShouldUpdateWhenFound() {
        Incident incident = new Incident();
        when(incidentRepository.findById(21L)).thenReturn(Optional.of(incident));
        when(incidentRepository.save(incident)).thenReturn(incident);

        IncidentDTO dto = new IncidentDTO();
        dto.setTitre("Nouveau titre");
        dto.setDescription("Desc");
        dto.setNiveauImpact(NiveauImpactEnum.TOUT_LE_MONDE);
        dto.setResolu(true);
        dto.setIdLieu(2L);
        when(lieuRepository.findById(2L)).thenReturn(Optional.of(new Lieu()));

        IncidentDTO result = service.updateIncident(21L, dto);

        assertNotNull(result);
    }

    @Test
    void getIncidentsForVolontaireShouldFilterOnlyToutLeMonde() {
        Competition competition = new Competition();
        Incident i1 = new Incident();
        i1.setNiveauImpact(NiveauImpactEnum.TOUT_LE_MONDE);
        Incident i2 = new Incident();
        i2.setNiveauImpact(NiveauImpactEnum.COMMISSAIRES_ET_ATHLETES);

        when(competitionRepository.findById(4L)).thenReturn(Optional.of(competition));
        when(incidentRepository.findByCompetition(competition)).thenReturn(List.of(i1, i2));

        List<IncidentDTO> result = service.getIncidentsForVolontaire(4L);

        assertEquals(1, result.size());
    }

    @Test
    void deleteIncidentShouldDelegateToRepository() {
        service.deleteIncident(30L);

        verify(incidentRepository).deleteById(30L);
    }
}
