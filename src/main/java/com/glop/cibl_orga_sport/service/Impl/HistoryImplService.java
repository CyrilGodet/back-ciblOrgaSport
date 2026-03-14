package com.glop.cibl_orga_sport.service.Impl;

import com.glop.cibl_orga_sport.data.History;
import com.glop.cibl_orga_sport.data.Utilisateur;
import com.glop.cibl_orga_sport.dto.HistoryDto;
import com.glop.cibl_orga_sport.repository.HistoryDao;
import com.glop.cibl_orga_sport.service.HistoryService;

import java.util.List;
import java.util.stream.Collectors;import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class HistoryImplService implements HistoryService {
    private HistoryDao historyDao;

    @Autowired
    public HistoryImplService(HistoryDao historyDao) {
        this.historyDao = historyDao;
    }

    @Override
    public String getUserConnected() {
        return "";
    }

    //SI ERREUR
    public void saveHistory(String action, String status) {
        History history = new History();
        history.setAction(action);
        history.setStatus(status);
        historyDao.save(history);
    }

    @Override
    public void saveHistory(String action, String status, Utilisateur user) {
        History history = new History();
        history.setUserConnected(user.getEmail());
        history.setUser(user);
        history.setAction(action);
        history.setStatus(status);
        historyDao.save(history);
    }

    @Override
    public void saveHistoryLogin(String action, String status, Utilisateur user, String login) {
        String userConnected = getUserConnected();
        History history = new History();
        history.setUserConnected(userConnected);
        history.setUser(user);
        history.setAction(action);
        history.setStatus(status);
        historyDao.save(history);
    }


//
//    public String getUserConnected() {
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//
//        if (authentication != null && authentication.getPrincipal() instanceof UserDetails) {
//            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
//            return userDetails.getUsername();
//        } else {
//            return "Attempt new User";
//        }
//    }

    @Override
    public List<HistoryDto> findAll() {
        return historyDao.findAll().stream()
                .map(HistoryDto::fromEntity)
                .collect(Collectors.toList());

    }
}
