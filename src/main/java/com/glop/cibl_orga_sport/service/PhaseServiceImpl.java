package com.glop.cibl_orga_sport.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.glop.cibl_orga_sport.data.Phase;
import com.glop.cibl_orga_sport.data.Epreuve;
import com.glop.cibl_orga_sport.repository.PhaseRepository;

@Service
public class PhaseServiceImpl implements PhaseService {

    @Autowired
    private PhaseRepository repository;

    @Override
    public Phase createPhase(String nomPhase, Epreuve epreuve) {
        Phase p = new Phase(nomPhase, epreuve);
        System.out.println("Création phase : " + nomPhase);
        return repository.save(p);
    }

    @Override
    public Phase updatePhase(Long id, String nomPhase, Epreuve epreuve) {
        Optional<Phase> existingPhase = repository.findById(id);
        if (existingPhase.isPresent()) {
            Phase p = existingPhase.get();
            p.setNomPhase(nomPhase);
            Epreuve oldEpreuve = p.getEpreuve();
            if (oldEpreuve != null && !oldEpreuve.equals(epreuve)) {
                oldEpreuve.getPhases().remove(p);
            }
            p.setEpreuve(epreuve);
            if (epreuve != null && !epreuve.getPhases().contains(p)) {
                epreuve.getPhases().add(p);
            }
            System.out.println("Modification phase : " + id);
            return repository.save(p);
        }
        System.out.println("Phase non trouvée : " + id);
        return null;
    }

    @Override
    public boolean deletePhase(Long id) {
        Optional<Phase> p = repository.findById(id);
        if (p.isPresent()) {
            repository.deleteById(id);
            System.out.println("Phase supprimée : " + id);
            return true;
        }
        System.out.println("Phase non trouvée : " + id);
        return false;
    }

    @Override
    public List<Phase> getAllPhases() {
        return repository.findAll();
    }

    @Override
    public Optional<Phase> getPhase(Long id) {
        return repository.findById(id);
    }
}
