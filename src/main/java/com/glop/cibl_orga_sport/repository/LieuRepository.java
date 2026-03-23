package com.glop.cibl_orga_sport.repository;

import com.glop.cibl_orga_sport.data.Lieu;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import java.util.List;

public interface LieuRepository extends JpaRepository<Lieu, Long> {
    Optional<Lieu> findByNomLieuAndVille(String nomLieu, String ville);
    List<com.glop.cibl_orga_sport.data.Lieu> findByNomLieuContainingIgnoreCase(String query);
}
