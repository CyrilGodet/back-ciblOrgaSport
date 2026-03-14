package com.glop.cibl_orga_sport.repository;

import com.glop.cibl_orga_sport.data.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface PermissionDao extends JpaRepository<Permission, Integer> {
    @Query(name = "find_permission", nativeQuery = true)
    List<Permission> findPermissionGroupedByRoles(@Param("designation") String designation);

}
