package com.glop.cibl_orga_sport.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import com.glop.cibl_orga_sport.data.Roles;
import com.glop.cibl_orga_sport.dto.RolesDto;
import com.glop.cibl_orga_sport.exception.EntityNotFoundException;
import com.glop.cibl_orga_sport.exception.InvalidEntityException;
import com.glop.cibl_orga_sport.repository.RolesDao;
import com.glop.cibl_orga_sport.service.Impl.RolesImplService;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class RolesImplServiceTest {

    @Mock
    private RolesDao rolesDao;
    @Mock
    private HistoryService historyService;

    @InjectMocks
    private RolesImplService service;

    @Test
    void saveShouldThrowWhenDuplicateDesignation() {
        when(rolesDao.existsByDesignation("ADMIN")).thenReturn(true);

        RolesDto dto = RolesDto.builder().designation("ADMIN").build();

        assertThrows(InvalidEntityException.class, () -> service.save(dto));
    }

    @Test
    void saveShouldPersistWhenValid() {
        RolesDto dto = RolesDto.builder().designation("USER").build();

        Roles saved = new Roles();
        saved.setIdRoles(5);
        saved.setDesignation("USER");

        when(rolesDao.existsByDesignation("USER")).thenReturn(false);
        when(rolesDao.save(any(Roles.class))).thenReturn(saved);

        RolesDto result = service.save(dto);

        assertNotNull(result);
        assertEquals(5, result.getId());
    }

    @Test
    void findByIdShouldThrowWhenMissing() {
        when(rolesDao.findById(99)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> service.findById(99));
    }

    @Test
    void findByDesignationShouldReturnNullWhenInputIsNull() {
        assertNull(service.findByDesignation(null));
    }

    @Test
    void deleteShouldSkipWhenIdNull() {
        service.delete(null);

        verify(rolesDao, never()).deleteById(any());
    }

    @Test
    void updateShouldReturnNullWhenRoleMissing() {
        when(rolesDao.findById(55)).thenReturn(Optional.empty());

        RolesDto result = service.update(55, RolesDto.builder().designation("X").build());

        assertNull(result);
    }

    @Test
    void updateShouldSaveWhenRoleFound() {
        Roles role = new Roles();
        role.setIdRoles(12);
        role.setDesignation("OLD");
        when(rolesDao.findById(12)).thenReturn(Optional.of(role));
        when(rolesDao.save(role)).thenReturn(role);

        RolesDto result = service.update(12, RolesDto.builder().designation("NEW").build());

        assertNotNull(result);
        assertEquals("NEW", result.getDesignation());
    }

    @Test
    void findAllShouldReturnMappedList() {
        Roles role = new Roles();
        role.setDesignation("USER");
        when(rolesDao.findAll()).thenReturn(List.of(role));

        List<RolesDto> result = service.findAll();

        assertEquals(1, result.size());
    }

    @Test
    void findByDesignationShouldReturnRoleWhenFound() {
        Roles role = new Roles();
        role.setIdRoles(1);
        role.setDesignation("ADMIN");
        when(rolesDao.findByDesignation("ADMIN")).thenReturn(Optional.of(role));

        RolesDto result = service.findByDesignation("ADMIN");

        assertNotNull(result);
        assertEquals("ADMIN", result.getDesignation());
    }
}
