package com.glop.cibl_orga_sport.mapper;

import com.glop.cibl_orga_sport.data.Epreuve;
import com.glop.cibl_orga_sport.data.Periode;
import com.glop.cibl_orga_sport.dto.EpreuveDTO;
import java.sql.Date;

public class EpreuveMapper {
    
    public static EpreuveDTO toDTO(Epreuve epreuve) {
        if (epreuve == null) return null;
        
        EpreuveDTO dto = new EpreuveDTO();
        dto.setIdEpreuve(epreuve.getIdEpreuve());
        dto.setNomEpreuve(epreuve.getNomEpreuve());
        dto.setDiscipline(epreuve.getDiscipline());
        dto.setGenre(epreuve.getGenre());
        dto.setStatut(epreuve.getStatut());
        
        if (epreuve.getCompetition() != null) {
            dto.setCompetition(CompetitionMapper.toDTO(epreuve.getCompetition()));
        }
        
        if (epreuve.getPeriode() != null) {
            dto.setDateDebut(epreuve.getPeriode().getDateDebut());
            dto.setDateFin(epreuve.getPeriode().getDateFin());
        }
        
        return dto;
    }

    public static Epreuve toEntity(EpreuveDTO dto) {
        if (dto == null) return null;
        
        Epreuve epreuve = new Epreuve(dto.getNomEpreuve());
        epreuve.setDiscipline(dto.getDiscipline());
        epreuve.setGenre(dto.getGenre());
        epreuve.setStatut(dto.getStatut());
        
        if (dto.getDateDebut() != null && dto.getDateFin() != null) {
            Periode periode = new Periode(
                (Date) dto.getDateDebut(),
                (Date) dto.getDateFin()
            );
            epreuve.setPeriode(periode);
        }
        
        return epreuve;
    }
}
