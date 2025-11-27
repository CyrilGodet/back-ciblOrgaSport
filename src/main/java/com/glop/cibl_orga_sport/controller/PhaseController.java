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

import com.glop.cibl_orga_sport.data.Phase;
import com.glop.cibl_orga_sport.data.Epreuve;
import com.glop.cibl_orga_sport.service.PhaseService;
import com.glop.cibl_orga_sport.service.EpreuveService;

import java.util.Optional;

@Controller
@RequestMapping("/phases")
public class PhaseController {
    
    @Autowired
    private PhaseService phaseService;

    @Autowired
    private EpreuveService epreuveService;

    @GetMapping
    public ModelAndView listPhases() {
        ModelAndView model = new ModelAndView("phase/list");
        model.addObject("phases", phaseService.getAllPhases());
        return model;
    }

    @GetMapping("/new")
    public ModelAndView showForm() {
        ModelAndView model = new ModelAndView("phase/form");
        model.addObject("phase", new Phase());
        model.addObject("epreuves", epreuveService.getAllEpreuves());
        model.addObject("isEdit", false);
        return model;
    }

    @GetMapping("/edit/{id}")
    public ModelAndView showEditForm(@PathVariable Long id) {
        Optional<Phase> phase = phaseService.getPhase(id);
        if (phase.isPresent()) {
            ModelAndView model = new ModelAndView("phase/form");
            model.addObject("phase", phase.get());
            model.addObject("epreuves", epreuveService.getAllEpreuves());
            model.addObject("isEdit", true);
            return model;
        }
        return new ModelAndView("redirect:/phases");
    }

    @PostMapping("/save")
    public RedirectView savePhase(@ModelAttribute Phase phase, @RequestParam(required = false) Long epreuveId) {
        Epreuve epreuve = null;
        if (epreuveId != null) {
            Optional<Epreuve> epreuveOpt = epreuveService.getEpreuve(epreuveId);
            if (epreuveOpt.isPresent()) {
                epreuve = epreuveOpt.get();
            }
        }

        if (phase.getIdPhase() != null) {
            phaseService.updatePhase(phase.getIdPhase(), phase.getNomPhase(), epreuve);
        } 
        else {
            phaseService.createPhase(phase.getNomPhase(), epreuve);
        }
        return new RedirectView("/phases");
    }

    @GetMapping("/delete/{id}")
    public RedirectView deletePhase(@PathVariable Long id) {
        phaseService.deletePhase(id);
        return new RedirectView("/phases");
    }
}
