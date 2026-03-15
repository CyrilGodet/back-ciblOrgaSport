package com.glop.cibl_orga_sport.data.auth.request;
import lombok.Data;

@Data
public class RequestChangePassword {
    private String userLogin;
    private String oldPassword;
    private String newPassword;
    private String newPasswordVerify;
}
