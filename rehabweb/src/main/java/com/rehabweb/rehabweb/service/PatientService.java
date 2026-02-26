package com.rehabweb.rehabweb.service;

import com.rehabweb.rehabweb.entity.Patient;
import com.rehabweb.rehabweb.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class PatientService {
    @Autowired
    private PatientRepository patientRepository;

    public Patient createPatient(Patient patient) {
        return patientRepository.save(patient);
    }

    public Optional<Patient> getPatientById(Long id) {
        return patientRepository.findById(id);
    }

    public List<Patient> getAllPatients() {
        return patientRepository.findAll();
    }

    public List<Patient> getActivePatients() {
        return patientRepository.findByStatus(Patient.PatientStatus.ACTIVE);
    }

    public Patient updatePatient(Patient patient) {
        return patientRepository.save(patient);
    }

    public void deactivatePatient(Long id) {
        Optional<Patient> patient = patientRepository.findById(id);
        if (patient.isPresent()) {
            patient.get().setStatus(Patient.PatientStatus.INACTIVE);
            patientRepository.save(patient.get());
        }
    }

    public void deletePatient(Long id) {
        patientRepository.deleteById(id);
    }

    public Optional<Patient> findByEmail(String email) {
        return patientRepository.findByEmail(email);
    }

    public List<Patient> searchByFirstName(String firstName) {
        return patientRepository.findByFirstNameContaining(firstName);
    }

    public List<Patient> getPatientsByTherapist(Long therapistId) {
        return patientRepository.findByAssignedTherapistId(therapistId);
    }
}
