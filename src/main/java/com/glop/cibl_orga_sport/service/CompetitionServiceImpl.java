package com.glop.cibl_orga_sport.service;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.glop.cibl_orga_sport.data.Competition;
import com.glop.cibl_orga_sport.repository.CompetitionRepository;

@Service
public class CompetitionServiceImpl implements CompetitionService {

    @Autowired
    private CompetitionRepository repository;

    @Override
    public Competition createCompetition(String name, Date dateDebut, Date dateFin) {
        Competition c = new Competition(name, dateDebut, dateFin);
        System.out.println("Création compétition : " + name);
        return repository.save(c);
    }

    @Override
    public Competition updateCompetition(Long id, String name, Date dateDebut, Date dateFin) {
        Optional<Competition> existingCompetition = repository.findById(id);
        if (existingCompetition.isPresent()) {
            Competition c = existingCompetition.get();
            c.setNameCompetition(name);
            c.setDateDebut(dateDebut);
            c.setDateFin(dateFin);
            System.out.println("Modification compétition : " + id);
            return repository.save(c);
        }
        System.out.println("Compétition non trouvée : " + id);
        return null;
    }

    @Override
    public boolean deleteCompetition(Long id) {
        Optional<Competition> c = repository.findById(id);
        if (c.isPresent()) {
            repository.deleteById(id);
            System.out.println("Compétition supprimée : " + id);
            return true;
        }
        System.out.println("Compétition non trouvée : " + id);
        return false;
    }

    @Override
    public List<Competition> getAllCompetitions() {
        return repository.findAll();
    }

    @Override
    public Optional<Competition> getCompetition(Long id) {
        return repository.findById(id);
    }
}

