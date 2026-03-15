package com.glop.cibl_orga_sport.data.auth.request;

import com.glop.cibl_orga_sport.dto.RolesDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SignUpRequest {
    private String name;

    private String lastname;

    private String login;

    private String mdp;

    private String verifyMdp;

    //@JsonIgnore
    private int state;

    //@JsonIgnore
    private RolesDto roles;
}