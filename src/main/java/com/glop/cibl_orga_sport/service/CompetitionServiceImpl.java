package com.glop.cibl_orga_sport.service;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.glop.cibl_orga_sport.data.Competition;
import com.glop.cibl_orga_sport.data.enumType.CompetitionStatusEnum;
import com.glop.cibl_orga_sport.data.enumType.CompetitionGenreEnum;
import com.glop.cibl_orga_sport.data.enumType.CompetitionSportEnum;
import com.glop.cibl_orga_sport.repository.CompetitionRepository;

@Service
public class CompetitionServiceImpl implements CompetitionService {

    @Autowired
    private CompetitionRepository repository;

    @Override
    public Competition createCompetition(String name, String description, CompetitionSportEnum sport, Date dateDebut, Date dateFin, 
                                        String pays, boolean estEnFrance, String adresse, String codePostal, String ville, 
                                        CompetitionGenreEnum genre, int ageMin, int ageMax) {
        if (dateDebut != null && dateFin != null && dateDebut.after(dateFin)) {
            throw new IllegalArgumentException("La date de début doit être avant la date de fin");
        }
        Competition c = new Competition(name, dateDebut, dateFin);
        c.setDescription(description);
        c.setSport(sport);
        c.setPays(pays);
        c.setEstEnFrance(estEnFrance);
        c.setAdresse(adresse);
        c.setCodePostal(codePostal);
        c.setVille(ville);
        c.setGenre(genre);
        c.setAgeMin(ageMin);
        c.setAgeMax(ageMax);
        c.setStatut(CompetitionStatusEnum.DRAFT);
        System.out.println("Création compétition : " + name);
        return repository.save(c);
    }

    @Override
    public Competition updateCompetition(Long id, String name, String description, CompetitionSportEnum sport, Date dateDebut, Date dateFin, 
                                        String pays, boolean estEnFrance, String adresse, String codePostal, String ville, 
                                        CompetitionGenreEnum genre, int ageMin, int ageMax, CompetitionStatusEnum statut) {
        if (dateDebut != null && dateFin != null && dateDebut.after(dateFin)) {
            throw new IllegalArgumentException("La date de début doit être avant la date de fin");
        }
        Optional<Competition> existingCompetition = repository.findById(id);
        if (existingCompetition.isPresent()) {
            Competition c = existingCompetition.get();
            c.setNameCompetition(name);
            c.setDescription(description);
            c.setSport(sport);
            c.setDateDebut(dateDebut);
            c.setDateFin(dateFin);
            c.setPays(pays);
            c.setEstEnFrance(estEnFrance);
            c.setAdresse(adresse);
            c.setCodePostal(codePostal);
            c.setVille(ville);
            c.setGenre(genre);
            c.setAgeMin(ageMin);
            c.setAgeMax(ageMax);
            c.setStatut(statut);
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
            Competition competition = c.get();
            if (competition.getEpreuves() != null && !competition.getEpreuves().isEmpty()) {
                throw new IllegalStateException("Impossible de supprimer cette compétition car elle est liée à " + 
                    competition.getEpreuves().size() + " épreuve(s) existante(s).");
            }
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

