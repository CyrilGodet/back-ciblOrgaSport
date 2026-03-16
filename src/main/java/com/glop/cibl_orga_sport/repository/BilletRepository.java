package com.glop.cibl_orga_sport.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.glop.cibl_orga_sport.data.Billet;

public interface BilletRepository extends JpaRepository<Billet, Long> {
    public List<Billet> findBySpectateurIdUtilisateur(Long spectateurId);

    public boolean existsByNumeroBillet(String numeroBillet);
}
