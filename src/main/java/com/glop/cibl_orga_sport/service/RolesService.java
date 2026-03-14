package com.glop.cibl_orga_sport.service;

import com.glop.cibl_orga_sport.dto.RolesDto;

import java.util.List;

public interface RolesService {
    RolesDto save(RolesDto rolesDto);

    RolesDto update(Integer id, RolesDto rolesDto);

    RolesDto findById(Integer id);

    List<RolesDto> findAll();

    void delete(Integer id);

    RolesDto findByDesignation(String designation);


}
