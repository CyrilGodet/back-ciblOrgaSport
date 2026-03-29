package com.glop.cibl_orga_sport.service.impl;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.glop.cibl_orga_sport.data.Competition;
import com.glop.cibl_orga_sport.data.ConditionAge;
import com.glop.cibl_orga_sport.data.Epreuve;
import com.glop.cibl_orga_sport.data.Periode;
import com.glop.cibl_orga_sport.data.enumType.CompetitionStatusEnum;
import com.glop.cibl_orga_sport.data.enumType.DisciplineEnum;
import com.glop.cibl_orga_sport.data.enumType.CompetitionGenreEnum;
import com.glop.cibl_orga_sport.repository.EpreuveRepository;
import com.glop.cibl_orga_sport.service.EpreuveService;

@Service
public class EpreuveServiceImpl implements EpreuveService {

    @Autowired
    private EpreuveRepository repository;

    @Override
    public Epreuve createEpreuve(String nomEpreuve, String description, DisciplineEnum discipline,
            CompetitionGenreEnum genre,
            Date dateDebut, Date dateFin, int ageMin, int ageMax, Competition competition) {
        Epreuve e = new Epreuve(nomEpreuve);
        e.setDescription(description);
        e.setDiscipline(discipline);
        e.setGenre(genre);

        if (dateDebut != null && dateFin != null) {
            Periode periode = new Periode(new Date(dateDebut.getTime()), new Date(dateFin.getTime()));
            e.setPeriode(periode);
        }

        ConditionAge conditionAge = new ConditionAge(ageMin, ageMax);
        e.setConditionAge(conditionAge);

        e.setStatut(CompetitionStatusEnum.DRAFT);
        e.setCompetition(competition);
        if (competition != null && !competition.getEpreuves().contains(e)) {
            competition.getEpreuves().add(e);
        }
        System.out.println("Épreuve créée : " + nomEpreuve);
        return repository.save(e);
    }

    @Override
    public Epreuve updateEpreuve(Long id, String nomEpreuve, String description, DisciplineEnum discipline,
            CompetitionGenreEnum genre,
            Date dateDebut, Date dateFin, int ageMin, int ageMax, CompetitionStatusEnum statut,
            Competition competition) {
        Optional<Epreuve> existingEpreuve = repository.findById(id);
        if (existingEpreuve.isPresent()) {
            Epreuve e = existingEpreuve.get();

            if (e.getStatut() != CompetitionStatusEnum.DRAFT) {
                System.out.println("Impossible de modifier une épreuve publiée : " + id);
                return e;
            }

            e.setNomEpreuve(nomEpreuve);
            e.setDescription(description);
            e.setDiscipline(discipline);
            e.setGenre(genre);

            if (dateDebut != null && dateFin != null) {
                if (e.getPeriode() == null) {
                    e.setPeriode(
                            new Periode(new Date(dateDebut.getTime()), new Date(dateFin.getTime())));
                } else {
                    e.getPeriode().setDateDebut(new Date(dateDebut.getTime()));
                    e.getPeriode().setDateFin(new Date(dateFin.getTime()));
                }
            }

            if (e.getConditionAge() == null) {
                e.setConditionAge(new ConditionAge(ageMin, ageMax));
            } else {
                e.getConditionAge().setAgeMin(ageMin);
                e.getConditionAge().setAgeMax(ageMax);
            }

            e.setStatut(statut);

            Competition oldCompetition = e.getCompetition();
            if (oldCompetition != null && !oldCompetition.equals(competition)) {
                oldCompetition.getEpreuves().remove(e);
            }
            e.setCompetition(competition);
            if (competition != null && !competition.getEpreuves().contains(e)) {
                competition.getEpreuves().add(e);
            }

            System.out.println("Épreuve modifiée : " + id);
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

            if (epreuve.getStatut() == CompetitionStatusEnum.PUBLISH) {
                epreuve.setStatut(CompetitionStatusEnum.CANCELLED);
                repository.save(epreuve);
                System.out.println("Épreuve annulée car elle est déjà publiée : " + id);
                return true;
            }

            boolean hasPhases = epreuve.getEtapesEpreuves() != null && !epreuve.getEtapesEpreuves().isEmpty();

            if (hasPhases) {
                throw new IllegalStateException(
                        "Impossible de supprimer cette épreuve car elle contient des phases.");
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

    @Override
    public List<Epreuve> getEpreuvesByCompetitionId(Long competitionId) {
        return repository.findByCompetitionIdCompetition(competitionId);
    }

    @Override
    public Epreuve publishEpreuve(Long id) {
        Optional<Epreuve> epreuveOpt = repository.findById(id);
        if (epreuveOpt.isPresent()) {
            Epreuve epreuve = epreuveOpt.get();
            if (epreuve.getStatut() != CompetitionStatusEnum.DRAFT) {
                System.out.println("Impossible de publier : l'épreuve n'est pas en mode DRAFT - ID: " + id);
                return null;
            }
            epreuve.setStatut(CompetitionStatusEnum.PUBLISH);
            System.out.println("Épreuve publiée : " + id);
            return repository.save(epreuve);
        }
        System.out.println("Épreuve non trouvée : " + id);
        return null;
    }

    @Override
    public Epreuve startEpreuve(Long id) {
        Optional<Epreuve> epreuveOpt = repository.findById(id);
        if (epreuveOpt.isPresent()) {
            Epreuve epreuve = epreuveOpt.get();
            if (epreuve.getStatut() != CompetitionStatusEnum.PUBLISH) {
                System.out.println("Impossible de démarrer : l'épreuve n'est pas publiée - ID: " + id);
                return null;
            }
            epreuve.setStatut(CompetitionStatusEnum.IN_PROGRESS);
            System.out.println("Épreuve démarrée : " + id);
            return repository.save(epreuve);
        }
        System.out.println("Épreuve non trouvée : " + id);
        return null;
    }

    @Override
    public Epreuve finishEpreuve(Long id) {
        Optional<Epreuve> epreuveOpt = repository.findById(id);
        if (epreuveOpt.isPresent()) {
            Epreuve epreuve = epreuveOpt.get();
            if (epreuve.getStatut() != CompetitionStatusEnum.IN_PROGRESS) {
                System.out.println("Impossible de terminer : l'épreuve n'est pas en cours - ID: " + id);
                return null;
            }
            epreuve.setStatut(CompetitionStatusEnum.FINISHED);
            System.out.println("Épreuve terminée : " + id);
            return repository.save(epreuve);
        }
        System.out.println("Épreuve non trouvée : " + id);
        return null;
    }
}
