package com.glop.cibl_orga_sport.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.glop.cibl_orga_sport.data.EtapeEpreuve;
import com.glop.cibl_orga_sport.data.Epreuve;
import com.glop.cibl_orga_sport.data.Periode;
import com.glop.cibl_orga_sport.data.Resultat;
import com.glop.cibl_orga_sport.data.enumType.EtapeEpreuveEnum;
import com.glop.cibl_orga_sport.repository.PhaseRepository;
import com.glop.cibl_orga_sport.service.PhaseService;

import java.sql.Date;

@Service
public class PhaseServiceImpl implements PhaseService {

    @Autowired
    private PhaseRepository repository;

    @Override
    public EtapeEpreuve createPhase(Epreuve epreuve, Date dateDebut, Date dateFin,
                                   EtapeEpreuveEnum etapeEnum, Resultat resultat) {
        Periode periode = new Periode(dateDebut, dateFin);
        EtapeEpreuve p = new EtapeEpreuve(epreuve, periode, resultat, etapeEnum);
        
        if (epreuve != null) {
            if (epreuve.getEtapesEpreuves() == null)
                epreuve.setEtapesEpreuves(new java.util.ArrayList<>());
            epreuve.getEtapesEpreuves().add(p);
        }
        
        System.out.println("Création phase : " + (etapeEnum != null ? etapeEnum.name() : "null"));
        return repository.save(p);
    }

    @Override
    public EtapeEpreuve updatePhase(Long id, Epreuve epreuve, Date dateDebut, Date dateFin,
                                   EtapeEpreuveEnum etapeEnum, Resultat resultat) {
        Optional<EtapeEpreuve> existingPhase = repository.findById(id);
        if (existingPhase.isPresent()) {
            EtapeEpreuve p = existingPhase.get();
            
            if (dateDebut != null && dateFin != null) {
                if (p.getPeriode() == null) {
                    p.setPeriode(new Periode(dateDebut, dateFin));
                } else {
                    p.getPeriode().setDateDebut(dateDebut);
                    p.getPeriode().setDateFin(dateFin);
                }
            }
            
            p.setEtapeEpreuveEnum(etapeEnum);
            p.setResultat(resultat);
            
            Epreuve oldEpreuve = p.getEpreuve();
            if (oldEpreuve != null && !oldEpreuve.equals(epreuve)) {
                oldEpreuve.getEtapesEpreuves().remove(p);
            }
            p.setEpreuve(epreuve);
            if (epreuve != null && !epreuve.getEtapesEpreuves().contains(p)) {
                epreuve.getEtapesEpreuves().add(p);
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
