package com.glop.cibl_orga_sport.service;

import com.glop.cibl_orga_sport.data.Category;
import com.glop.cibl_orga_sport.data.Epreuve;
import java.util.List;
import java.util.Optional;

public interface CategoryService {

    Category createCategory(String nameCategory, Epreuve epreuve);
    Category updateCategory(Long id, String nameCategory, Epreuve epreuve);
    boolean deleteCategory(Long id);
    List<Category> getAllCategories();
    Optional<Category> getCategory(Long id);
}
