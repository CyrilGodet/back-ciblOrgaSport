package com.glop.cibl_orga_sport.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import com.glop.cibl_orga_sport.data.Permission;
import com.glop.cibl_orga_sport.data.Roles;
import com.glop.cibl_orga_sport.dto.PermissionDto;
import com.glop.cibl_orga_sport.dto.RolesDto;
import com.glop.cibl_orga_sport.exception.EntityNotFoundException;
import com.glop.cibl_orga_sport.exception.InvalidEntityException;
import com.glop.cibl_orga_sport.repository.PermissionDao;
import com.glop.cibl_orga_sport.repository.RolesDao;
import com.glop.cibl_orga_sport.service.Impl.PermissionImplService;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class PermissionImplServiceTest {

    @Mock
    private PermissionDao permissionDao;
    @Mock
    private RolesDao rolesDao;

    @InjectMocks
    private PermissionImplService service;

    @Test
    void saveShouldhrowWhenInvalid() {
        PermissionDto dto = PermissionDto.builder().caption("").credential(0).build();

        assertThrows(InvalidEntityException.class, () -> service.save(dto));
    }

    @Test
    void saveShouldersistWhenValid() {
        Roles role = new Roles();
        role.setIdRoles(2);

        PermissionDto dto = PermissionDto.builder()
                .caption("READ")
                .credential(1)
                .roles(RolesDto.builder().id(2).designation("USER").build())
                .build();

        Permission saved = new Permission();
        saved.setIdPermission(10L);
        saved.setCaption("READ");
        saved.setCredential(1);
        saved.setRoles(role);

        when(rolesDao.findById(2)).thenReturn(Optional.of(role));
        when(permissionDao.save(any(Permission.class))).thenReturn(saved);

        PermissionDto result = service.save(dto);

        assertNotNull(result);
        assertEquals(10L, result.getId());
    }

    @Test
    void findByIdShouldhrowWhenMissing() {
        when(permissionDao.findById(99)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> service.findById(99));
    }

    @Test
    void deleteShouldgnoreNullId() {
        service.delete(null);

        verify(permissionDao, never()).deleteById(any());
    }

    @Test
    void getPermissionGroupedByRolesShouldeturnMappedList() {
        Permission permission = new Permission();
        permission.setCaption("READ");
        permission.setCredential(1);

        when(permissionDao.findPermissionGroupedByRoles("ADMIN")).thenReturn(List.of(permission));

        List<PermissionDto> result = service.getPermissionGroupedByRoles("ADMIN");

        assertEquals(1, result.size());
        assertEquals("READ", result.get(0).getCaption());
    }

    @Test
    void updateShouldeturnNullWhenMissing() {
        when(permissionDao.findById(123)).thenReturn(Optional.empty());

        PermissionDto result = service.update(123, PermissionDto.builder().build());

        assertNull(result);
    }

    @Test
    void updateShouldersistWhenFound() {
        Permission permission = new Permission();
        permission.setIdPermission(11L);
        when(permissionDao.findById(11)).thenReturn(Optional.of(permission));
        when(permissionDao.save(permission)).thenReturn(permission);

        PermissionDto dto = PermissionDto.builder()
                .caption("WRITE")
                .credential(2)
                .status(1)
                .roles(RolesDto.builder().id(3).designation("ADMIN").build())
                .build();

        PermissionDto result = service.update(11, dto);

        assertNotNull(result);
    }

    @Test
    void findAllShouldeturnMappedList() {
        Permission permission = new Permission();
        permission.setCaption("READ");
        permission.setCredential(1);
        when(permissionDao.findAll()).thenReturn(List.of(permission));

        List<PermissionDto> result = service.findAll();

        assertEquals(1, result.size());
    }

    @Test
    void deleteShouldeleteWhenIdProvided() {
        service.delete(5);

        verify(permissionDao).deleteById(5);
    }
}
