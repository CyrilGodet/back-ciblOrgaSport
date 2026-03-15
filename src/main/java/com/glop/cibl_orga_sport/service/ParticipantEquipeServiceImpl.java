package com.glop.cibl_orga_sport.service;

import com.glop.cibl_orga_sport.data.ParticipantEquipe;
import com.glop.cibl_orga_sport.data.Sportif;
import com.glop.cibl_orga_sport.dto.ParticipantEquipeDTO;
import com.glop.cibl_orga_sport.repository.ParticipantEquipeRepository;
import com.glop.cibl_orga_sport.repository.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ParticipantEquipeServiceImpl implements ParticipantEquipeService {

    @Autowired
    private ParticipantEquipeRepository equipeRepository;

    @Autowired
    private UtilisateurRepository utilisateurRepository;

    @Override
    public List<ParticipantEquipe> getAllEquipes() {
        return equipeRepository.findAll();
    }

    @Override
    public Optional<ParticipantEquipe> getEquipe(Long id) {
        return equipeRepository.findById(id);
    }

    @Override
    public ParticipantEquipe createEquipe(String nomEquipe) {
        ParticipantEquipe equipe = new ParticipantEquipe(nomEquipe);
        return equipeRepository.save(equipe);
    }

    @Override
    public ParticipantEquipe createEquipe(ParticipantEquipeDTO dto) {
        ParticipantEquipe equipe = new ParticipantEquipe(dto.getNomEquipe());
        if (dto.getSportifs() != null) {
            List<Sportif> sportifs = dto.getSportifs().stream()
                    .map(sDto -> utilisateurRepository.findById(sDto.getIdUtilisateur())) // Need to correctly fetch
                                                                                          // Sportif
                    .filter(Optional::isPresent)
                    .map(Optional::get)
                    .filter(u -> u instanceof Sportif)
                    .map(u -> (Sportif) u)
                    .collect(Collectors.toList());
            equipe.setSportifs(sportifs);
        }
        return equipeRepository.save(equipe);
    }

    @Override
    public List<ParticipantEquipe> searchEquipes(String query) {
        System.out
                .println("Recherche d'équipes : " + equipeRepository.findByNomEquipeContainingIgnoreCase(query).size());
        return equipeRepository.findByNomEquipeContainingIgnoreCase(query);
    }

    @Override
    public ParticipantEquipe updateEquipe(Long id, String nomEquipe) {
        Optional<ParticipantEquipe> existing = equipeRepository.findById(id);
        if (existing.isPresent()) {
            ParticipantEquipe equipe = existing.get();
            equipe.setNomEquipe(nomEquipe);
            return equipeRepository.save(equipe);
        }
        return null;
    }

    @Override
    public boolean deleteEquipe(Long id) {
        if (equipeRepository.existsById(id)) {
            equipeRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
