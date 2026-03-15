package com.glop.cibl_orga_sport.dto.auth;

import com.glop.cibl_orga_sport.data.History;
import com.glop.cibl_orga_sport.data.Roles;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class AuthenticationRequest {
    private String name;

    private String lastname;

    private String login;

    private String mdp;

    private int state;

    private Roles roles;
    private List<History> historyList;

}
