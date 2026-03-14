package com.glop.cibl_orga_sport.dto;

public class SportifDTO extends UtilisateurDTO {
    public SportifDTO() {
        super();
    }

    public Long getIdSportif() {
        return getIdUtilisateur();
    }

    public void setIdSportif(Long idSportif) {
        setIdUtilisateur(idSportif);
    }
}
