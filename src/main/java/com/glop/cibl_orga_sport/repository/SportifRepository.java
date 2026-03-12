package com.glop.cibl_orga_sport.repository;

import com.glop.cibl_orga_sport.data.Sportif;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SportifRepository extends JpaRepository<Sportif, Long> {
    // List<Sportif> findByEquipe_IdEquipe(Long idEquipe);
}
