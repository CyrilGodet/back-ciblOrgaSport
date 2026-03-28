package com.glop.cibl_orga_sport.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.glop.cibl_orga_sport.data.Lieu;
import com.glop.cibl_orga_sport.data.enumType.LieuCategorieEnum;
import com.glop.cibl_orga_sport.dto.LieuDTO;
import com.glop.cibl_orga_sport.repository.LieuRepository;
import com.glop.cibl_orga_sport.service.LieuService;

@Service
public class LieuServiceImpl implements LieuService {

    @Autowired
    private LieuRepository repository;


    @Override
    public Lieu updateLieu(Long id, String nom, String ville, String adresse, com.glop.cibl_orga_sport.dto.CoordonneesGPSDTO gpsDto) {
        Optional<Lieu> existingLieu = repository.findById(id);
        if (existingLieu.isPresent()) {
            Lieu l = existingLieu.get();
            l.setNomLieu(nom);
            l.setVille(ville);
            l.setAdresse(adresse);
            if (gpsDto != null) {
                l.setGpsCoordinates(new com.glop.cibl_orga_sport.data.CoordonneesGPS(
                    gpsDto.getLatitude(),
                    gpsDto.getLongitude()
                ));
            }
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

    @Override
    public List<Lieu> searchLieux(String query) {
        return repository.findByNomLieuContainingIgnoreCase(query);
    }

    @Override
    public Lieu createLieu(LieuDTO lieuDTO) {
        LieuCategorieEnum categorie = lieuDTO.getCategorie() == null
                ? LieuCategorieEnum.EVENEMENT
                : lieuDTO.getCategorie();
        lieuDTO.setCategorie(categorie);
        Lieu l = com.glop.cibl_orga_sport.mapper.LieuMapper.toEntity(lieuDTO);
        return repository.save(l);
    }

    @Override
    public List<Lieu> getLieuxForAffectations() {
        return repository.findByCategorieOrderByNomLieuAsc(LieuCategorieEnum.EVENEMENT);
    }
}
