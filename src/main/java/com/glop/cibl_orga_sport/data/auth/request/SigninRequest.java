package com.glop.cibl_orga_sport.data.auth.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SigninRequest {
    private String login;
    private String mdp;

    @Override
    public String toString() {
        return "SigninRequest(login=" + login + ", mdp=***MASQUÉ***)";
    }
}
