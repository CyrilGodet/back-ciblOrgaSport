package com.glop.cibl_orga_sport.service;

import com.glop.cibl_orga_sport.data.Utilisateur;
import com.glop.cibl_orga_sport.data.auth.request.RequestChangePassword;
import com.glop.cibl_orga_sport.dto.UtilisateurDTO;
import com.glop.cibl_orga_sport.exception.InvalidEntityException;
import com.glop.cibl_orga_sport.exception.InvalidOperationException;
import com.glop.cibl_orga_sport.repository.UtilisateurRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class ChangeMdpImplService implements ChangeMdpService {
    private final UtilisateurRepository userDao;
    private final PasswordEncoder passwordEncoder;
    private final HistoryService historyService;
    private final UtilisateurService userService;
    @Override
    public boolean isTruePassword(String login, String mdp) {
        log.info("---->" + this.getClass().getName() + "is Password start ");
        Optional<Utilisateur> user = userDao.findByEmail(login);
        if (user != null) {
            log.info("Find user True");
            String encodedPassword = user.get().getPassword();
            boolean check = passwordEncoder.matches(mdp, encodedPassword);
            return check;
        } else {
            return false;
        }
    }


    @Override
    public int changeUserPassword(UtilisateurDTO userDto, String newPassword) {
        log.info(this.getClass().getName() + "changeUserPassword start!");
        String userLogin = userDto.getEmail();
        String userPassword = userDto.getMdp();
        boolean checkPassword = isTruePassword(userLogin, userPassword);

        if (checkPassword) {
            log.info("vrai mdp");
            userDao.changeUserPassword(userLogin, passwordEncoder.encode(newPassword));
            log.info(this.getClass().getName() + "changeUserPassword end!");
            return 1;
        }
        log.info(this.getClass().getName() + "changeUserPassword end!");
        return 0;
    }
    @Override
    public int changeUserPasswordCheck(RequestChangePassword requestChangePassword) {
        String email = requestChangePassword.getUserLogin();
        UtilisateurDTO userDtoFind = userService.findByEmail(email);
        if (userDtoFind == null) {
            historyService.saveHistory("Change password", "failed");
            throw new InvalidEntityException("User not found.");
        }
        String plainPassword = requestChangePassword.getOldPassword();
        String storedHashedPassword = userDtoFind.getMdp();
        if (passwordEncoder.matches(plainPassword, storedHashedPassword) == false) {
            historyService.saveHistory("Change password", "failed");
            throw new InvalidEntityException("Please insert the correct password cher user");
        }

        if (!requestChangePassword.getNewPassword().equals(requestChangePassword.getNewPasswordVerify())) {
            historyService.saveHistory("Change password", "failed");
            throw new InvalidEntityException("The passwords do not match.");
        }

        String userCoLogin = historyService.getUserConnected();
        System.out.println(userCoLogin);

        userDtoFind.setEmail(requestChangePassword.getUserLogin());
        userDtoFind.setMdp(requestChangePassword.getOldPassword());
        Utilisateur user = UtilisateurDTO.toEntity(userDtoFind);
        System.out.println("user = " + user);

        int check = changeUserPassword(userDtoFind, requestChangePassword.getNewPassword());
        if (check == 1) {
            return 1;
        } else {
            historyService.saveHistory("Change password", "failed");
            throw new InvalidOperationException("Changing the password is impossible");
        }

    }

}
