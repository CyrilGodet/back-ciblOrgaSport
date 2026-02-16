package com.glop.cibl_orga_sport.service;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.glop.cibl_orga_sport.data.Competition;
import com.glop.cibl_orga_sport.data.Periode;
import com.glop.cibl_orga_sport.data.Lieu;
import com.glop.cibl_orga_sport.data.ConditionAge;
import com.glop.cibl_orga_sport.data.enumType.CompetitionStatusEnum;
import com.glop.cibl_orga_sport.data.enumType.CompetitionGenreEnum;
import com.glop.cibl_orga_sport.data.enumType.CompetitionSportEnum;
import com.glop.cibl_orga_sport.repository.CompetitionRepository;
import com.glop.cibl_orga_sport.repository.LieuRepository;

@Service
public class CompetitionServiceImpl implements CompetitionService {

    @Autowired
    private CompetitionRepository repository;

    @Autowired
    private LieuRepository lieuRepository;

    @Override
    public Competition createCompetition(String name, String description, CompetitionSportEnum sport, Date dateDebut, Date dateFin, 
                                        String pays, boolean estEnFrance, String adresse, String codePostal, String ville, 
                                        CompetitionGenreEnum genre, int ageMin, int ageMax, Long lieuId) {
        if (dateDebut != null && dateFin != null && dateDebut.after(dateFin)) {
            throw new IllegalArgumentException("La date de début doit être avant la date de fin");
        }
        
        Periode periode = new Periode(dateDebut, dateFin);
        Lieu lieu = null;
        if (lieuId != null) {
            lieu = lieuRepository.findById(lieuId).orElse(null);
        }
        ConditionAge conditionAge = new ConditionAge(ageMin, ageMax);
        
        Competition c = new Competition(name, description, periode, lieu, conditionAge, genre, CompetitionStatusEnum.DRAFT, sport);
        
        System.out.println("Création compétition : " + name);
        return repository.save(c);
    }

    @Override
    public Competition updateCompetition(Long id, String name, String description, CompetitionSportEnum sport, Date dateDebut, Date dateFin, 
                                        String pays, boolean estEnFrance, String adresse, String codePostal, String ville, 
                                        CompetitionGenreEnum genre, int ageMin, int ageMax, CompetitionStatusEnum statut, Long lieuId) {
        if (dateDebut != null && dateFin != null && dateDebut.after(dateFin)) {
            throw new IllegalArgumentException("La date de début doit être avant la date de fin");
        }
        Optional<Competition> existingCompetition = repository.findById(id);
        if (existingCompetition.isPresent()) {
            Competition c = existingCompetition.get();

            if(c.getStatut() != CompetitionStatusEnum.DRAFT) {
                System.out.println("Impossible de modifier une compétition publiée : " + id);
                return c;
            }

            c.setNameCompetition(name);
            c.setDescription(description);
            c.setSport(sport);
            
            if (dateDebut != null && dateFin != null) {
                if (c.getPeriode() == null) {
                    c.setPeriode(new Periode(dateDebut, dateFin));
                } else {
                    c.getPeriode().setDateDebut(dateDebut);
                    c.getPeriode().setDateFin(dateFin);
                }
            }
            
            if (lieuId != null) {
                Lieu lieu = lieuRepository.findById(lieuId).orElse(null);
                c.setLieu(lieu);
            }
            
            if (c.getConditionAge() == null) {
                c.setConditionAge(new ConditionAge(ageMin, ageMax));
            } else {
                c.getConditionAge().setAgeMin(ageMin);
                c.getConditionAge().setAgeMax(ageMax);
            }
            
            c.setGenre(genre);
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

            if(competition.getStatut() != CompetitionStatusEnum.DRAFT) {
                System.out.println("Impossible de supprimer une compétition publiée : " + id);
                competition.setStatut(CompetitionStatusEnum.CANCELLED);
                repository.save(competition);
                return false;
            }

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

    @Override
    public Competition publishCompetition(Long id) {
        Optional<Competition> competitionOpt = repository.findById(id);
        if (competitionOpt.isPresent()) {
            Competition competition = competitionOpt.get();
            if (competition.getStatut() != CompetitionStatusEnum.DRAFT) {
                System.out.println("Impossible de publier : la compétition n'est pas en mode DRAFT - ID: " + id);
                return null;
            }
            competition.setStatut(CompetitionStatusEnum.PUBLISH);
            System.out.println("Compétition publiée : " + id);
            return repository.save(competition);
        }
        System.out.println("Compétition non trouvée : " + id);
        return null;
    }

    @Override
    public Competition startCompetition(Long id) {
        Optional<Competition> competitionOpt = repository.findById(id);
        if (competitionOpt.isPresent()) {
            Competition competition = competitionOpt.get();
            if (competition.getStatut() != CompetitionStatusEnum.PUBLISH) {
                System.out.println("Impossible de démarrer : la compétition n'est pas publiée - ID: " + id);
                return null;
            }
            competition.setStatut(CompetitionStatusEnum.IN_PROGRESS);
            System.out.println("Compétition démarrée : " + id);
            return repository.save(competition);
        }
        System.out.println("Compétition non trouvée : " + id);
        return null;
    }

    @Override
    public Competition finishCompetition(Long id) {
        Optional<Competition> competitionOpt = repository.findById(id);
        if (competitionOpt.isPresent()) {
            Competition competition = competitionOpt.get();
            if (competition.getStatut() != CompetitionStatusEnum.IN_PROGRESS) {
                System.out.println("Impossible de terminer : la compétition n'est pas en cours - ID: " + id);
                return null;
            }
            competition.setStatut(CompetitionStatusEnum.FINISHED);
            System.out.println("Compétition terminée : " + id);
            return repository.save(competition);
        }
        System.out.println("Compétition non trouvée : " + id);
        return null;
    }
}

