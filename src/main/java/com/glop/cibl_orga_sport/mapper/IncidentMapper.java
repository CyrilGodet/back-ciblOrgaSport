package com.glop.cibl_orga_sport.mapper;

import com.glop.cibl_orga_sport.data.Incident;
import com.glop.cibl_orga_sport.data.enumType.IncidentStatutEnum;
import com.glop.cibl_orga_sport.data.enumType.NiveauImpactEnum;
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
        dto.setStatut(incident.getStatut());
        dto.setStatutFront(toFrontStatut(incident.getStatut()));
        dto.setGravite(toFrontGravite(incident.getNiveauImpact()));
        
        if (incident.getCompetition() != null) {
            dto.setIdCompetition(incident.getCompetition().getIdCompetition());
        }
        
        if (incident.getLieu() != null) {
            dto.setIdLieu(incident.getLieu().getIdLieu());
            dto.setNomLieu(incident.getLieu().getNomLieu());
        }

        if (incident.getCreateur() != null) {
            dto.setIdCreateur(incident.getCreateur().getIdUtilisateur());
            dto.setNomCreateur(incident.getCreateur().getNom());
            dto.setPrenomCreateur(incident.getCreateur().getPrenom());
        }
        
        return dto;
    }
    
    public static Incident toEntity(IncidentDTO dto) {
        if (dto == null) return null;
        
        Incident incident = new Incident();
        incident.setIdIncident(dto.getIdIncident());
        incident.setTitre(dto.getTitre());
        incident.setDescription(dto.getDescription());
        incident.setNiveauImpact(dto.getNiveauImpact() != null ? dto.getNiveauImpact() : fromFrontGravite(dto.getGravite()));

        if (dto.getDateIncident() != null) {
            incident.setDateIncident(dto.getDateIncident());
        }

        IncidentStatutEnum statut = dto.getStatut() != null ? dto.getStatut() : fromFrontStatut(dto.getStatutFront());
        if (statut != null) {
            incident.setStatut(statut);
        }

        incident.setEstResolu(dto.isResolu() || incident.getStatut() == IncidentStatutEnum.FERME);
        
        return incident;
    }

    private static String toFrontStatut(IncidentStatutEnum statut) {
        if (statut == null) {
            return null;
        }
        return switch (statut) {
            case OUVERT -> "ouvert";
            case EN_COURS -> "en_cours_traitement";
            case FERME -> "ferme";
            case ANNULE -> "annule";
        };
    }

    private static String toFrontGravite(NiveauImpactEnum impact) {
        if (impact == null) {
            return null;
        }
        return switch (impact) {
            case COMMISSAIRES_SEULEMENT -> "leger";
            case COMMISSAIRES_ET_ATHLETES -> "modere";
            case TOUT_LE_MONDE -> "tres_grave";
        };
    }

    private static IncidentStatutEnum fromFrontStatut(String statutFront) {
        if (statutFront == null || statutFront.isBlank()) {
            return null;
        }
        return switch (statutFront) {
            case "ouvert" -> IncidentStatutEnum.OUVERT;
            case "en_cours_traitement" -> IncidentStatutEnum.EN_COURS;
            case "ferme" -> IncidentStatutEnum.FERME;
            case "annule" -> IncidentStatutEnum.ANNULE;
            default -> null;
        };
    }

    private static NiveauImpactEnum fromFrontGravite(String gravite) {
        if (gravite == null || gravite.isBlank()) {
            return null;
        }
        return switch (gravite) {
            case "leger" -> NiveauImpactEnum.COMMISSAIRES_SEULEMENT;
            case "modere", "grave" -> NiveauImpactEnum.COMMISSAIRES_ET_ATHLETES;
            case "tres_grave" -> NiveauImpactEnum.TOUT_LE_MONDE;
            default -> null;
        };
    }
}
