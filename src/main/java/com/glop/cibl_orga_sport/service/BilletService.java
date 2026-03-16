package com.glop.cibl_orga_sport.service;

import java.util.List;
import java.util.Optional;

import com.glop.cibl_orga_sport.data.Billet;
import com.glop.cibl_orga_sport.dto.BilletDTO;

public interface BilletService {
    public Billet createBillet(BilletDTO dto);

    public Billet updateBillet(Long id, BilletDTO dto);

    public boolean deleteBillet(Long id);

    public Optional<Billet> getBillet(Long id);

    public List<Billet> getAllBillets();

    public List<Billet> getBilletsBySpectateur(Long spectateurId);
}
