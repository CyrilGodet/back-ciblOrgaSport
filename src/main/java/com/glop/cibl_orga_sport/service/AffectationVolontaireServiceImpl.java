package com.glop.cibl_orga_sport.service;

import com.glop.cibl_orga_sport.data.AffectationVolontaire;
import com.glop.cibl_orga_sport.data.Volontaire;
import com.glop.cibl_orga_sport.data.Epreuve;
import com.glop.cibl_orga_sport.data.Lieu;
import com.glop.cibl_orga_sport.dto.AffectationVolontaireDTO;
import com.glop.cibl_orga_sport.mapper.AffectationVolontaireMapper;
import com.glop.cibl_orga_sport.repository.AffectationVolontaireRepository;
import com.glop.cibl_orga_sport.repository.VolontaireRepository;
import com.glop.cibl_orga_sport.repository.EpreuveRepository;
import com.glop.cibl_orga_sport.repository.LieuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AffectationVolontaireServiceImpl implements AffectationVolontaireService {
    
    @Autowired
    private AffectationVolontaireRepository affectationRepository;
    
    @Autowired
    private VolontaireRepository volontaireRepository;
    
    @Autowired
    private EpreuveRepository epreuveRepository;
    
    @Autowired
    private LieuRepository lieuRepository;
    
    @Override
    public List<AffectationVolontaireDTO> getAllAffectations() {
        return affectationRepository.findAll().stream()
                .map(AffectationVolontaireMapper::toDTO)
                .collect(Collectors.toList());
    }
    
    @Override
    public Optional<AffectationVolontaireDTO> getAffectationById(Long id) {
        return affectationRepository.findById(id)
                .map(AffectationVolontaireMapper::toDTO);
    }
    
    @Override
    public List<AffectationVolontaireDTO> getAffectationsByVolontaire(Long volontaireId) {
        Optional<Volontaire> volontaire = volontaireRepository.findById(volontaireId);
        if (volontaire.isPresent()) {
            return affectationRepository.findByVolontaire(volontaire.get()).stream()
                    .map(AffectationVolontaireMapper::toDTO)
                    .collect(Collectors.toList());
        }
        return List.of();
    }
    
    @Override
    public List<AffectationVolontaireDTO> getAffectationsByCompetition(Long competitionId) {
        return affectationRepository.findByCompetitionId(competitionId).stream()
                .map(AffectationVolontaireMapper::toDTO)
                .collect(Collectors.toList());
    }
    
    @Override
    public List<AffectationVolontaireDTO> getProgrammeVolontaire(Long volontaireId, LocalDate date) {
        Optional<Volontaire> volontaire = volontaireRepository.findById(volontaireId);
        if (volontaire.isPresent()) {
            return affectationRepository.findByVolontaireAndDateAffectation(volontaire.get(), date).stream()
                    .map(AffectationVolontaireMapper::toDTO)
                    .collect(Collectors.toList());
        }
        return List.of();
    }
    
    @Override
    public List<AffectationVolontaireDTO> getProgrammeVolontaireFutur(Long volontaireId) {
        Optional<Volontaire> volontaire = volontaireRepository.findById(volontaireId);
        if (volontaire.isPresent()) {
            return affectationRepository.findByVolontaireAndDateAffectationGreaterThanEqual(
                    volontaire.get(), LocalDate.now()).stream()
                    .map(AffectationVolontaireMapper::toDTO)
                    .collect(Collectors.toList());
        }
        return List.of();
    }
    
    @Override
    public AffectationVolontaireDTO createAffectation(AffectationVolontaireDTO affectationDTO) {
        AffectationVolontaire affectation = AffectationVolontaireMapper.toEntity(affectationDTO);
        
        if (affectationDTO.getIdVolontaire() != null) {
            Optional<Volontaire> volontaire = volontaireRepository.findById(affectationDTO.getIdVolontaire());
            volontaire.ifPresent(affectation::setVolontaire);
        }
        
        if (affectationDTO.getIdEpreuve() != null) {
            Optional<Epreuve> epreuve = epreuveRepository.findById(affectationDTO.getIdEpreuve());
            epreuve.ifPresent(affectation::setEpreuve);
        }
        
        if (affectationDTO.getIdLieuRdv() != null) {
            Optional<Lieu> lieu = lieuRepository.findById(affectationDTO.getIdLieuRdv());
            lieu.ifPresent(affectation::setLieuRdv);
        }
        
        AffectationVolontaire savedAffectation = affectationRepository.save(affectation);
        return AffectationVolontaireMapper.toDTO(savedAffectation);
    }
    
    @Override
    public AffectationVolontaireDTO updateAffectation(Long id, AffectationVolontaireDTO affectationDTO) {
        Optional<AffectationVolontaire> existingAffectation = affectationRepository.findById(id);
        if (existingAffectation.isPresent()) {
            AffectationVolontaire affectation = existingAffectation.get();
            affectation.setDateAffectation(affectationDTO.getDateAffectation());
            affectation.setHeureDebut(affectationDTO.getHeureDebut());
            affectation.setHeureFin(affectationDTO.getHeureFin());
            affectation.setPoste(affectationDTO.getPoste());
            affectation.setCommentaire(affectationDTO.getInstructions());
            
            if (affectationDTO.getIdLieuRdv() != null) {
                Optional<Lieu> lieu = lieuRepository.findById(affectationDTO.getIdLieuRdv());
                lieu.ifPresent(affectation::setLieuRdv);
            }
            
            if (affectationDTO.getIdEpreuve() != null) {
                Optional<Epreuve> epreuve = epreuveRepository.findById(affectationDTO.getIdEpreuve());
                epreuve.ifPresent(affectation::setEpreuve);
            }
            
            AffectationVolontaire updatedAffectation = affectationRepository.save(affectation);
            return AffectationVolontaireMapper.toDTO(updatedAffectation);
        }
        return null;
    }
    
    @Override
    public void deleteAffectation(Long id) {
        affectationRepository.deleteById(id);
    }
}
