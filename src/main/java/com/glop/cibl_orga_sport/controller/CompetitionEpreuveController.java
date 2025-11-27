package com.glop.cibl_orga_sport.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.glop.cibl_orga_sport.service.CompetitionService;
import com.glop.cibl_orga_sport.service.EpreuveService;
import com.glop.cibl_orga_sport.service.CompetitionEpreuveService;
import com.glop.cibl_orga_sport.data.Competition;

import java.util.Optional;

@Controller
@RequestMapping("/competitions/{competitionId}/epreuves")
public class CompetitionEpreuveController {
    
    @Autowired
    private CompetitionService competitionService;
    
    @Autowired
    private EpreuveService epreuveService;
    
    @Autowired
    private CompetitionEpreuveService competitionEpreuveService;

    @GetMapping
    public ModelAndView manageEpreuves(@PathVariable Long competitionId) {
        Optional<Competition> competition = competitionService.getCompetition(competitionId);
        
        if (competition.isPresent()) {
            ModelAndView model = new ModelAndView("competition/manage-epreuves");
            model.addObject("competition", competition.get());
            model.addObject("allEpreuves", epreuveService.getAllEpreuves());
            return model;
        }
        
        return new ModelAndView("redirect:/competitions");
    }

    @PostMapping("/add")
    public RedirectView addEpreuve(@PathVariable Long competitionId, @RequestParam Long epreuveId) {
        competitionEpreuveService.linkEpreuveToCompetition(competitionId, epreuveId);
        return new RedirectView("/competitions/" + competitionId + "/epreuves");
    }

    @GetMapping("/remove/{epreuveId}")
    public RedirectView removeEpreuve(@PathVariable Long competitionId, @PathVariable Long epreuveId) throws IllegalAccessException {
        competitionEpreuveService.unlinkEpreuveFromCompetition(competitionId, epreuveId);
        return new RedirectView("/competitions/" + competitionId + "/epreuves");
    }
}
