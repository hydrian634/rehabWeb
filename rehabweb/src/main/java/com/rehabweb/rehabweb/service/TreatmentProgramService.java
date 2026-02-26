package com.rehabweb.rehabweb.service;

import com.rehabweb.rehabweb.entity.TreatmentProgram;
import com.rehabweb.rehabweb.repository.TreatmentProgramRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class TreatmentProgramService {
    @Autowired
    private TreatmentProgramRepository treatmentProgramRepository;

    public TreatmentProgram createProgram(TreatmentProgram program) {
        return treatmentProgramRepository.save(program);
    }

    public Optional<TreatmentProgram> getProgramById(Long id) {
        return treatmentProgramRepository.findById(id);
    }

    public List<TreatmentProgram> getAllPrograms() {
        return treatmentProgramRepository.findAll();
    }

    public TreatmentProgram updateProgram(TreatmentProgram program) {
        return treatmentProgramRepository.save(program);
    }

    public void completeProgram(Long id) {
        Optional<TreatmentProgram> program = treatmentProgramRepository.findById(id);
        if (program.isPresent()) {
            program.get().setStatus(TreatmentProgram.ProgramStatus.COMPLETED);
            treatmentProgramRepository.save(program.get());
        }
    }

    public void deleteProgram(Long id) {
        treatmentProgramRepository.deleteById(id);
    }

    public List<TreatmentProgram> getProgramsByPatient(Long patientId) {
        return treatmentProgramRepository.findByPatientId(patientId);
    }

    public List<TreatmentProgram> getActiveProgramsByPatient(Long patientId) {
        return treatmentProgramRepository.findByPatientIdAndStatus(patientId, TreatmentProgram.ProgramStatus.ACTIVE);
    }

    public List<TreatmentProgram> getActivePrograms() {
        return treatmentProgramRepository.findByStatus(TreatmentProgram.ProgramStatus.ACTIVE);
    }

    public List<TreatmentProgram> getProgramsByType(TreatmentProgram.ProgramType programType) {
        return treatmentProgramRepository.findByProgramType(programType);
    }
}
