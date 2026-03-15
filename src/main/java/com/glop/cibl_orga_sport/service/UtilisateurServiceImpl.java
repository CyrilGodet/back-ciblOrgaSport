package com.glop.cibl_orga_sport.service;

import com.glop.cibl_orga_sport.data.Sportif;
import com.glop.cibl_orga_sport.data.Visiteur;
import com.glop.cibl_orga_sport.data.Commissaire;
import com.glop.cibl_orga_sport.data.Lieu;
import com.glop.cibl_orga_sport.data.ParticipantSportif;
import com.glop.cibl_orga_sport.dto.SportifDTO;
import com.glop.cibl_orga_sport.dto.VisiteurDTO;
import com.glop.cibl_orga_sport.repository.UtilisateurRepository;
import com.glop.cibl_orga_sport.repository.LieuRepository;
import com.glop.cibl_orga_sport.repository.SportifRepository;
import com.glop.cibl_orga_sport.repository.ParticipantSportifRepository;
import com.glop.cibl_orga_sport.mapper.SportifMapper;
import com.glop.cibl_orga_sport.mapper.VisiteurMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class UtilisateurServiceImpl implements UtilisateurService {

    @Autowired
    private UtilisateurRepository repository;

    @Autowired
    private SportifRepository sportifRepository;

    @Autowired
    private ParticipantSportifRepository participantSportifRepository;

    @Autowired
    private LieuRepository lieuRepository;

    @Override
    public Sportif createSportif(SportifDTO dto) {
        Sportif sportif = SportifMapper.toEntity(dto);
        if (dto.getLieu() != null && dto.getLieu().getIdLieu() != null) {
            Optional<Lieu> lieu = lieuRepository.findById(dto.getLieu().getIdLieu());
            lieu.ifPresent(sportif::setLieu);
        }
        Sportif savedSportif = repository.save(sportif);
        // Automatically create a ParticipantSportif wrapper
        ParticipantSportif participantSportif = new ParticipantSportif(savedSportif);
        participantSportifRepository.save(participantSportif);
        return savedSportif;
    }

    @Override
    public Visiteur createVisiteur(VisiteurDTO dto) {
        Visiteur visiteur = VisiteurMapper.toEntity(dto);
        if (dto.getLieu() != null && dto.getLieu().getIdLieu() != null) {
            Optional<Lieu> lieu = lieuRepository.findById(dto.getLieu().getIdLieu());
            lieu.ifPresent(visiteur::setLieu);
        }
        return repository.save(visiteur);
    }

    @Override
    public List<Sportif> getAllSportifs() {
        return repository.findAllSportifs();
    }

    @Override
    public List<Visiteur> getAllVisiteurs() {
        return repository.findAllVisiteurs();
    }

    @Override
    public List<Commissaire> getAllCommissaires() {
        return repository.findAllCommissaires();
    }

    @Override
    public List<Sportif> searchSportifs(String query) {
        return sportifRepository.findByNomContainingIgnoreCaseOrPrenomContainingIgnoreCaseOrEmailContainingIgnoreCase(
                query, query, query);
    }

    @Override
    public List<ParticipantSportif> searchParticipantSportifs(String query) {
        return participantSportifRepository.searchParticipantSportifs(query);
    }
}
