package com.glop.cibl_orga_sport.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import com.glop.cibl_orga_sport.data.Lieu;
import com.glop.cibl_orga_sport.data.Commissaire;
import com.glop.cibl_orga_sport.data.ParticipantSportif;
import com.glop.cibl_orga_sport.data.Sportif;
import com.glop.cibl_orga_sport.data.UserDtoJson;
import com.glop.cibl_orga_sport.data.Utilisateur;
import com.glop.cibl_orga_sport.data.Visiteur;
import com.glop.cibl_orga_sport.dto.LieuDTO;
import com.glop.cibl_orga_sport.dto.SportifDTO;
import com.glop.cibl_orga_sport.dto.VisiteurDTO;
import com.glop.cibl_orga_sport.exception.EntityNotFoundException;
import com.glop.cibl_orga_sport.repository.LieuRepository;
import com.glop.cibl_orga_sport.repository.ParticipantSportifRepository;
import com.glop.cibl_orga_sport.repository.SportifRepository;
import com.glop.cibl_orga_sport.repository.UtilisateurRepository;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@ExtendWith(MockitoExtension.class)
class UtilisateurServiceImplTest {

    @Mock
    private UtilisateurRepository repository;
    @Mock
    private SportifRepository sportifRepository;
    @Mock
    private ParticipantSportifRepository participantSportifRepository;
    @Mock
    private LieuRepository lieuRepository;
    @Mock
    private HistoryService historyService;

    @InjectMocks
    private UtilisateurServiceImpl service;

    @Test
    void createSportifShouldSaveSportifAndParticipant() {
        SportifDTO dto = new SportifDTO();
        dto.setNom("Nom");

        LieuDTO lieuDTO = new LieuDTO();
        lieuDTO.setIdLieu(2L);
        dto.setLieu(lieuDTO);

        when(lieuRepository.findById(2L)).thenReturn(Optional.of(new Lieu()));

        Sportif savedSportif = new Sportif();
        savedSportif.setIdUtilisateur(11L);
        when(repository.save(any(Sportif.class))).thenReturn(savedSportif);
        when(participantSportifRepository.save(any(ParticipantSportif.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Sportif result = service.createSportif(dto);

        assertNotNull(result);
        assertEquals(11L, result.getIdUtilisateur());
        verify(participantSportifRepository).save(any(ParticipantSportif.class));
    }

    @Test
    void createVisiteurShouldSave() {
        VisiteurDTO dto = new VisiteurDTO();
        dto.setNom("Nom");

        Visiteur saved = new Visiteur();
        saved.setNom("Nom");
        when(repository.save(any(Visiteur.class))).thenReturn(saved);

        Visiteur result = service.createVisiteur(dto);

        assertNotNull(result);
        assertEquals("Nom", result.getNom());
    }

    @Test
    void findByEmailShouldReturnNullWhenEmailNull() {
        assertNull(service.findByEmail(null));
    }

    @Test
    void findByEmailShouldThrowWhenNotFound() {
        when(repository.findByEmail("a@test.com")).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> service.findByEmail("a@test.com"));
    }

    @Test
    void userDetailsServiceShouldThrowWhenUserMissing() {
        UserDetailsService uds = service.userDetailsService();
        when(repository.findByEmail("a@test.com")).thenReturn(Optional.empty());

        assertThrows(UsernameNotFoundException.class, () -> uds.loadUserByUsername("a@test.com"));
    }

    @Test
    void updateNoMdpShouldReturnNullWhenUserMissing() {
        when(repository.findById(3L)).thenReturn(Optional.empty());

        UserDtoJson request = new UserDtoJson();
        UserDtoJson result = service.updateNoMdp(3L, request);

        assertNull(result);
    }

    @Test
    void approvalShouldSetStateTo10WhenUserFound() {
        Utilisateur user = new Utilisateur();
        user.setEmail("u@test.com");
        when(repository.findById(4L)).thenReturn(Optional.of(user));
        when(repository.save(any(Utilisateur.class))).thenAnswer(invocation -> invocation.getArgument(0));

        UserDtoJson result = service.approval(4L);

        assertNotNull(result);
        assertEquals(10, user.getState());
    }

    @Test
    void findAllShouldMapUsers() {
        Utilisateur u1 = new Utilisateur();
        u1.setEmail("a@test.com");
        Utilisateur u2 = new Utilisateur();
        u2.setEmail("b@test.com");

        when(repository.findAll()).thenReturn(List.of(u1, u2));

        List<UserDtoJson> result = service.findAll();

        assertEquals(2, result.size());
    }

    @Test
    void getAllSportifsShouldDelegateToRepository() {
        when(repository.findAllSportifs()).thenReturn(List.of(new Sportif()));

        List<Sportif> result = service.getAllSportifs();

        assertEquals(1, result.size());
    }

    @Test
    void getAllVisiteursShouldDelegateToRepository() {
        when(repository.findAllVisiteurs()).thenReturn(List.of(new Visiteur()));

        List<Visiteur> result = service.getAllVisiteurs();

        assertEquals(1, result.size());
    }

    @Test
    void getAllCommissairesShouldDelegateToRepository() {
        when(repository.findAllCommissaires()).thenReturn(List.of(new Commissaire()));

        List<Commissaire> result = service.getAllCommissaires();

        assertEquals(1, result.size());
    }

    @Test
    void searchSportifsShouldDelegateToSportifRepository() {
        when(sportifRepository.findByNomContainingIgnoreCaseOrPrenomContainingIgnoreCaseOrEmailContainingIgnoreCase(
                "abc", "abc", "abc")).thenReturn(List.of(new Sportif()));

        List<Sportif> result = service.searchSportifs("abc");

        assertEquals(1, result.size());
    }

    @Test
    void searchParticipantSportifsShouldDelegateToRepository() {
        when(participantSportifRepository.searchParticipantSportifs("q")).thenReturn(List.of(new ParticipantSportif()));

        List<ParticipantSportif> result = service.searchParticipantSportifs("q");

        assertEquals(1, result.size());
    }

    @Test
    void findByIdShouldReturnNullByCurrentImplementation() {
        assertNull(service.findById(1));
    }
}
