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

@CrossOrigin(origins = "http//localhost:4200")
@RestController
@RequestMapping(ROLES_ENDPOINT)
public class RolesController implements RolesApi {
    private RolesService rolesService;

    @Autowired
    public RolesController(RolesService rolesService) {
        this.rolesService = rolesService;
    }

    @Override
    public ResponseEntity<RolesDto> save(RolesDto rolesDto) {
        return ResponseEntity.ok(rolesService.save(rolesDto));
    }

    @Override
    public ResponseEntity<RolesDto> update(Long id, RolesDto rolesDto) {
        return ResponseEntity.ok(rolesService.update(id,rolesDto));
    }

    @Override
    public ResponseEntity<RolesDto> findById(Long id) {
        return ResponseEntity.ok(rolesService.findById(id));

    }

    @Override
    public ResponseEntity<List<RolesDto>> findAll() {
        return ResponseEntity.ok((rolesService.findAll()));
    }

    @Override
    public ResponseEntity<RolesDto> delete(Long id) {
        rolesService.delete(id);
        return ResponseEntity.ok(RolesDto.builder().build());
    }

    @Override
    public ResponseEntity<RolesDto> findDesignation(String designation) {
        return ResponseEntity.ok(rolesService.findByDesignation(designation));
    }
}
