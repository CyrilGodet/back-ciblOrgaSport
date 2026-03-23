package com.glop.cibl_orga_sport.mapper;

import com.glop.cibl_orga_sport.data.Billet;
import com.glop.cibl_orga_sport.dto.BilletDTO;

public class BilletMapper {

    public static BilletDTO toDTO(Billet billet) {
        if (billet == null) {
            return null;
        }

        Long visiteurId = billet.getVisiteur() != null ? billet.getVisiteur().getIdVisiteur() : null;
        Long epreuveId = billet.getEpreuve() != null ? billet.getEpreuve().getIdEpreuve() : null;

        return new BilletDTO(
                billet.getIdBillet(),
                visiteurId,
                epreuveId,
                billet.getNumeroBillet(),
                billet.getCategorie(),
                billet.getDateAchat());
    }

    public static Billet toEntity(BilletDTO dto) {
        if (dto == null) {
            return null;
        }

        Billet billet = new Billet();
        billet.setIdBillet(dto.getIdBillet());
        billet.setNumeroBillet(dto.getNumeroBillet());
        billet.setCategorie(dto.getCategorie());
        billet.setDateAchat(dto.getDateAchat());
        return billet;
    }
}
