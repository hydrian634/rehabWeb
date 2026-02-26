package com.rehabweb.rehabweb.repository;

import com.rehabweb.rehabweb.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {
    Optional<Patient> findByEmail(String email);
    List<Patient> findByFirstNameContaining(String firstName);
    List<Patient> findByStatus(Patient.PatientStatus status);
    List<Patient> findByAssignedTherapistId(Long therapistId);
}
