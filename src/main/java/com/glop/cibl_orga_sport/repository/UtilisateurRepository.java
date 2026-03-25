package com.glop.cibl_orga_sport.repository;

import com.glop.cibl_orga_sport.data.Utilisateur;
import com.glop.cibl_orga_sport.data.Sportif;
import com.glop.cibl_orga_sport.data.Visiteur;
import com.glop.cibl_orga_sport.data.Commissaire;
import com.glop.cibl_orga_sport.dto.UtilisateurDTO;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UtilisateurRepository extends JpaRepository<Utilisateur, Long> {

    @Query("SELECT s FROM Sportif s")
    List<Sportif> findAllSportifs();

    @Query("SELECT v FROM Visiteur v")
    List<Visiteur> findAllVisiteurs();

    @Query("SELECT c FROM Commissaire c")
    List<Commissaire> findAllCommissaires();

    Optional<Utilisateur> findByEmail(String email);

    @Transactional
    @Modifying
    @Query(value = "UPDATE \"user\" set mdp = :password WHERE email = :email", nativeQuery = true)
    void changeUserPassword(@Param("login") String login,@Param("password") String password);

    boolean existsByEmail(String email);

}
