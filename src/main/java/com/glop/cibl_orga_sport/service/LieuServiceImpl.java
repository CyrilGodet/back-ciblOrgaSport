package com.glop.cibl_orga_sport.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.glop.cibl_orga_sport.data.Lieu;
import com.glop.cibl_orga_sport.repository.LieuRepository;

@Service
public class LieuServiceImpl implements LieuService {

    @Autowired
    private LieuRepository repository;

    @Override
    public Lieu createLieu(String nom, String ville, String adresse) {
        Lieu l = new Lieu(nom, ville, adresse);
        System.out.println("Création lieu : " + nom);
        return repository.save(l);
    }

    @Override
    public Lieu updateLieu(Long id, String nom, String ville, String adresse) {
        Optional<Lieu> existingLieu = repository.findById(id);
        if (existingLieu.isPresent()) {
            Lieu l = existingLieu.get();
            l.setNomLieu(nom);
            l.setVille(ville);
            l.setAdresse(adresse);
            System.out.println("Modification lieu : " + id);
            return repository.save(l);
        }
        System.out.println("Lieu non trouvé : " + id);
        return null;
    }

    @Override
    public boolean deleteLieu(Long id) {
        Optional<Lieu> l = repository.findById(id);
        if (l.isPresent()) {
            Lieu lieu = l.get();
            if (lieu.getPhases() != null && !lieu.getPhases().isEmpty()) {
                throw new IllegalStateException("Impossible de supprimer ce lieu car il est lié à " + 
                    lieu.getPhases().size() + " phase(s) existante(s).");
            }
            repository.deleteById(id);
            System.out.println("Lieu supprimé : " + id);
            return true;
        }
        System.out.println("Lieu non trouvé : " + id);
        return false;
    }

    @Override
    public List<Lieu> getAllLieux() {
        return repository.findAll();
    }

    @Override
    public Optional<Lieu> getLieu(Long id) {
        return repository.findById(id);
    }
}
