package com.glop.cibl_orga_sport.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import com.glop.cibl_orga_sport.data.Compte;
import com.glop.cibl_orga_sport.data.Lieu;
import com.glop.cibl_orga_sport.data.Visiteur;
import com.glop.cibl_orga_sport.dto.AccountCreationDTO;
import com.glop.cibl_orga_sport.dto.LieuDTO;
import com.glop.cibl_orga_sport.dto.UtilisateurDTO;
import com.glop.cibl_orga_sport.repository.CompteRepository;
import com.glop.cibl_orga_sport.repository.LieuRepository;
import com.glop.cibl_orga_sport.repository.UtilisateurRepository;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class CompteServiceImplTest {

    @Mock
    private CompteRepository repository;
    @Mock
    private UtilisateurRepository utilisateurRepository;
    @Mock
    private LieuRepository lieuRepository;

    @InjectMocks
    private CompteServiceImpl service;

    @Test
    void createAccountShouldSaveUserAndCompte() {
        AccountCreationDTO dto = new AccountCreationDTO();
        dto.setNom("Nom");
        dto.setPrenom("Prenom");
        dto.setEmail("mail@test.com");
        dto.setUsername("user1");
        dto.setPassword("pwd");

        LieuDTO lieuDTO = new LieuDTO();
        lieuDTO.setIdLieu(5L);
        dto.setLieu(lieuDTO);

        Lieu lieu = new Lieu();
        when(lieuRepository.findById(5L)).thenReturn(Optional.of(lieu));

        Visiteur saved = new Visiteur();
        saved.setIdUtilisateur(12L);
        saved.setNom("Nom");
        saved.setPrenom("Prenom");
        saved.setEmail("mail@test.com");
        when(utilisateurRepository.save(any(Visiteur.class))).thenReturn(saved);

        when(repository.save(any(Compte.class))).thenAnswer(invocation -> invocation.getArgument(0));

        UtilisateurDTO result = service.createAccount(dto);

        assertNotNull(result);
        assertEquals(12L, result.getIdUtilisateur());
        verify(repository).save(any(Compte.class));
    }

    @Test
    void loginShouldReturnUserWhenCredentialsMatch() {
        Compte compte = new Compte();
        compte.setUsername("u1");
        compte.setPassword("secret");
        Visiteur visiteur = new Visiteur();
        visiteur.setIdUtilisateur(2L);
        compte.setUtilisateur(visiteur);

        when(repository.findByUsername("u1")).thenReturn(Optional.of(compte));

        Optional<UtilisateurDTO> result = service.login("u1", "secret");

        assertTrue(result.isPresent());
        assertEquals(2L, result.get().getIdUtilisateur());
    }

    @Test
    void loginShouldReturnEmptyWhenPasswordDoesNotMatch() {
        Compte compte = new Compte();
        compte.setUsername("u1");
        compte.setPassword("secret");
        when(repository.findByUsername("u1")).thenReturn(Optional.of(compte));

        assertTrue(service.login("u1", "wrong").isEmpty());
    }
}
