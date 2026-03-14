package com.glop.cibl_orga_sport.repository;

import com.glop.cibl_orga_sport.data.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface PermissionDao extends JpaRepository<Permission, Integer> {
    @Query(value = "SELECT DISTINCT p.* FROM permission p " +
            "JOIN role_permission rp ON p.id = rp.permission_id " +
            "JOIN roles r ON rp.role_id = r.id " +
            "WHERE r.designation = :designation",
            nativeQuery = true)
    List<Permission> findPermissionGroupedByRoles(@Param("designation") String designation);

}
