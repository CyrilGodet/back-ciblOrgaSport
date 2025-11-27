package com.glop.cibl_orga_sport.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.glop.cibl_orga_sport.data.Epreuve;
import com.glop.cibl_orga_sport.service.EpreuveService;

import java.util.Optional;

@Controller
@RequestMapping("/epreuves")
public class EpreuveController {
    
    @Autowired
    private EpreuveService epreuveService;

    @GetMapping
    public ModelAndView listEpreuves() {
        ModelAndView model = new ModelAndView("epreuve/list");
        model.addObject("epreuves", epreuveService.getAllEpreuves());
        return model;
    }

    @GetMapping("/new")
    public ModelAndView showForm() {
        ModelAndView model = new ModelAndView("epreuve/form");
        model.addObject("epreuve", new Epreuve());
        model.addObject("isEdit", false);
        return model;
    }

    @GetMapping("/edit/{id}")
    public ModelAndView showEditForm(@PathVariable Long id) {
        Optional<Epreuve> epreuve = epreuveService.getEpreuve(id);
        if (epreuve.isPresent()) {
            ModelAndView model = new ModelAndView("epreuve/form");
            model.addObject("epreuve", epreuve.get());
            model.addObject("isEdit", true);
            return model;
        }
        return new ModelAndView("redirect:/epreuves");
    }

    @PostMapping("/save")
    public RedirectView saveEpreuve(@ModelAttribute Epreuve epreuve) {
        if (epreuve.getIdEpreuve() != null) {
            epreuveService.updateEpreuve(epreuve.getIdEpreuve(), epreuve.getNomEpreuve());
        } 
        else {
            epreuveService.createEpreuve(epreuve.getNomEpreuve());
        }
        return new RedirectView("/epreuves");
    }

    @GetMapping("/delete/{id}")
    public RedirectView deleteEpreuve(@PathVariable Long id) {
        epreuveService.deleteEpreuve(id);
        return new RedirectView("/epreuves");
    }
}
