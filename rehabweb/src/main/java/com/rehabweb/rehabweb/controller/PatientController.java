package com.rehabweb.rehabweb.controller;

import com.rehabweb.rehabweb.entity.Patient;
import com.rehabweb.rehabweb.service.PatientService;
import com.rehabweb.rehabweb.service.TherapistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/patients")
public class PatientController {
    @Autowired
    private PatientService patientService;

    @Autowired
    private TherapistService therapistService;

    @GetMapping
    public String listPatients(Model model) {
        model.addAttribute("patients", patientService.getAllPatients());
        return "patients/list";
    }

    @GetMapping("/new")
    public String newPatientForm(Model model) {
        model.addAttribute("patient", new Patient());
        model.addAttribute("therapists", therapistService.getAllTherapists());
        return "patients/form";
    }

    @GetMapping("/{id}")
    public String viewPatient(@PathVariable Long id, Model model) {
        Optional<Patient> patient = patientService.getPatientById(id);
        if (patient.isPresent()) {
            model.addAttribute("patient", patient.get());
            return "patients/view";
        }
        return "redirect:/patients";
    }

    @GetMapping("/{id}/edit")
    public String editPatientForm(@PathVariable Long id, Model model) {
        Optional<Patient> patient = patientService.getPatientById(id);
        if (patient.isPresent()) {
            model.addAttribute("patient", patient.get());
            model.addAttribute("therapists", therapistService.getAllTherapists());
            return "patients/form";
        }
        return "redirect:/patients";
    }

    @PostMapping
    public String savePatient(@ModelAttribute Patient patient) {
        patientService.createPatient(patient);
        return "redirect:/patients";
    }

    @PostMapping("/{id}/update")
    public String updatePatient(@PathVariable Long id, @ModelAttribute Patient patient) {
        patient.setId(id);
        patientService.updatePatient(patient);
        return "redirect:/patients/" + id;
    }

    @GetMapping("/{id}/delete")
    public String deletePatient(@PathVariable Long id) {
        patientService.deletePatient(id);
        return "redirect:/patients";
    }

    @GetMapping("/search")
    public String searchPatients(@RequestParam String keyword, Model model) {
        model.addAttribute("patients", patientService.searchByFirstName(keyword));
        return "patients/list";
    }
}
