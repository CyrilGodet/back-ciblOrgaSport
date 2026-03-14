package com.glop.cibl_orga_sport.repository;

import com.glop.cibl_orga_sport.data.Roles;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RolesDao extends JpaRepository<Roles, Long> {
    boolean existsByDesignation(String designation);

    Optional<Roles> findByDesignation(String designation);

}