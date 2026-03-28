package com.glop.cibl_orga_sport.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.glop.cibl_orga_sport.data.Utilisateur;
import com.glop.cibl_orga_sport.data.auth.request.RequestChangePassword;
import com.glop.cibl_orga_sport.dto.UtilisateurDTO;
import com.glop.cibl_orga_sport.repository.UtilisateurRepository;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

@ExtendWith(MockitoExtension.class)
class ChangeMdpImplServiceTest {

    @Mock
    private UtilisateurRepository userDao;
    @Mock
    private PasswordEncoder passwordEncoder;
    @Mock
    private HistoryService historyService;
    @Mock
    private UtilisateurService userService;

    @InjectMocks
    private ChangeMdpImplService service;

    @Test
    void isTruePasswordShouldReturnTrueWhenPasswordMatches() {
        Utilisateur user = new Utilisateur();
        user.setMdp("encoded");
        when(userDao.findByEmail("u@test.com")).thenReturn(Optional.of(user));
        when(passwordEncoder.matches("plain", "encoded")).thenReturn(true);

        assertTrue(service.isTruePassword("u@test.com", "plain"));
    }

    @Test
    void isTruePasswordShouldReturnFalseWhenRepositoryReturnsNullOptional() {
        when(userDao.findByEmail("missing@test.com")).thenReturn(null);

        assertFalse(service.isTruePassword("missing@test.com", "plain"));
    }

    @Test
    void changeUserPasswordShouldReturnZero() {
        UtilisateurDTO dto = new UtilisateurDTO();
        dto.setEmail("u@test.com");

        assertEquals(0, service.changeUserPassword(dto, "newPwd"));
    }

    @Test
    void changeUserPasswordCheckShouldReturnZero() {
        RequestChangePassword request = new RequestChangePassword();

        assertEquals(0, service.changeUserPasswordCheck(request));
    }
}
