package com.glop.cibl_orga_sport.dto;

import com.glop.cibl_orga_sport.data.enumType.DocumentStatusEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CertificatMedicalDTO {
    private byte[] contenu;
    private DocumentStatusEnum status;
}
