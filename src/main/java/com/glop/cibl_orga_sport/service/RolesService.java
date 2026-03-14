package com.glop.cibl_orga_sport.service;

import com.glop.cibl_orga_sport.dto.RolesDto;

import java.util.List;

public interface RolesService {
    RolesDto save(RolesDto rolesDto);

    RolesDto update(Long id, RolesDto rolesDto);

    RolesDto findById(Long id);

    List<RolesDto> findAll();

    void delete(Long id);

    RolesDto findByDesignation(String designation);


}
