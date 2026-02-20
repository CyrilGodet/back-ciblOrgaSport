package com.glop.cibl_orga_sport.service;

import com.glop.cibl_orga_sport.data.Equipe;
import com.glop.cibl_orga_sport.data.Competition;
import com.glop.cibl_orga_sport.repository.EquipeRepository;
import com.glop.cibl_orga_sport.repository.CompetitionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EquipeServiceImpl implements EquipeService {
    
    @Autowired
    private EquipeRepository equipeRepository;
    
    @Autowired
    private CompetitionRepository competitionRepository;

    @Override
    public List<Equipe> getAllEquipes() {
        return equipeRepository.findAll();
    }

    @Override
    public Optional<Equipe> getEquipe(Long id) {
        return equipeRepository.findById(id);
    }

    @Override
    public List<Equipe> getEquipesByCompetitionId(Long competitionId) {
        return equipeRepository.findByCompetition_IdCompetition(competitionId);
    }

    @Override
    public Equipe createEquipe(String nomEquipe, Long competitionId) {
        Equipe equipe = new Equipe(nomEquipe);
        if (competitionId != null) {
            Optional<Competition> competition = competitionRepository.findById(competitionId);
            competition.ifPresent(equipe::setCompetition);
        }
        return equipeRepository.save(equipe);
    }

    @Override
    public Equipe updateEquipe(Long id, String nomEquipe, Long competitionId) {
        Optional<Equipe> existing = equipeRepository.findById(id);
        if (existing.isPresent()) {
            Equipe equipe = existing.get();
            equipe.setNomEquipe(nomEquipe);
            if (competitionId != null) {
                Optional<Competition> competition = competitionRepository.findById(competitionId);
                competition.ifPresent(equipe::setCompetition);
            }
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
