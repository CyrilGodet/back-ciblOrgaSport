package com.glop.cibl_orga_sport.service;
import com.glop.cibl_orga_sport.dto.PermissionDto;

import java.util.List;

public interface PermissionService {

    PermissionDto save(PermissionDto permissionDto);

    PermissionDto update(Integer id,PermissionDto permissionDto);

    PermissionDto findById(Integer id);

    List<PermissionDto> findAll();

    void delete(Integer id);

    List<PermissionDto> getPermissionGroupedByRoles(String role);
}
