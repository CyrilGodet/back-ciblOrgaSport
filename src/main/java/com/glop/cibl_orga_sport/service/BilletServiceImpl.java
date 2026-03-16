package com.glop.cibl_orga_sport.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.glop.cibl_orga_sport.data.Billet;
import com.glop.cibl_orga_sport.data.Epreuve;
import com.glop.cibl_orga_sport.data.Spectateur;
import com.glop.cibl_orga_sport.dto.BilletDTO;
import com.glop.cibl_orga_sport.mapper.BilletMapper;
import com.glop.cibl_orga_sport.repository.BilletRepository;
import com.glop.cibl_orga_sport.repository.EpreuveRepository;
import com.glop.cibl_orga_sport.repository.SpectateurRepository;

@Service
public class BilletServiceImpl implements BilletService {

    @Autowired
    private BilletRepository billetRepository;

    @Autowired
    private SpectateurRepository spectateurRepository;

    @Autowired
    private EpreuveRepository epreuveRepository;

    @Override
    public Billet createBillet(BilletDTO dto) {
        if (dto == null || dto.getSpectateurId() == null || dto.getEpreuveId() == null || dto.getNumeroBillet() == null
                || dto.getCategorie() == null) {
            return null;
        }

        if (billetRepository.existsByNumeroBillet(dto.getNumeroBillet())) {
            return null;
        }

        Optional<Spectateur> spectateur = spectateurRepository.findById(dto.getSpectateurId());
        Optional<Epreuve> epreuve = epreuveRepository.findById(dto.getEpreuveId());

        if (spectateur.isEmpty() || epreuve.isEmpty()) {
            return null;
        }

        Billet billet = BilletMapper.toEntity(dto);
        billet.setSpectateur(spectateur.get());
        billet.setEpreuve(epreuve.get());
        if (billet.getDateAchat() == null) {
            billet.setDateAchat(LocalDateTime.now());
        }

        return billetRepository.save(billet);
    }

    @Override
    public Billet updateBillet(Long id, BilletDTO dto) {
        Optional<Billet> existing = billetRepository.findById(id);
        if (existing.isEmpty() || dto == null) {
            return null;
        }

        Billet billet = existing.get();

        if (dto.getNumeroBillet() != null && !dto.getNumeroBillet().equals(billet.getNumeroBillet())) {
            if (billetRepository.existsByNumeroBillet(dto.getNumeroBillet())) {
                return null;
            }
            billet.setNumeroBillet(dto.getNumeroBillet());
        }

        if (dto.getCategorie() != null) {
            billet.setCategorie(dto.getCategorie());
        }

        if (dto.getDateAchat() != null) {
            billet.setDateAchat(dto.getDateAchat());
        }

        if (dto.getSpectateurId() != null) {
            Optional<Spectateur> spectateur = spectateurRepository.findById(dto.getSpectateurId());
            if (spectateur.isEmpty()) {
                return null;
            }
            billet.setSpectateur(spectateur.get());
        }

        if (dto.getEpreuveId() != null) {
            Optional<Epreuve> epreuve = epreuveRepository.findById(dto.getEpreuveId());
            if (epreuve.isEmpty()) {
                return null;
            }
            billet.setEpreuve(epreuve.get());
        }

        return billetRepository.save(billet);
    }

    @Override
    public boolean deleteBillet(Long id) {
        Optional<Billet> billet = billetRepository.findById(id);
        if (billet.isPresent()) {
            billetRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public Optional<Billet> getBillet(Long id) {
        return billetRepository.findById(id);
    }

    @Override
    public List<Billet> getAllBillets() {
        return billetRepository.findAll();
    }

    @Override
    public List<Billet> getBilletsBySpectateur(Long spectateurId) {
        return billetRepository.findBySpectateurIdUtilisateur(spectateurId);
    }
}
