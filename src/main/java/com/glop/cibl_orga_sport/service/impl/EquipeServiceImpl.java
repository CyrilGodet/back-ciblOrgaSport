package com.glop.cibl_orga_sport.service.impl;

import com.glop.cibl_orga_sport.data.Equipe;
import com.glop.cibl_orga_sport.data.Sportif;
import com.glop.cibl_orga_sport.dto.EquipeDTO;
import com.glop.cibl_orga_sport.repository.EquipeRepository;
import com.glop.cibl_orga_sport.repository.UtilisateurRepository;
import com.glop.cibl_orga_sport.service.EquipeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EquipeServiceImpl implements EquipeService {

    @Autowired
    private EquipeRepository equipeRepository;

    @Autowired
    private UtilisateurRepository utilisateurRepository;

    @Override
    public List<Equipe> getAllEquipes() {
        return equipeRepository.findAll();
    }

    @Override
    public Optional<Equipe> getEquipe(Long id) {
        return equipeRepository.findById(id);
    }

    @Override
    public Equipe createEquipe(String nomEquipe) {
        Equipe equipe = new Equipe(nomEquipe);
        return equipeRepository.save(equipe);
    }

    @Override
    public Equipe createEquipe(EquipeDTO dto) {
        Equipe equipe = new Equipe(dto.getNomEquipe());
        if (dto.getParticipants() != null) {
            List<Sportif> sportifs = dto.getParticipants().stream()
                .map(sDto -> utilisateurRepository.findById(sDto.getIdSportif()))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .filter(u -> u instanceof Sportif)
                .map(u -> (Sportif) u)
                .collect(Collectors.toList());
            equipe.setParticipants(sportifs);
        }
        return equipeRepository.save(equipe);
    }

    @Override
    public List<Equipe> searchEquipes(String query) {
        return equipeRepository.findByNomEquipeContainingIgnoreCase(query);
    }

    @Override
    public Equipe updateEquipe(Long id, String nomEquipe) {
        Optional<Equipe> existing = equipeRepository.findById(id);
        if (existing.isPresent()) {
            Equipe equipe = existing.get();
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
