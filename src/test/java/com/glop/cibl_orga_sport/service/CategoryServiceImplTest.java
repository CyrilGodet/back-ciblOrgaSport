package com.glop.cibl_orga_sport.service;

import com.glop.cibl_orga_sport.data.Category;
import com.glop.cibl_orga_sport.data.Epreuve;
import com.glop.cibl_orga_sport.repository.CategoryRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CategoryServiceImplTest {

    @Mock
    private CategoryRepository categoryRepository;

    @InjectMocks
    private CategoryServiceImpl categoryService;

    @Test
    void testCreateCategory() {
        Epreuve epreuve = new Epreuve("100m nage libre");
        epreuve.setIdEpreuve(1L);

        Category category = new Category("Hommes");
        category.setIdCategory(1L);
        category.setEpreuve(epreuve);

        when(categoryRepository.save(any(Category.class))).thenReturn(category);

        Category result = categoryService.createCategory("Hommes", epreuve);

        assertNotNull(result);
        assertEquals("Hommes", result.getNameCategory());
        assertEquals(epreuve, result.getEpreuve());
    }

    @Test
    void testGetAllCategories() {
        Category category1 = new Category("Hommes");
        category1.setIdCategory(1L);

        Category category2 = new Category("Femmes");
        category2.setIdCategory(2L);

        when(categoryRepository.findAll()).thenReturn(Arrays.asList(category1, category2));

        List<Category> result = categoryService.getAllCategories();

        assertEquals(2, result.size());
        assertEquals("Hommes", result.get(0).getNameCategory());
        assertEquals("Femmes", result.get(1).getNameCategory());
    }

    @Test
    void testGetCategory() {
        Category category = new Category("Hommes");
        category.setIdCategory(1L);

        when(categoryRepository.findById(1L)).thenReturn(Optional.of(category));

        Optional<Category> result = categoryService.getCategory(1L);

        assertTrue(result.isPresent());
        assertEquals("Hommes", result.get().getNameCategory());
    }

    @Test
    void testUpdateCategory() {
        Epreuve epreuve = new Epreuve("100m nage libre");
        epreuve.setIdEpreuve(1L);

        Category existingCategory = new Category("Hommes");
        existingCategory.setIdCategory(1L);

        when(categoryRepository.findById(1L)).thenReturn(Optional.of(existingCategory));
        when(categoryRepository.save(any(Category.class))).thenReturn(existingCategory);

        Category result = categoryService.updateCategory(1L, "Hommes Modified", epreuve);

        assertNotNull(result);
        assertEquals("Hommes Modified", result.getNameCategory());
        assertEquals(epreuve, result.getEpreuve());
    }

    @Test
    void testUpdateCategory_NotFound() {
        when(categoryRepository.findById(999L)).thenReturn(Optional.empty());

        Category result = categoryService.updateCategory(999L, "Hommes", null);

        assertNull(result);
    }

    @Test
    void testDeleteCategory() {
        Category category = new Category("Hommes");
        category.setIdCategory(1L);

        when(categoryRepository.findById(1L)).thenReturn(Optional.of(category));

        boolean result = categoryService.deleteCategory(1L);

        assertTrue(result);
    }

    @Test
    void testDeleteCategory_NotFound() {
        when(categoryRepository.findById(999L)).thenReturn(Optional.empty());

        boolean result = categoryService.deleteCategory(999L);

        assertFalse(result);
    }
}
