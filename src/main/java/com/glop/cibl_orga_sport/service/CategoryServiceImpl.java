package com.glop.cibl_orga_sport.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.glop.cibl_orga_sport.data.Category;
import com.glop.cibl_orga_sport.data.Epreuve;
import com.glop.cibl_orga_sport.repository.CategoryRepository;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository repository;

    @Override
    public Category createCategory(String nameCategory, Epreuve epreuve) {
        Category c = new Category(nameCategory);
        c.setEpreuve(epreuve);
        System.out.println("Création catégorie : " + nameCategory);
        return repository.save(c);
    }

    @Override
    public Category updateCategory(Long id, String nameCategory, Epreuve epreuve) {
        Optional<Category> existingCategory = repository.findById(id);
        if (existingCategory.isPresent()) {
            Category c = existingCategory.get();
            c.setNameCategory(nameCategory);
            Epreuve oldEpreuve = c.getEpreuve();
            if (oldEpreuve != null && !oldEpreuve.equals(epreuve)) {
                oldEpreuve.getCategories().remove(c);
            }
            c.setEpreuve(epreuve);
            if (epreuve != null && !epreuve.getCategories().contains(c)) {
                epreuve.getCategories().add(c);
            }
            System.out.println("Modification catégorie : " + id);
            return repository.save(c);
        }
        System.out.println("Catégorie non trouvée : " + id);
        return null;
    }

    @Override
    public boolean deleteCategory(Long id) {
        Optional<Category> c = repository.findById(id);
        if (c.isPresent()) {
            repository.deleteById(id);
            System.out.println("Catégorie supprimée : " + id);
            return true;
        }
        System.out.println("Catégorie non trouvée : " + id);
        return false;
    }

    @Override
    public List<Category> getAllCategories() {
        return repository.findAll();
    }

    @Override
    public Optional<Category> getCategory(Long id) {
        return repository.findById(id);
    }
}
