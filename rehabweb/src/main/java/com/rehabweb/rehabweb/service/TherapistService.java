package com.rehabweb.rehabweb.service;

import com.rehabweb.rehabweb.entity.Therapist;
import com.rehabweb.rehabweb.repository.TherapistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class TherapistService {
    @Autowired
    private TherapistRepository therapistRepository;

    public Therapist createTherapist(Therapist therapist) {
        return therapistRepository.save(therapist);
    }

    public Optional<Therapist> getTherapistById(Long id) {
        return therapistRepository.findById(id);
    }

    public List<Therapist> getAllTherapists() {
        return therapistRepository.findAll();
    }

    public List<Therapist> getActiveTherapists() {
        return therapistRepository.findByStatus(Therapist.TherapistStatus.ACTIVE);
    }

    public Therapist updateTherapist(Therapist therapist) {
        return therapistRepository.save(therapist);
    }

    public void deactivateTherapist(Long id) {
        Optional<Therapist> therapist = therapistRepository.findById(id);
        if (therapist.isPresent()) {
            therapist.get().setStatus(Therapist.TherapistStatus.INACTIVE);
            therapistRepository.save(therapist.get());
        }
    }

    public void deleteTherapist(Long id) {
        therapistRepository.deleteById(id);
    }

    public Optional<Therapist> findByEmail(String email) {
        return therapistRepository.findByEmail(email);
    }

    public List<Therapist> searchByFirstName(String firstName) {
        return therapistRepository.findByFirstNameContaining(firstName);
    }

    public List<Therapist> findBySpecialization(String specialization) {
        return therapistRepository.findBySpecialization(specialization);
    }
}
