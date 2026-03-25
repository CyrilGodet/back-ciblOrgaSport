package com.glop.cibl_orga_sport.validator;

import com.glop.cibl_orga_sport.data.auth.request.SignUpRequest;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class SignUpValidator {
    public static List<String> validate(SignUpRequest signUpRequest) {
        List<String> errors = new ArrayList<>();
        if (signUpRequest == null) {
            errors.add("Veuillez renseigner l'information de l'user");
            return errors;
        }

        if (!StringUtils.hasLength(signUpRequest.getLogin()) || signUpRequest.getLogin().equals(" ")) {
            errors.add("Veuillez renseigner le nom de l'user");
        }

        if (!StringUtils.hasLength(signUpRequest.getMdp()) || signUpRequest.getMdp().equals(" ")) {
            errors.add("Veuillez renseigner le mot de passe de l'user");
        }
        if (!StringUtils.hasLength(signUpRequest.getVerifyMdp()) || signUpRequest.getVerifyMdp().equals(" ")) {
            errors.add("Veuillez renseigner la verification du  mot de passe de l'user");
        }

        if (!StringUtils.hasLength(signUpRequest.getName()) || signUpRequest.getName().equals(" ") ) {
            errors.add("Veuillez renseigner le nom de l'user");
        }
        if (!StringUtils.hasLength(signUpRequest.getLastname()) || signUpRequest.getLastname().equals(" ")) {
            errors.add("Veuillez renseigner le prénom de l'user");
        }
        return errors;
    }
}
