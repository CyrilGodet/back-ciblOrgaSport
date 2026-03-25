package com.glop.cibl_orga_sport.dto;

public class ValidationSaisieResultatsRequestDTO {

    private Long idCommissaire;
    private boolean saisieManuelle;

    public ValidationSaisieResultatsRequestDTO() {
    }

    public Long getIdCommissaire() {
        return idCommissaire;
    }

    public void setIdCommissaire(Long idCommissaire) {
        this.idCommissaire = idCommissaire;
    }

    public boolean isSaisieManuelle() {
        return saisieManuelle;
    }

    public void setSaisieManuelle(boolean saisieManuelle) {
        this.saisieManuelle = saisieManuelle;
    }
}
