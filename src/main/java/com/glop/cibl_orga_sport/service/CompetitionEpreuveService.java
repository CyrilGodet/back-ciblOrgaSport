package com.glop.cibl_orga_sport.service;

public interface CompetitionEpreuveService {
    
    boolean linkEpreuveToCompetition(Long competitionId, Long epreuveId);
    
    boolean unlinkEpreuveFromCompetition(Long competitionId, Long epreuveId) throws IllegalAccessException;
}
