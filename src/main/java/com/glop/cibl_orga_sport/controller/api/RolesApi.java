package com.glop.cibl_orga_sport.controller.api;

import com.glop.cibl_orga_sport.dto.RolesDto;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.glop.cibl_orga_sport.utils.Constants.ROLES_ENDPOINT;


@RequestMapping(ROLES_ENDPOINT)
public interface RolesApi {
    @PostMapping(value = "/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<RolesDto> save(@RequestBody RolesDto rolesDto);

    @PutMapping(value = "/update/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<RolesDto> update(@PathVariable("id") Long id, @RequestBody RolesDto rolesDto);

    @GetMapping(value = "/id/{idRoles}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<RolesDto> findById(@PathVariable("idRoles") Long id);

    @GetMapping(value = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<RolesDto>> findAll();

    @DeleteMapping(value = "/delete/{idRoles}")
    ResponseEntity<RolesDto> delete(@PathVariable("idRoles") Long id);

    @GetMapping(value ="/{designation}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<RolesDto> findDesignation(@PathVariable("designation") String designation);
}
