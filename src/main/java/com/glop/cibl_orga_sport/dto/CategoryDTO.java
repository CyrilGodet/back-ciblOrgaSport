package com.glop.cibl_orga_sport.dto;

public class CategoryDTO {
    private Long idCategory;
    private String nameCategory;
    private EpreuveDTO epreuve;

    public CategoryDTO() {}

    public CategoryDTO(Long idCategory, String nameCategory, EpreuveDTO epreuve) {
        this.idCategory = idCategory;
        this.nameCategory = nameCategory;
        this.epreuve = epreuve;
    }

    public Long getIdCategory() {
        return idCategory;
    }

    public void setIdCategory(Long idCategory) {
        this.idCategory = idCategory;
    }

    public String getNameCategory() {
        return nameCategory;
    }

    public void setNameCategory(String nameCategory) {
        this.nameCategory = nameCategory;
    }

    public EpreuveDTO getEpreuve() {
        return epreuve;
    }

    public void setEpreuve(EpreuveDTO epreuve) {
        this.epreuve = epreuve;
    }
}
