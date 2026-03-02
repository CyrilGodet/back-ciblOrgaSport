package com.glop.cibl_orga_sport.repository;

import com.glop.cibl_orga_sport.data.Volontaire;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VolontaireRepository extends JpaRepository<Volontaire, Long> {
    Optional<Volontaire> findByEmail(String email);
}
