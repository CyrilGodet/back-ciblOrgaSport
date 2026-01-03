package com.glop.cibl_orga_sport.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.glop.cibl_orga_sport.data.Category;
import com.glop.cibl_orga_sport.data.Epreuve;
import com.glop.cibl_orga_sport.dto.CategoryDTO;
import com.glop.cibl_orga_sport.mapper.CategoryMapper;
import com.glop.cibl_orga_sport.service.CategoryService;
import com.glop.cibl_orga_sport.service.EpreuveService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/categories")
@CrossOrigin(origins = "http://localhost:4200")
public class CategoryController {
    
    @Autowired
    private CategoryService categoryService;

    @Autowired
    private EpreuveService epreuveService;

    @GetMapping
    public List<CategoryDTO> getAllCategories() {
        return categoryService.getAllCategories().stream()
                .map(CategoryMapper::toDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryDTO> getCategory(@PathVariable Long id) {
        Optional<Category> category = categoryService.getCategory(id);
        return category.map(CategoryMapper::toDTO)
                       .map(ResponseEntity::ok)
                       .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public CategoryDTO createCategory(@RequestBody Category category) {
        Epreuve epreuve = null;
        if (category.getEpreuve() != null && category.getEpreuve().getIdEpreuve() != null) {
            Optional<Epreuve> epreuveOpt = epreuveService.getEpreuve(category.getEpreuve().getIdEpreuve());
            if (epreuveOpt.isPresent()) {
                epreuve = epreuveOpt.get();
            }
        }
        Category created = categoryService.createCategory(category.getNameCategory(), epreuve);
        return CategoryMapper.toDTO(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoryDTO> updateCategory(@PathVariable Long id, @RequestBody Category category) {
        Epreuve epreuve = null;
        if (category.getEpreuve() != null && category.getEpreuve().getIdEpreuve() != null) {
            Optional<Epreuve> epreuveOpt = epreuveService.getEpreuve(category.getEpreuve().getIdEpreuve());
            if (epreuveOpt.isPresent()) {
                epreuve = epreuveOpt.get();
            }
        }
        Category updated = categoryService.updateCategory(id, category.getNameCategory(), epreuve);
        if (updated != null) {
            return ResponseEntity.ok(CategoryMapper.toDTO(updated));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Long id) {
        boolean deleted = categoryService.deleteCategory(id);
        if (deleted) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
