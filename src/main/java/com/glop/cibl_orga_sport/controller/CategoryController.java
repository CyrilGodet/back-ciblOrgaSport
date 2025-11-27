package com.glop.cibl_orga_sport.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.glop.cibl_orga_sport.data.Category;
import com.glop.cibl_orga_sport.data.Epreuve;
import com.glop.cibl_orga_sport.service.CategoryService;
import com.glop.cibl_orga_sport.service.EpreuveService;

import java.util.Optional;

@Controller
@RequestMapping("/categories")
public class CategoryController {
    
    @Autowired
    private CategoryService categoryService;

    @Autowired
    private EpreuveService epreuveService;

    @GetMapping
    public ModelAndView listCategories() {
        ModelAndView model = new ModelAndView("category/list");
        model.addObject("categories", categoryService.getAllCategories());
        return model;
    }

    @GetMapping("/new")
    public ModelAndView showForm() {
        ModelAndView model = new ModelAndView("category/form");
        model.addObject("category", new Category());
        model.addObject("epreuves", epreuveService.getAllEpreuves());
        model.addObject("isEdit", false);
        return model;
    }

    @GetMapping("/edit/{id}")
    public ModelAndView showEditForm(@PathVariable Long id) {
        Optional<Category> category = categoryService.getCategory(id);
        if (category.isPresent()) {
            ModelAndView model = new ModelAndView("category/form");
            model.addObject("category", category.get());
            model.addObject("epreuves", epreuveService.getAllEpreuves());
            model.addObject("isEdit", true);
            return model;
        }
        return new ModelAndView("redirect:/categories");
    }

    @PostMapping("/save")
    public RedirectView saveCategory(@ModelAttribute Category category, @RequestParam(required = false) Long epreuveId) {
        Epreuve epreuve = null;
        if (epreuveId != null) {
            Optional<Epreuve> epreuveOpt = epreuveService.getEpreuve(epreuveId);
            if (epreuveOpt.isPresent()) {
                epreuve = epreuveOpt.get();
            }
        }

        if (category.getIdCategory() != null) {
            categoryService.updateCategory(category.getIdCategory(), category.getNameCategory(), epreuve);
        } 
        else {
            categoryService.createCategory(category.getNameCategory(), epreuve);
        }
        return new RedirectView("/categories");
    }

    @GetMapping("/delete/{id}")
    public RedirectView deleteCategory(@PathVariable Long id) {
        categoryService.deleteCategory(id);
        return new RedirectView("/categories");
    }
}
