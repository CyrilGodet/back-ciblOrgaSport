package com.glop.cibl_orga_sport.service;

import com.glop.cibl_orga_sport.data.Utilisateur;
import com.glop.cibl_orga_sport.dto.HistoryDto;
import java.util.List;

public interface HistoryService {
    String getUserConnected();
    void saveHistory(String action, String status);

    void saveHistory(String action, String status, Utilisateur user);

    //void saveHistoryWithoutPassword(String action, String status, User user);

    public void saveHistoryLogin(String action, String status, Utilisateur user, String login);
    List<HistoryDto> findAll();

    //void saveHistoryChangeMdp(String action, String status, User userCo);
}
