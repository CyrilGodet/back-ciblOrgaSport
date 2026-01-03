package com.glop.cibl_orga_sport.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.glop.cibl_orga_sport.data.Competition;
import com.glop.cibl_orga_sport.data.Epreuve;
import com.glop.cibl_orga_sport.repository.EpreuveRepository;

@Service
public class EpreuveServiceImpl implements EpreuveService {

    @Autowired
    private EpreuveRepository repository;

    @Override
    public Epreuve createEpreuve(String nomEpreuve, Competition competition) {
        Epreuve e = new Epreuve(nomEpreuve);
        e.setCompetition(competition);
        if (competition != null && !competition.getEpreuves().contains(e)) {
            competition.getEpreuves().add(e);
        }
        System.out.println("Création épreuve : " + nomEpreuve);
        return repository.save(e);
    }

    @Override
    public Epreuve updateEpreuve(Long id, String nomEpreuve, Competition competition) {
        Optional<Epreuve> existingEpreuve = repository.findById(id);
        if (existingEpreuve.isPresent()) {
            Epreuve e = existingEpreuve.get();
            e.setNomEpreuve(nomEpreuve);
            
            Competition oldCompetition = e.getCompetition();
            if (oldCompetition != null && !oldCompetition.equals(competition)) {
                oldCompetition.getEpreuves().remove(e);
            }
            e.setCompetition(competition);
            if (competition != null && !competition.getEpreuves().contains(e)) {
                competition.getEpreuves().add(e);
            }
            
            System.out.println("Modification épreuve : " + id);
            return repository.save(e);
        }
        System.out.println("Épreuve non trouvée : " + id);
        return null;
    }

    @Override
    public boolean deleteEpreuve(Long id) {
        Optional<Epreuve> e = repository.findById(id);
        if (e.isPresent()) {
            Epreuve epreuve = e.get();
            boolean hasPhases = epreuve.getPhases() != null && !epreuve.getPhases().isEmpty();
            boolean hasCategories = epreuve.getCategories() != null && !epreuve.getCategories().isEmpty();
            
            if (hasPhases || hasCategories) {
                throw new IllegalStateException("Impossible de supprimer cette épreuve car elle contient des phases ou des catégories.");
            }
            
            repository.deleteById(id);
            System.out.println("Épreuve supprimée : " + id);
            return true;
        }
        System.out.println("Épreuve non trouvée : " + id);
        return false;
    }

    @Override
    public List<Epreuve> getAllEpreuves() {
        return repository.findAll();
    }

    @Override
    public Optional<Epreuve> getEpreuve(Long id) {
        return repository.findById(id);
    }
}
