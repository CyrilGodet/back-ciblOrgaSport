package com.glop.cibl_orga_sport.mapper;

import com.glop.cibl_orga_sport.data.Category;
import com.glop.cibl_orga_sport.dto.CategoryDTO;

public class CategoryMapper {
    
    public static CategoryDTO toDTO(Category category) {
        if (category == null) return null;
        return new CategoryDTO(
            category.getIdCategory(),
            category.getNameCategory(),
            EpreuveMapper.toDTO(category.getEpreuve())
        );
    }

    public static Category toEntity(CategoryDTO dto) {
        if (dto == null) return null;
        Category category = new Category(dto.getNameCategory());
        return category;
    }
}
