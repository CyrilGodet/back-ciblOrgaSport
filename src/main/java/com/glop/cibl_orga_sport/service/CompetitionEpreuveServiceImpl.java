package com.glop.cibl_orga_sport.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.glop.cibl_orga_sport.data.Competition;
import com.glop.cibl_orga_sport.data.Epreuve;
import com.glop.cibl_orga_sport.repository.CompetitionRepository;
import com.glop.cibl_orga_sport.repository.EpreuveRepository;

@Service
public class CompetitionEpreuveServiceImpl implements CompetitionEpreuveService {

    @Autowired
    private CompetitionRepository competitionRepository;
    
    @Autowired
    private EpreuveRepository epreuveRepository;

    @Override
    public boolean linkEpreuveToCompetition(Long competitionId, Long epreuveId) {
        Optional<Competition> competitionOpt = competitionRepository.findById(competitionId);
        Optional<Epreuve> epreuveOpt = epreuveRepository.findById(epreuveId);
        
        if (competitionOpt.isPresent() && epreuveOpt.isPresent()) {
            Competition competition = competitionOpt.get();
            Epreuve epreuve = epreuveOpt.get();
            
            competition.addEpreuve(epreuve);
            competitionRepository.save(competition);
            
            System.out.println("Épreuve " + epreuve.getNomEpreuve() + " liée à la compétition " + competition.getNameCompetition());
            return true;
        }
        
        System.out.println("Impossible de lier : compétition ou épreuve non trouvée");
        return false;
    }

    @Override
    public boolean unlinkEpreuveFromCompetition(Long competitionId, Long epreuveId) throws IllegalAccessException {
        Optional<Competition> competitionOpt = competitionRepository.findById(competitionId);
        Optional<Epreuve> epreuveOpt = epreuveRepository.findById(epreuveId);
        
        if (competitionOpt.isPresent() && epreuveOpt.isPresent()) {
            Competition competition = competitionOpt.get();
            Epreuve epreuve = epreuveOpt.get();
            
            competition.removeEpreuve(epreuve);
            competitionRepository.save(competition);
            
            System.out.println("Épreuve " + epreuve.getNomEpreuve() + " déliée de la compétition " + competition.getNameCompetition());
            return true;
        }
        
        System.out.println("Impossible de délier : compétition ou épreuve non trouvée");
        return false;
    }
}
