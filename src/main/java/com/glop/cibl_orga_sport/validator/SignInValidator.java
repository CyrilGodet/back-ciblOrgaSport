package com.glop.cibl_orga_sport.validator;

import com.glop.cibl_orga_sport.dto.UtilisateurDTO;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class SignInValidator {
    public static List<String> validate(UtilisateurDTO userDto) {
        List<String> errors = new ArrayList<>();
        if (userDto == null) {
            errors.add("Veuillez renseigner l'information de l'user");
            return errors;
        }

        if (!StringUtils.hasLength(userDto.getEmail())) {
            errors.add("Veuillez renseigner le nom de l'user");
        }
        if (!StringUtils.hasLength(userDto.getMdp())) {
            errors.add("Veuillez renseigner le mot de passe de l'user");
        }
        if (!StringUtils.hasLength(userDto.getNom())) {
            errors.add("Veuillez renseigner le nom de l'user");
        }
        if (!StringUtils.hasLength(userDto.getPrenom())) {
            errors.add("Veuillez renseigner le prénom de l'user");
        }
        return errors;
    }
}
