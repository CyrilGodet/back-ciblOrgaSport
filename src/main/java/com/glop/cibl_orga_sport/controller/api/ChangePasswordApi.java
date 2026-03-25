package com.glop.cibl_orga_sport.controller.api;

import com.glop.cibl_orga_sport.data.auth.request.RequestChangePassword;
import com.glop.cibl_orga_sport.utils.Constants;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping(Constants.MDP_CHANGE_ENDPOINT)
public interface ChangePasswordApi {
    @PostMapping(value = "/change", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<String> changePassword(@RequestBody RequestChangePassword requestChangePassword);

}