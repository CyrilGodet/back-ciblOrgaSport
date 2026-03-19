package com.glop.cibl_orga_sport.dto;

public class AccountCreationDTO extends UtilisateurDTO {
    private String username;
    private String password;

    public AccountCreationDTO() {
        super();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
