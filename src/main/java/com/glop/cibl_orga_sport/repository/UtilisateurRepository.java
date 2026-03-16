package com.glop.cibl_orga_sport.repository;

import com.glop.cibl_orga_sport.data.Utilisateur;
import com.glop.cibl_orga_sport.data.Sportif;
import com.glop.cibl_orga_sport.data.Visiteur;
import com.glop.cibl_orga_sport.data.Commissaire;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface UtilisateurRepository extends JpaRepository<Utilisateur, Long> {
    
    @Query("SELECT s FROM Sportif s")
    List<Sportif> findAllSportifs();

    @Query("SELECT v FROM Visiteur v")
    List<Visiteur> findAllVisiteurs();

    @Query("SELECT c FROM Commissaire c")
    List<Commissaire> findAllCommissaires();
}
