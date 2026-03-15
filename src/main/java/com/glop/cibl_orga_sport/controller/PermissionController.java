package com.glop.cibl_orga_sport.controller;

import com.glop.cibl_orga_sport.controller.api.PermissionApi;
import com.glop.cibl_orga_sport.dto.PermissionDto;
import com.glop.cibl_orga_sport.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.glop.cibl_orga_sport.utils.Constants.PERMISSION_ENDPOINT;

@RequestMapping(PERMISSION_ENDPOINT)
@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class PermissionController implements PermissionApi {
    private PermissionService permisssionService;

    @Autowired
    public PermissionController(PermissionService permisssionService) {
        this.permisssionService = permisssionService;
    }

    @Override
    public ResponseEntity<PermissionDto> save(PermissionDto permissionDto) {
        return ResponseEntity.ok(permisssionService.save(permissionDto));


    }

    @Override
    public ResponseEntity<PermissionDto> update(Integer id, PermissionDto permissionDto) {
        return ResponseEntity.ok(permisssionService.update(id,permissionDto));

    }

    @Override
    public ResponseEntity<List<PermissionDto>> findAll() {
        return ResponseEntity.ok(permisssionService.findAll());

    }

    @Override
    public ResponseEntity delete(Integer id) {
        permisssionService.delete(id);
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<PermissionDto> findById(Integer id) {
        return ResponseEntity.ok(permisssionService.findById(id));

    }

    @Override
    public ResponseEntity<List<PermissionDto>> getLineLabelsGroupedByLabel(String roles) {
        List<PermissionDto> groupedPermissions = permisssionService.getPermissionGroupedByRoles(roles);

        if (!groupedPermissions.isEmpty()) {
            return new ResponseEntity<>(groupedPermissions, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}