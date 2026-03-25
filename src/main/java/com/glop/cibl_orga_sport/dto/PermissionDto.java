package com.glop.cibl_orga_sport.dto;

import com.glop.cibl_orga_sport.data.Permission;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class PermissionDto {
    private Long id;
    private int credential;
    private int status;
    private String caption;
    private RolesDto roles;


    public static PermissionDto fromEntity(Permission permission) {
        if (permission == null) {
            return null;
        }
        return PermissionDto.builder()
                .id(permission.getIdPermission())
                .credential(permission.getCredential())
                .caption(permission.getCaption())
                .status(permission.getStatus())
                .roles(RolesDto.fromEntity(permission.getRoles()))
                .build();
    }

    public static Permission toEntity(PermissionDto permissionDto) {
        if (permissionDto == null) {
            return null;
        }
        Permission permission = new Permission();
        permission.setIdPermission(permissionDto.getId());
        permission.setCredential(permissionDto.getCredential());
        permission.setStatus(permissionDto.getStatus());
        permission.setCaption(permissionDto.getCaption());
        permission.setRoles(RolesDto.toEntity(permissionDto.getRoles()));
        return permission;
    }
}