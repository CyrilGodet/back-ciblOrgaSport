package com.glop.cibl_orga_sport.service.impl;

import com.glop.cibl_orga_sport.data.Equipe;
import com.glop.cibl_orga_sport.data.Competition;
import com.glop.cibl_orga_sport.repository.EquipeRepository;
import com.glop.cibl_orga_sport.service.EquipeService;
import com.glop.cibl_orga_sport.repository.CompetitionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EquipeServiceImpl implements EquipeService {

    @Autowired
    private EquipeRepository equipeRepository;

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
