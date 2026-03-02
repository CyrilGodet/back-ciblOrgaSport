package com.glop.cibl_orga_sport.service;

import com.glop.cibl_orga_sport.dto.IncidentDTO;

import java.util.List;
import java.util.Optional;

public interface IncidentService {
    List<IncidentDTO> getAllIncidents();
    List<IncidentDTO> getIncidentsByCompetition(Long competitionId);
    List<IncidentDTO> getIncidentsNonResolus();
    Optional<IncidentDTO> getIncidentById(Long id);
    IncidentDTO createIncident(IncidentDTO incidentDTO);
    IncidentDTO updateIncident(Long id, IncidentDTO incidentDTO);
    IncidentDTO resoudreIncident(Long id);
    void deleteIncident(Long id);
    
    List<IncidentDTO> getIncidentsForVolontaire(Long competitionId);
    List<IncidentDTO> getIncidentsForSportif(Long competitionId);
}
