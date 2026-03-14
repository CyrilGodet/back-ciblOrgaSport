package com.glop.cibl_orga_sport.service.Impl;

import com.glop.cibl_orga_sport.data.Permission;
import com.glop.cibl_orga_sport.data.Roles;
import com.glop.cibl_orga_sport.dto.PermissionDto;
import com.glop.cibl_orga_sport.dto.RolesDto;
import com.glop.cibl_orga_sport.exception.EntityNotFoundException;
import com.glop.cibl_orga_sport.exception.ErrorCodes;
import com.glop.cibl_orga_sport.exception.InvalidEntityException;
import com.glop.cibl_orga_sport.repository.PermissionDao;
import com.glop.cibl_orga_sport.repository.RolesDao;
import com.glop.cibl_orga_sport.service.PermissionService;
import com.glop.cibl_orga_sport.validator.PermissionValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class PermissionImplService implements PermissionService {
    private PermissionDao permissionDao;
    private RolesDao rolesDao;

    @Autowired
    public PermissionImplService(PermissionDao permissionDao, RolesDao rolesDao) {
        this.permissionDao = permissionDao;
        this.rolesDao = rolesDao;
    }

    @Override
    public PermissionDto save(PermissionDto permissionDto) {
        List<String> errors = PermissionValidator.validate(permissionDto);
        if (!errors.isEmpty()) {
            log.error("permission is not valid", permissionDto);
            throw new InvalidEntityException("permission is not valid", ErrorCodes.PERMISSION_NOT_VALID, errors);
        }
        Long idRoles = permissionDto.getRoles().getId();
        Optional<Roles> role = rolesDao.findById(idRoles);
        Permission permissionEntity = PermissionDto.toEntity(permissionDto);
        permissionEntity.setRoles(role.get());
        permissionEntity.setStatus(5);
        Permission savedPermission = permissionDao.save(permissionEntity);
        PermissionDto dto = PermissionDto.fromEntity(savedPermission);
        return dto;
    }

    @Override
    public PermissionDto update(Integer id, PermissionDto permissionDto) {
        log.info("Inside update role{}", id);
        Optional<Permission> permissionOptional = permissionDao.findById(id);
        if (permissionOptional.isPresent()) {
            log.info("permission found with id {}", id);
            Permission permission = permissionOptional.get();
            permission.setCaption(permissionDto.getCaption());
            permission.setCredential(permissionDto.getCredential());
            permission.setStatus(permissionDto.getStatus());
            permission.setRoles(RolesDto.toEntity(permissionDto.getRoles()));
            Permission savedPermission = permissionDao.save(permission);
            log.info("permission with the id {}", id);
            return PermissionDto.fromEntity(permission);
        }else {
            log.info("Permission with id {} not found", id);
            return null;
        }
    }


    @Override
    public PermissionDto findById(Integer id) {
        if (id == null) {
            log.error("ID is null");
            return null;
        }
        return permissionDao.findById(id)
                .map(PermissionDto::fromEntity)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Aucun permission avec l'ID " + id + "n'est trouve dans la BDD",
                        ErrorCodes.PERMISSION_NOT_FOUND
                ));
    }

    @Override
    public List<PermissionDto> findAll() {
        return permissionDao.findAll().stream()
                .map(PermissionDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Integer id) {
        if (id == null) {
            log.error("permission id is null ");
            return;
        }
        permissionDao.deleteById(id);

    }

    @Override
    public List<PermissionDto> getPermissionGroupedByRoles(String role) {
        List<PermissionDto> listePermissionDesign = permissionDao.findPermissionGroupedByRoles(role).stream()
                .map(PermissionDto::fromEntity)
                .collect(Collectors.toList());
        return listePermissionDesign;
    }
}