package com.glop.cibl_orga_sport.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.glop.cibl_orga_sport.data.EtapeEpreuve;
import com.glop.cibl_orga_sport.data.Epreuve;
import com.glop.cibl_orga_sport.data.Lieu;
import com.glop.cibl_orga_sport.repository.PhaseRepository;

import java.sql.Date;

@Service
public class PhaseServiceImpl implements PhaseService {

    @Autowired
    private PhaseRepository repository;

    @Override
    public EtapeEpreuve createPhase(String nomPhase, Date dateDebut, Date dateFin, Epreuve epreuve,
            Lieu lieu) {
        EtapeEpreuve p = new EtapeEpreuve(nomPhase, dateDebut, dateFin, epreuve, lieu);
        if (epreuve != null) {
            if (epreuve.getEtapesEpreuves() == null)
                epreuve.setPhases(new java.util.HashSet<>());
            epreuve.getEtapesEpreuves().add(p);
        }
        if (lieu != null) {
            if (lieu.getPhases() == null)
                lieu.setPhases(new java.util.HashSet<>());
            lieu.getPhases().add(p);
        }
        System.out.println("Création phase : " + nomPhase);
        return repository.save(p);
    }

    @Override
    public EtapeEpreuve updatePhase(Long id, String nomPhase, java.sql.Date dateDebut, java.sql.Date dateFin, Epreuve epreuve,
            Lieu lieu) {
        Optional<EtapeEpreuve> existingPhase = repository.findById(id);
        if (existingPhase.isPresent()) {
            EtapeEpreuve p = existingPhase.get();
            p.setNomPhase(nomPhase);
            p.setDateDebut(dateDebut);
            p.setDateFin(dateFin);
            Epreuve oldEpreuve = p.getEpreuve();
            if (oldEpreuve != null && !oldEpreuve.equals(epreuve)) {
                oldEpreuve.getEtapesEpreuves().remove(p);
            }
            p.setEpreuve(epreuve);
            if (epreuve != null && !epreuve.getEtapesEpreuves().contains(p)) {
                epreuve.getEtapesEpreuves().add(p);
            }
            Lieu oldLieu = p.getLieu();
            if (oldLieu != null && !oldLieu.equals(lieu)) {
                oldLieu.getPhases().remove(p);
            }
            p.setLieu(lieu);
            if (lieu != null && !lieu.getPhases().contains(p)) {
                lieu.getPhases().add(p);
            }
            System.out.println("Modification phase : " + id);
            return repository.save(p);
        }
        System.out.println("Phase non trouvée : " + id);
        return null;
    }

    @Override
    public boolean deletePhase(Long id) {
        Optional<EtapeEpreuve> p = repository.findById(id);
        if (p.isPresent()) {
            repository.deleteById(id);
            System.out.println("Phase supprimée : " + id);
            return true;
        }
        System.out.println("Phase non trouvée : " + id);
        return false;
    }

    @Override
    public List<EtapeEpreuve> getAllPhases() {
        return repository.findAll();
    }

    @Override
    public Optional<EtapeEpreuve> getPhase(Long id) {
        return repository.findById(id);
    }

    @Override
    public List<EtapeEpreuve> getPhasesByCompetitionId(Long competitionId) {
        return repository.findByEpreuveCompetitionIdCompetition(competitionId);
    }
}
