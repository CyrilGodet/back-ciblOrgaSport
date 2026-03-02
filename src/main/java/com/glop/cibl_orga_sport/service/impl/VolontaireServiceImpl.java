package com.glop.cibl_orga_sport.service.impl;

import com.glop.cibl_orga_sport.data.Volontaire;
import com.glop.cibl_orga_sport.dto.VolontaireDTO;
import com.glop.cibl_orga_sport.mapper.VolontaireMapper;
import com.glop.cibl_orga_sport.repository.VolontaireRepository;
import com.glop.cibl_orga_sport.service.VolontaireService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class VolontaireServiceImpl implements VolontaireService {
    
    @Autowired
    private VolontaireRepository volontaireRepository;
    
    @Override
    public List<VolontaireDTO> getAllVolontaires() {
        return volontaireRepository.findAll().stream()
                .map(VolontaireMapper::toDTO)
                .collect(Collectors.toList());
    }
    
    @Override
    public Optional<VolontaireDTO> getVolontaireById(Long id) {
        return volontaireRepository.findById(id)
                .map(VolontaireMapper::toDTO);
    }
    
    @Override
    public Optional<VolontaireDTO> getVolontaireByEmail(String email) {
        return volontaireRepository.findByEmail(email)
                .map(VolontaireMapper::toDTO);
    }
    
    @Override
    public VolontaireDTO createVolontaire(VolontaireDTO volontaireDTO) {
        Volontaire volontaire = VolontaireMapper.toEntity(volontaireDTO);
        Volontaire savedVolontaire = volontaireRepository.save(volontaire);
        return VolontaireMapper.toDTO(savedVolontaire);
    }
    
    @Override
    public VolontaireDTO updateVolontaire(Long id, VolontaireDTO volontaireDTO) {
        Optional<Volontaire> existingVolontaire = volontaireRepository.findById(id);
        if (existingVolontaire.isPresent()) {
            Volontaire volontaire = existingVolontaire.get();
            volontaire.setNom(volontaireDTO.getNom());
            volontaire.setPrenom(volontaireDTO.getPrenom());
            volontaire.setEmail(volontaireDTO.getEmail());
            volontaire.setTelephone(volontaireDTO.getTelephone());
            volontaire.setCompetences(volontaireDTO.getCompetences());
            
            Volontaire updatedVolontaire = volontaireRepository.save(volontaire);
            return VolontaireMapper.toDTO(updatedVolontaire);
        }
        return null;
    }
    
    @Override
    public void deleteVolontaire(Long id) {
        volontaireRepository.deleteById(id);
    }
}
