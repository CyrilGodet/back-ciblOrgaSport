package com.glop.cibl_orga_sport.service;

import com.glop.cibl_orga_sport.data.auth.request.RequestChangePassword;
import com.glop.cibl_orga_sport.dto.UtilisateurDTO;

public interface ChangeMdpService {
    boolean isTruePassword(String login, String mdp);

    int changeUserPassword(UtilisateurDTO userDto, String newPassword);

    int changeUserPasswordCheck(RequestChangePassword requestChangePassword);


}