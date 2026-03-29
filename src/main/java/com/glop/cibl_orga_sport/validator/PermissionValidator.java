package com.glop.cibl_orga_sport.validator;

import com.glop.cibl_orga_sport.dto.PermissionDto;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class PermissionValidator {
    public static List<String> validate(PermissionDto permissionDto) {
        List<String> errors = new ArrayList<>();
        if (permissionDto == null) {
            errors.add("VEuillez renseigner l'information à propos de la permission");
            return errors;
        }

        if (!StringUtils.hasLength(permissionDto.getCaption())) {
            errors.add("Veuillez renseigner le caption");
        }

        if (permissionDto.getCredential() == 0) {
            errors.add("Veuillez renseigner le credential");
        }

        if (permissionDto.getCredential() == 0) {
            errors.add("Veuillez renseigner le credential");
        }
        return errors;
    }
}
