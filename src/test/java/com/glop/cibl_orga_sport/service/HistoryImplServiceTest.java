package com.glop.cibl_orga_sport.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import com.glop.cibl_orga_sport.data.History;
import com.glop.cibl_orga_sport.data.Utilisateur;
import com.glop.cibl_orga_sport.dto.HistoryDto;
import com.glop.cibl_orga_sport.repository.HistoryDao;
import com.glop.cibl_orga_sport.service.Impl.HistoryImplService;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class HistoryImplServiceTest {

    @Mock
    private HistoryDao historyDao;

    @InjectMocks
    private HistoryImplService service;

    @Test
    void getUserConnectedShouldReturnEmptyString() {
        assertEquals("", service.getUserConnected());
    }

    @Test
    void saveHistoryShouldPersistSimpleHistory() {
        service.saveHistory("action", "success");

        verify(historyDao).save(any(History.class));
    }

    @Test
    void saveHistoryWithUserShouldPersistHistoryWithUser() {
        Utilisateur user = new Utilisateur();

        service.saveHistory("action", "success", user);

        verify(historyDao).save(any(History.class));
    }

    @Test
    void saveHistoryLoginShouldPersistHistory() {
        Utilisateur user = new Utilisateur();

        service.saveHistoryLogin("login", "success", user, "user@test.com");

        verify(historyDao).save(any(History.class));
    }

    @Test
    void findAllShouldMapEntitiesToDtos() {
        History history = new History();
        history.setAction("A");
        history.setStatus("OK");
        when(historyDao.findAll()).thenReturn(List.of(history));

        List<HistoryDto> result = service.findAll();

        assertEquals(1, result.size());
        assertEquals("A", result.get(0).getAction());
    }
}
