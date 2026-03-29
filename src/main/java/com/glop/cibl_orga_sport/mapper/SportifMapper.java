package com.glop.cibl_orga_sport.mapper;

import com.glop.cibl_orga_sport.data.Sportif;
import com.glop.cibl_orga_sport.dto.CertificatMedicalDTO;
import com.glop.cibl_orga_sport.dto.PasseportDTO;
import com.glop.cibl_orga_sport.dto.SportifDTO;

public class SportifMapper {

    public static SportifDTO toDTO(Sportif sportif) {
        if (sportif == null)
            return null;

        SportifDTO dto = new SportifDTO();
        UtilisateurMapper.mapToDTO(sportif, dto);
        dto.setCertificatMedical(new CertificatMedicalDTO(sportif.getCertificatMedicalContenu(), sportif.getCertificatMedicalStatus()));
        dto.setPasseport(new PasseportDTO(sportif.getPasseportContenu(), sportif.getPasseportStatus()));
        dto.setEstConformeCharteEuropeenne(sportif.isEstConformeCharteEuropeenne());
        dto.setAFouniPasseport(sportif.isaFouniPasseport());
        dto.setAFounicertificatMedical(sportif.isaFounicertificatMedical());
        return dto;
    }

    public static Sportif toEntity(SportifDTO dto) {
        if (dto == null)
            return null;

        Sportif sportif = new Sportif();
        UtilisateurMapper.mapToEntity(dto, sportif);
        if (dto.getCertificatMedical() != null) {
            sportif.setCertificatMedicalContenu(dto.getCertificatMedical().getContenu());
            sportif.setCertificatMedicalStatus(dto.getCertificatMedical().getStatus());
        }
        if (dto.getPasseport() != null) {
            sportif.setPasseportContenu(dto.getPasseport().getContenu());
            sportif.setPasseportStatus(dto.getPasseport().getStatus());
        }
        sportif.setEstConformeCharteEuropeenne(dto.isEstConformeCharteEuropeenne());
        sportif.setaFouniPasseport(dto.isAFouniPasseport());
        sportif.setaFounicertificatMedical(dto.isAFounicertificatMedical());
        return sportif;
    }
}
