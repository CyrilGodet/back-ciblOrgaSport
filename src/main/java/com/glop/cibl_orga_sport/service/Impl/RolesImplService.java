package com.glop.cibl_orga_sport.service.Impl;

import com.glop.cibl_orga_sport.data.Roles;
import com.glop.cibl_orga_sport.dto.RolesDto;
import com.glop.cibl_orga_sport.exception.EntityNotFoundException;
import com.glop.cibl_orga_sport.exception.ErrorCodes;
import com.glop.cibl_orga_sport.exception.InvalidEntityException;
import com.glop.cibl_orga_sport.repository.RolesDao;
import com.glop.cibl_orga_sport.service.HistoryService;
import com.glop.cibl_orga_sport.service.RolesService;
import com.glop.cibl_orga_sport.validator.RolesValidator;
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
public class RolesImplService implements RolesService {
    private RolesDao rolesDao;
    private HistoryService historyService;

    @Autowired
    public RolesImplService(RolesDao rolesDao, HistoryService historyService) {
        this.rolesDao = rolesDao;
        this.historyService = historyService;
    }

    @Override
    public RolesDto save(RolesDto rolesDto) {
        String desination = rolesDto.getDesignation();
        if (rolesDao.existsByDesignation(desination)) {
            throw new InvalidEntityException("Une autre rôle a été déjà créer");
        }

        List<String> errors = RolesValidator.validate(rolesDto);
        if (!errors.isEmpty()) {
            log.error("Roles is not valid my dear", rolesDto);
            historyService.saveHistory("Roles add", "failed");
            throw new InvalidEntityException("La roles n'est pas valide", ErrorCodes.ROLES_NOT_VALID, errors);
        }
        Roles rolesEntity = RolesDto.toEntity(rolesDto);
        Roles savedRoles = rolesDao.save(rolesEntity);
        //historyService.saveHistory("register User", "success", savedRoles);
        Long id = savedRoles.getIdRoles();
        RolesDto dto = RolesDto.fromEntity(savedRoles);
        return dto;
    }

    @Override
    public RolesDto update(Long id, RolesDto rolesDto) {
        log.info("Inside update roles{}", id);
        Optional<Roles> rolesOptional = rolesDao.findById(id);
        if (rolesOptional.isPresent()) {
            log.info("Roles found with id {}", id);
            Roles roles = rolesOptional.get();
            roles.setDesignation(rolesDto.getDesignation());
            rolesDao.save(roles);
            log.info("Roles with id {}", id);
            return RolesDto.fromEntity(roles);
        } else {
            log.info("Roles with id {} not found", id);
            return null;
        }
    }

    @Override
    public RolesDto findById(Long id) {
        if (id == null) {
            log.error("Roles ID is null");
            return null;
        }

        Optional<Roles> roles = rolesDao.findById(id);
        RolesDto rolesDto = RolesDto.fromEntity(roles.get());
        return Optional.of(rolesDto).orElseThrow(() ->
                new EntityNotFoundException("Aucun category avec l'Id = " + id + "dans la base", ErrorCodes.CATEGORY_NOT_FOUND));

    }

    @Override
    public List<RolesDto> findAll() {
        List<RolesDto> listeRoles = rolesDao.findAll().stream()
                .map(RolesDto::fromEntity)
                .collect(Collectors.toList());
        return listeRoles;
    }

    @Override
    public void delete(Long id) {
        if (id == null){
            log.error("Role ID is null");
            return;
        }
        rolesDao.deleteById(id);
    }

    @Override
    public RolesDto findByDesignation(String designation) {
        if (designation == null) {
            log.error("designation is null");
            return null;
        }
        return rolesDao.findByDesignation(designation)
                .map(RolesDto::fromEntity)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Aucun roles avec cette designation = " + designation +"is not found in the BD",
                        ErrorCodes.ROLES_NOT_FOUND
                ));
    }
}
