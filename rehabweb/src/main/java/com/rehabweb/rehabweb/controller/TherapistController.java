package com.rehabweb.rehabweb.controller;

import com.rehabweb.rehabweb.entity.Therapist;
import com.rehabweb.rehabweb.service.TherapistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/therapists")
public class TherapistController {
    @Autowired
    private TherapistService therapistService;

    @GetMapping
    public String listTherapists(Model model) {
        model.addAttribute("therapists", therapistService.getAllTherapists());
        return "therapists/list";
    }

    @GetMapping("/new")
    public String newTherapistForm(Model model) {
        model.addAttribute("therapist", new Therapist());
        return "therapists/form";
    }

    @GetMapping("/{id}")
    public String viewTherapist(@PathVariable Long id, Model model) {
        Optional<Therapist> therapist = therapistService.getTherapistById(id);
        if (therapist.isPresent()) {
            model.addAttribute("therapist", therapist.get());
            return "therapists/view";
        }
        return "redirect:/therapists";
    }

    @GetMapping("/{id}/edit")
    public String editTherapistForm(@PathVariable Long id, Model model) {
        Optional<Therapist> therapist = therapistService.getTherapistById(id);
        if (therapist.isPresent()) {
            model.addAttribute("therapist", therapist.get());
            return "therapists/form";
        }
        return "redirect:/therapists";
    }

    @PostMapping
    public String saveTherapist(@ModelAttribute Therapist therapist) {
        therapistService.createTherapist(therapist);
        return "redirect:/therapists";
    }

    @PostMapping("/{id}/update")
    public String updateTherapist(@PathVariable Long id, @ModelAttribute Therapist therapist) {
        therapist.setId(id);
        therapistService.updateTherapist(therapist);
        return "redirect:/therapists/" + id;
    }

    @GetMapping("/{id}/delete")
    public String deleteTherapist(@PathVariable Long id) {
        therapistService.deleteTherapist(id);
        return "redirect:/therapists";
    }

    @GetMapping("/search")
    public String searchTherapists(@RequestParam String keyword, Model model) {
        model.addAttribute("therapists", therapistService.searchByFirstName(keyword));
        return "therapists/list";
    }
}
