package com.glop.cibl_orga_sport.controller;

import com.glop.cibl_orga_sport.controller.api.RolesApi;
import com.glop.cibl_orga_sport.dto.RolesDto;
import com.glop.cibl_orga_sport.service.RolesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.glop.cibl_orga_sport.utils.Constants.ROLES_ENDPOINT;

import com.glop.cibl_orga_sport.dto.RolesDto;
import com.glop.cibl_orga_sport.service.RolesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import static com.glop.cibl_orga_sport.utils.Constants.ROLES_ENDPOINT;

@RestController
@RequestMapping(ROLES_ENDPOINT)  // ✅ "/api/roles"
@CrossOrigin(origins = "http://localhost:4200")
public class RolesController {

    @Autowired
    private RolesService rolesService;

    @PostMapping("/create")
    public ResponseEntity<RolesDto> save(@RequestBody RolesDto rolesDto) {
        return ResponseEntity.ok(rolesService.save(rolesDto));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<RolesDto> update(@PathVariable("id") Long id, @RequestBody RolesDto rolesDto) {
        return ResponseEntity.ok(rolesService.update(id, rolesDto));
    }

    @GetMapping("/id/{idRoles}")
    public ResponseEntity<RolesDto> findById(@PathVariable("idRoles") Long id) {
        return ResponseEntity.ok(rolesService.findById(id));
    }

    @GetMapping("/all")
    public ResponseEntity<List<RolesDto>> findAll() {
        return ResponseEntity.ok(rolesService.findAll());
    }

    @DeleteMapping("/delete/{idRoles}")
    public ResponseEntity<RolesDto> delete(@PathVariable("idRoles") Long id) {
        rolesService.delete(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{designation}")
    public ResponseEntity<RolesDto> findDesignation(@PathVariable("designation") String designation) {
        return ResponseEntity.ok(rolesService.findByDesignation(designation));
    }
}