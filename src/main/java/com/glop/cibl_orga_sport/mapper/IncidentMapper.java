package com.glop.cibl_orga_sport.mapper;

import com.glop.cibl_orga_sport.data.Incident;
import com.glop.cibl_orga_sport.dto.IncidentDTO;

public class IncidentMapper {
    
    public static IncidentDTO toDTO(Incident incident) {
        if (incident == null) return null;
        
        IncidentDTO dto = new IncidentDTO();
        dto.setIdIncident(incident.getIdIncident());
        dto.setTitre(incident.getTitre());
        dto.setDescription(incident.getDescription());
        dto.setNiveauImpact(incident.getNiveauImpact());
        dto.setDateIncident(incident.getDateIncident());
        dto.setResolu(incident.isEstResolu());
        
        if (incident.getCompetition() != null) {
            dto.setIdCompetition(incident.getCompetition().getIdCompetition());
        }
        
        if (incident.getLieu() != null) {
            dto.setIdLieu(incident.getLieu().getIdLieu());
            dto.setNomLieu(incident.getLieu().getNomLieu());
        }
        
        return dto;
    }
    
    public static Incident toEntity(IncidentDTO dto) {
        if (dto == null) return null;
        
        Incident incident = new Incident();
        incident.setIdIncident(dto.getIdIncident());
        incident.setTitre(dto.getTitre());
        incident.setDescription(dto.getDescription());
        incident.setNiveauImpact(dto.getNiveauImpact());
        incident.setDateIncident(dto.getDateIncident());
        incident.setEstResolu(dto.isResolu());
        
        return incident;
    }
}
