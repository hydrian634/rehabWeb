package com.rehabweb.rehabweb.controller;

import com.rehabweb.rehabweb.entity.TreatmentProgram;
import com.rehabweb.rehabweb.service.TreatmentProgramService;
import com.rehabweb.rehabweb.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/programs")
public class TreatmentProgramController {
    @Autowired
    private TreatmentProgramService treatmentProgramService;

    @Autowired
    private PatientService patientService;

    @GetMapping
    public String listPrograms(Model model) {
        model.addAttribute("programs", treatmentProgramService.getAllPrograms());
        return "programs/list";
    }

    @GetMapping("/new")
    public String newProgramForm(Model model) {
        model.addAttribute("program", new TreatmentProgram());
        model.addAttribute("patients", patientService.getAllPatients());
        return "programs/form";
    }

    @GetMapping("/{id}")
    public String viewProgram(@PathVariable Long id, Model model) {
        Optional<TreatmentProgram> program = treatmentProgramService.getProgramById(id);
        if (program.isPresent()) {
            model.addAttribute("program", program.get());
            return "programs/view";
        }
        return "redirect:/programs";
    }

    @GetMapping("/{id}/edit")
    public String editProgramForm(@PathVariable Long id, Model model) {
        Optional<TreatmentProgram> program = treatmentProgramService.getProgramById(id);
        if (program.isPresent()) {
            model.addAttribute("program", program.get());
            model.addAttribute("patients", patientService.getAllPatients());
            return "programs/form";
        }
        return "redirect:/programs";
    }

    @PostMapping
    public String saveProgram(@ModelAttribute TreatmentProgram program) {
        treatmentProgramService.createProgram(program);
        return "redirect:/programs";
    }

    @PostMapping("/{id}/update")
    public String updateProgram(@PathVariable Long id, @ModelAttribute TreatmentProgram program) {
        program.setId(id);
        treatmentProgramService.updateProgram(program);
        return "redirect:/programs/" + id;
    }

    @GetMapping("/{id}/delete")
    public String deleteProgram(@PathVariable Long id) {
        treatmentProgramService.deleteProgram(id);
        return "redirect:/programs";
    }

    @GetMapping("/{id}/complete")
    public String completeProgram(@PathVariable Long id) {
        treatmentProgramService.completeProgram(id);
        return "redirect:/programs/" + id;
    }
}
