package com.rehabweb.rehabweb.repository;

import com.rehabweb.rehabweb.entity.TreatmentProgram;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TreatmentProgramRepository extends JpaRepository<TreatmentProgram, Long> {
    List<TreatmentProgram> findByPatientId(Long patientId);
    List<TreatmentProgram> findByStatus(TreatmentProgram.ProgramStatus status);
    List<TreatmentProgram> findByProgramType(TreatmentProgram.ProgramType programType);
    List<TreatmentProgram> findByPatientIdAndStatus(Long patientId, TreatmentProgram.ProgramStatus status);
}
