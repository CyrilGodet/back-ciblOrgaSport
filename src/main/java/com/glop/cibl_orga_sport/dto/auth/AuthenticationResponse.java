package com.glop.cibl_orga_sport.dto.auth;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AuthenticationResponse {
    private String token;
}