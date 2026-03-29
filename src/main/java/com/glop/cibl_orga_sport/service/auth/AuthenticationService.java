package com.glop.cibl_orga_sport.service.auth;

import com.glop.cibl_orga_sport.data.auth.request.SignUpRequest;
import com.glop.cibl_orga_sport.data.auth.request.SigninRequest;
import com.glop.cibl_orga_sport.data.auth.response.JwtAuthenticationResponse;

public interface AuthenticationService {
    JwtAuthenticationResponse signup(SignUpRequest request);

    JwtAuthenticationResponse signin(SigninRequest request);

}
