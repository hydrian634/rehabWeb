package com.rehabweb.rehabweb.controller;

import com.rehabweb.rehabweb.service.PatientService;
import com.rehabweb.rehabweb.service.TherapistService;
import com.rehabweb.rehabweb.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @Autowired
    private PatientService patientService;

    @Autowired
    private TherapistService therapistService;

    @Autowired
    private AppointmentService appointmentService;

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("totalPatients", patientService.getAllPatients().size());
        model.addAttribute("totalTherapists", therapistService.getAllTherapists().size());
        model.addAttribute("totalAppointments", appointmentService.getAllAppointments().size());
        model.addAttribute("scheduledAppointments", appointmentService.getScheduledAppointments().size());
        return "index";
    }

    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        model.addAttribute("activePatients", patientService.getActivePatients().size());
        model.addAttribute("activeTherapists", therapistService.getActiveTherapists().size());
        model.addAttribute("scheduledAppointments", appointmentService.getScheduledAppointments());
        return "dashboard";
    }
}
