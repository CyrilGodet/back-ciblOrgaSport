package com.glop.cibl_orga_sport.validator;

import com.glop.cibl_orga_sport.dto.RolesDto;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class RolesValidator {
    public static List<String> validate(RolesDto rolesDto) {
        List<String> errors = new ArrayList<>();
        if (rolesDto == null) {
            errors.add("Veuillez renseigner le nom de la catégorie");
            return errors;
        }

        if (!StringUtils.hasLength(rolesDto.getDesignation()) || rolesDto.getDesignation().equals(" ")) {
            errors.add("Veuillez renseigner le nom de la catégorie");
        }
        return errors;
    }
}
