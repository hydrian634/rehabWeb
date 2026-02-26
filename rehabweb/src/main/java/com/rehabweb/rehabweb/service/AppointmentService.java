package com.rehabweb.rehabweb.service;

import com.rehabweb.rehabweb.entity.Appointment;
import com.rehabweb.rehabweb.repository.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class AppointmentService {
    @Autowired
    private AppointmentRepository appointmentRepository;

    public Appointment createAppointment(Appointment appointment) {
        return appointmentRepository.save(appointment);
    }

    public Optional<Appointment> getAppointmentById(Long id) {
        return appointmentRepository.findById(id);
    }

    public List<Appointment> getAllAppointments() {
        return appointmentRepository.findAll();
    }

    public Appointment updateAppointment(Appointment appointment) {
        return appointmentRepository.save(appointment);
    }

    public void cancelAppointment(Long id) {
        Optional<Appointment> appointment = appointmentRepository.findById(id);
        if (appointment.isPresent()) {
            appointment.get().setStatus(Appointment.AppointmentStatus.CANCELLED);
            appointmentRepository.save(appointment.get());
        }
    }

    public void deleteAppointment(Long id) {
        appointmentRepository.deleteById(id);
    }

    public List<Appointment> getAppointmentsByPatient(Long patientId) {
        return appointmentRepository.findByPatientId(patientId);
    }

    public List<Appointment> getAppointmentsByTherapist(Long therapistId) {
        return appointmentRepository.findByTherapistId(therapistId);
    }

    public List<Appointment> getScheduledAppointments() {
        return appointmentRepository.findByStatus(Appointment.AppointmentStatus.SCHEDULED);
    }

    public List<Appointment> getAppointmentsByDateRange(LocalDateTime start, LocalDateTime end) {
        return appointmentRepository.findByAppointmentDateTimeBetween(start, end);
    }

    public List<Appointment> getScheduledAppointmentsByPatient(Long patientId) {
        return appointmentRepository.findByPatientIdAndStatus(patientId, Appointment.AppointmentStatus.SCHEDULED);
    }
}
