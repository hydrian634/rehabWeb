package com.rehabweb.rehabweb.repository;

import com.rehabweb.rehabweb.entity.Therapist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TherapistRepository extends JpaRepository<Therapist, Long> {
    Optional<Therapist> findByEmail(String email);
    List<Therapist> findByFirstNameContaining(String firstName);
    List<Therapist> findByStatus(Therapist.TherapistStatus status);
    List<Therapist> findBySpecialization(String specialization);
}
