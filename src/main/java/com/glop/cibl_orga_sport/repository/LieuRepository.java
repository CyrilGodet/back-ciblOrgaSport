package com.glop.cibl_orga_sport.repository;

import com.glop.cibl_orga_sport.data.Lieu;
import com.glop.cibl_orga_sport.data.enumType.LieuCategorieEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import java.util.List;

public interface LieuRepository extends JpaRepository<Lieu, Long> {
    Optional<Lieu> findByNomLieuAndVille(String nomLieu, String ville);
    List<Lieu> findByNomLieuContainingIgnoreCase(String query);
    List<Lieu> findByCategorieOrderByNomLieuAsc(LieuCategorieEnum categorie);

}
