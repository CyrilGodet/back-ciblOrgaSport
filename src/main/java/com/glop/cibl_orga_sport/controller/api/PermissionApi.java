package com.glop.cibl_orga_sport.controller.api;
import com.glop.cibl_orga_sport.dto.PermissionDto;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.glop.cibl_orga_sport.utils.Constants.PERMISSION_ENDPOINT;

@RequestMapping(PERMISSION_ENDPOINT)
public interface PermissionApi {
    @PostMapping(value = "/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<PermissionDto> save(@RequestBody PermissionDto permissionDto);


    @PutMapping(value = "/update/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<PermissionDto> update(@PathVariable("id") Integer id, @RequestBody PermissionDto permissionDto);

    @GetMapping(value = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<PermissionDto>> findAll();

    @DeleteMapping(value = "/delete/{idPermission}")
    ResponseEntity delete(Integer id);

    @GetMapping(value = "/id/{idPermission}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<PermissionDto> findById(@PathVariable("idPermission") Integer id);

    @GetMapping(value = "/find-by-design/{roles}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<PermissionDto>> getLineLabelsGroupedByLabel(@PathVariable("roles") String roles);

}
