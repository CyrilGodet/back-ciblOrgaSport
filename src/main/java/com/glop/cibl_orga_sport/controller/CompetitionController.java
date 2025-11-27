package com.glop.cibl_orga_sport.controller;

import java.time.LocalDate;
import java.sql.Date;
import java.util.Optional;

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

import com.glop.cibl_orga_sport.data.Competition;
import com.glop.cibl_orga_sport.service.CompetitionService;

@Controller
@RequestMapping("/competitions")
public class CompetitionController {
    
    @Autowired
    private CompetitionService service;

    @GetMapping
    public ModelAndView listCompetitions() {
        ModelAndView model = new ModelAndView("competition/list");
        model.addObject("competitions", service.getAllCompetitions());
        return model;
    }

    @GetMapping("/new")
    public ModelAndView showForm() {
        ModelAndView model = new ModelAndView("competition/form");
        model.addObject("competition", new Competition());
        return model;
    }

    @GetMapping("/edit/{id}")
    public ModelAndView showEditForm(@PathVariable Long id) {
        Optional<Competition> competition = service.getCompetition(id);
        if (competition.isPresent()) {
            ModelAndView model = new ModelAndView("competition/form");
            model.addObject("competition", competition.get());
            return model;
        }
        return new ModelAndView("redirect:/competitions");
    }

    @PostMapping("/save")
    public RedirectView saveCompetition(@ModelAttribute Competition competition, @RequestParam String dateDebutStr, @RequestParam String dateFinStr) {

        Date dateDebut = Date.valueOf(LocalDate.parse(dateDebutStr));
        Date dateFin = Date.valueOf(LocalDate.parse(dateFinStr));

        if (competition.getIdCompetition() != null) {
            service.updateCompetition(competition.getIdCompetition(), competition.getNameCompetition(), dateDebut, dateFin);
        } 
        else {
            service.createCompetition(competition.getNameCompetition(), dateDebut, dateFin);
        }
        return new RedirectView("/competitions");
    }

    @GetMapping("/delete/{id}")
    public RedirectView deleteCompetition(@PathVariable Long id) {
        service.deleteCompetition(id);
        return new RedirectView("/competitions");
    }
}
