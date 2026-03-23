package com.glop.cibl_orga_sport.data;

import com.glop.cibl_orga_sport.dto.RolesDto;
import lombok.Data;

@Data
public class UserDtoJson {
    private Long id;
    private String name;
    private String lastname;
    private String login;
    private RolesDto roles;
    private int state;
}
