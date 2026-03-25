package com.glop.cibl_orga_sport.service.impl;

import com.glop.cibl_orga_sport.data.Incident;
import com.glop.cibl_orga_sport.data.Competition;
import com.glop.cibl_orga_sport.data.Lieu;
import com.glop.cibl_orga_sport.data.enumType.IncidentStatutEnum;
import com.glop.cibl_orga_sport.data.enumType.NiveauImpactEnum;
import com.glop.cibl_orga_sport.dto.IncidentDTO;
import com.glop.cibl_orga_sport.mapper.IncidentMapper;
import com.glop.cibl_orga_sport.repository.IncidentRepository;
import com.glop.cibl_orga_sport.repository.CompetitionRepository;
import com.glop.cibl_orga_sport.repository.LieuRepository;
import com.glop.cibl_orga_sport.service.IncidentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class IncidentServiceImpl implements IncidentService {
    
    @Autowired
    private IncidentRepository incidentRepository;
    
    @Autowired
    private CompetitionRepository competitionRepository;
    
    @Autowired
    private LieuRepository lieuRepository;
    
    @Override
    public List<IncidentDTO> getAllIncidents() {
        return incidentRepository.findAll().stream()
                .map(IncidentMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<IncidentDTO> getIncidentsOuverts() {
        return incidentRepository.findByStatutIn(List.of(IncidentStatutEnum.OUVERT, IncidentStatutEnum.EN_COURS))
                .stream()
                .map(IncidentMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<IncidentDTO> getIncidentsHistorique() {
        return incidentRepository.findByStatutIn(List.of(IncidentStatutEnum.FERME, IncidentStatutEnum.ANNULE))
                .stream()
                .map(IncidentMapper::toDTO)
                .collect(Collectors.toList());
    }
    
    @Override
    public List<IncidentDTO> getIncidentsByCompetition(Long competitionId) {
        Optional<Competition> competition = competitionRepository.findById(competitionId);
        if (competition.isPresent()) {
            return incidentRepository.findByCompetition(competition.get()).stream()
                    .map(IncidentMapper::toDTO)
                    .collect(Collectors.toList());
        }
        return List.of();
    }
    
    @Override
    public List<IncidentDTO> getIncidentsNonResolus() {
        return incidentRepository.findByEstResoluFalse().stream()
                .map(IncidentMapper::toDTO)
                .collect(Collectors.toList());
    }
    
    @Override
    public Optional<IncidentDTO> getIncidentById(Long id) {
        return incidentRepository.findById(id)
                .map(IncidentMapper::toDTO);
    }
    
    @Override
    public IncidentDTO createIncident(IncidentDTO incidentDTO) {
        Incident incident = IncidentMapper.toEntity(incidentDTO);

        if (incidentDTO.getStatut() != null) {
            incident.setStatut(incidentDTO.getStatut());
        }
        incident.setEstResolu(incident.getStatut() == IncidentStatutEnum.FERME);
        
        if (incidentDTO.getIdCompetition() != null) {
            Optional<Competition> competition = competitionRepository.findById(incidentDTO.getIdCompetition());
            competition.ifPresent(incident::setCompetition);
        }
        
        if (incidentDTO.getIdLieu() != null) {
            Optional<Lieu> lieu = lieuRepository.findById(incidentDTO.getIdLieu());
            lieu.ifPresent(incident::setLieu);
        }
        
        Incident savedIncident = incidentRepository.save(incident);
        return IncidentMapper.toDTO(savedIncident);
    }
    
    @Override
    public IncidentDTO updateIncident(Long id, IncidentDTO incidentDTO) {
        Optional<Incident> existingIncident = incidentRepository.findById(id);
        if (existingIncident.isPresent()) {
            Incident incident = existingIncident.get();
            incident.setTitre(incidentDTO.getTitre());
            incident.setDescription(incidentDTO.getDescription());
            incident.setNiveauImpact(incidentDTO.getNiveauImpact());

            IncidentStatutEnum statut = incidentDTO.getStatut();
            if (statut != null) {
                incident.setStatut(statut);
                incident.setEstResolu(statut == IncidentStatutEnum.FERME || incidentDTO.isResolu());
            } else {
                incident.setEstResolu(incidentDTO.isResolu());
                if (incidentDTO.isResolu()) {
                    incident.setStatut(IncidentStatutEnum.FERME);
                }
            }
            
            if (incidentDTO.getIdLieu() != null) {
                Optional<Lieu> lieu = lieuRepository.findById(incidentDTO.getIdLieu());
                lieu.ifPresent(incident::setLieu);
            }
            
            Incident updatedIncident = incidentRepository.save(incident);
            return IncidentMapper.toDTO(updatedIncident);
        }
        return null;
    }
    
    @Override
    public IncidentDTO resoudreIncident(Long id) {
        Optional<Incident> existingIncident = incidentRepository.findById(id);
        if (existingIncident.isPresent()) {
            Incident incident = existingIncident.get();
            incident.setEstResolu(true);
            incident.setStatut(IncidentStatutEnum.FERME);
            Incident updatedIncident = incidentRepository.save(incident);
            return IncidentMapper.toDTO(updatedIncident);
        }
        return null;
    }
    
    @Override
    public void deleteIncident(Long id) {
        incidentRepository.deleteById(id);
    }
    
    @Override
    public List<IncidentDTO> getIncidentsForVolontaire(Long competitionId) {
        Optional<Competition> competition = competitionRepository.findById(competitionId);
        if (competition.isEmpty()) {
            return List.of();
        }
        
        List<Incident> incidents = incidentRepository.findByCompetition(competition.get());
        
        return incidents.stream()
                .filter(incident -> incident.getNiveauImpact() == NiveauImpactEnum.TOUT_LE_MONDE)
                .map(IncidentMapper::toDTO)
                .collect(Collectors.toList());
    }
    
    @Override
    public List<IncidentDTO> getIncidentsForSportif(Long competitionId) {
        Optional<Competition> competition = competitionRepository.findById(competitionId);
        if (competition.isEmpty()) {
            return List.of();
        }
        
        List<Incident> incidents = incidentRepository.findByCompetition(competition.get());
        
        return incidents.stream()
                .filter(incident -> {
                    NiveauImpactEnum niveau = incident.getNiveauImpact();
                    return niveau == NiveauImpactEnum.TOUT_LE_MONDE || 
                           niveau == NiveauImpactEnum.COMMISSAIRES_ET_ATHLETES;
                })
                .map(IncidentMapper::toDTO)
                .collect(Collectors.toList());
    }
}
