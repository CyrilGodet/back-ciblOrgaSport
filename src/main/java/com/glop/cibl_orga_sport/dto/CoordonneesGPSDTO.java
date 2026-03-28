package com.glop.cibl_orga_sport.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CoordonneesGPSDTO {
    private Double latitude;
    private Double longitude;
}
