package com.glop.cibl_orga_sport.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import com.glop.cibl_orga_sport.data.ParticipantEquipe;
import com.glop.cibl_orga_sport.data.Sportif;
import com.glop.cibl_orga_sport.dto.ParticipantEquipeDTO;
import com.glop.cibl_orga_sport.dto.SportifDTO;
import com.glop.cibl_orga_sport.repository.ParticipantEquipeRepository;
import com.glop.cibl_orga_sport.repository.UtilisateurRepository;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ParticipantEquipeServiceImplTest {

    @Mock
    private ParticipantEquipeRepository equipeRepository;
    @Mock
    private UtilisateurRepository utilisateurRepository;

    @InjectMocks
    private ParticipantEquipeServiceImpl service;

    @Test
    void createEquipeByNameShouldSave() {
        when(equipeRepository.save(any(ParticipantEquipe.class))).thenAnswer(invocation -> invocation.getArgument(0));

        ParticipantEquipe result = service.createEquipe("Equipe A");

        assertEquals("Equipe A", result.getNomEquipe());
    }

    @Test
    void getAllEquipesShouldReturnRepositoryValues() {
        when(equipeRepository.findAll()).thenReturn(List.of(new ParticipantEquipe("A"), new ParticipantEquipe("B")));

        List<ParticipantEquipe> result = service.getAllEquipes();

        assertEquals(2, result.size());
    }

    @Test
    void getEquipeShouldReturnOptionalWhenFound() {
        when(equipeRepository.findById(3L)).thenReturn(Optional.of(new ParticipantEquipe("C")));

        Optional<ParticipantEquipe> result = service.getEquipe(3L);

        assertTrue(result.isPresent());
    }

    @Test
    void createEquipeByDtoShouldMapSportifs() {
        ParticipantEquipeDTO dto = new ParticipantEquipeDTO();
        dto.setNomEquipe("Team France");

        SportifDTO sportifDTO = new SportifDTO();
        sportifDTO.setIdUtilisateur(7L);
        dto.setSportifs(List.of(sportifDTO));

        Sportif sportif = new Sportif();
        sportif.setIdUtilisateur(7L);

        when(utilisateurRepository.findById(7L)).thenReturn(Optional.of(sportif));
        when(equipeRepository.save(any(ParticipantEquipe.class))).thenAnswer(invocation -> invocation.getArgument(0));

        ParticipantEquipe result = service.createEquipe(dto);

        assertNotNull(result);
        assertEquals(1, result.getSportifs().size());
    }

    @Test
    void searchEquipesShouldUseTailleWhenProvided() {
        when(equipeRepository.findByNomEquipeContainingIgnoreCaseAndTaille("abc", 2))
                .thenReturn(List.of(new ParticipantEquipe("A")));

        List<ParticipantEquipe> result = service.searchEquipes("abc", 2);

        assertEquals(1, result.size());
    }

    @Test
    void updateEquipeShouldReturnNullWhenNotFound() {
        when(equipeRepository.findById(99L)).thenReturn(Optional.empty());

        assertNull(service.updateEquipe(99L, "Nouveau"));
    }

    @Test
    void deleteEquipeShouldDeleteWhenExists() {
        when(equipeRepository.existsById(10L)).thenReturn(true);

        assertTrue(service.deleteEquipe(10L));
        verify(equipeRepository).deleteById(10L);
    }
}
