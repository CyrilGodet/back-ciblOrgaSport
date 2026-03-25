package com.glop.cibl_orga_sport.controller;

import com.glop.cibl_orga_sport.controller.api.ChangePasswordApi;
import com.glop.cibl_orga_sport.data.auth.request.RequestChangePassword;
import com.glop.cibl_orga_sport.service.ChangeMdpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class ChangePasswordController implements ChangePasswordApi {
    private ChangeMdpService changeMdpService;

    @Autowired
    public ChangePasswordController(ChangeMdpService changeMdpService) {
        this.changeMdpService = changeMdpService;
    }


    @Override
    public ResponseEntity<String> changePassword(RequestChangePassword requestChangePassword) {
        int result = changeMdpService.changeUserPasswordCheck(requestChangePassword);
        if (result == 1) {
            return ResponseEntity.ok("Password changed successfully.");
        } else {
            return ResponseEntity.badRequest().body("Failed to change the password.");
        }
    }
}