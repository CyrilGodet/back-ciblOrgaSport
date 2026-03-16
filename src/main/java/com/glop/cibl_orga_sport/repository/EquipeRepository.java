package com.glop.cibl_orga_sport.repository;

import com.glop.cibl_orga_sport.data.Equipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EquipeRepository extends JpaRepository<Equipe, Long> {
}
