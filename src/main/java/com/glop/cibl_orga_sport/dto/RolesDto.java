package com.glop.cibl_orga_sport.dto;


import com.glop.cibl_orga_sport.data.Roles;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class RolesDto {
    private Long id;
    private String designation;

    public static RolesDto fromEntity(Roles roles) {
        if (roles == null) {
            return null;
        }
        return RolesDto.builder()
                .id(roles.getIdRoles())
                .designation(roles.getDesignation())
                .build();
    }

    public static Roles toEntity(RolesDto rolesDto) {
        if (rolesDto == null) {
            return null;
        }
        Roles roles = new Roles();
        roles.setIdRoles(rolesDto.getId());
        roles.setDesignation(rolesDto.getDesignation());
        return roles;
    }
}
