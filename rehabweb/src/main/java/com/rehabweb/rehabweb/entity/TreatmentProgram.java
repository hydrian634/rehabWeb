package com.rehabweb.rehabweb.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "treatment_programs")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TreatmentProgram {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "patient_id", nullable = false)
    private Patient patient;

    @Column(nullable = false)
    private String programName;

    @Column(length = 1000)
    private String description;

    @Column(nullable = false)
    private LocalDate startDate;

    @Column(nullable = false)
    private LocalDate endDate;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private ProgramType programType;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private ProgramStatus status = ProgramStatus.ACTIVE;

    @Column
    private Integer sessionsPerWeek;

    @Column(length = 500)
    private String goals;

    @Column(length = 500)
    private String notes;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(nullable = false)
    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }

    public enum ProgramType {
        PHYSIOTHERAPY, OCCUPATIONAL_THERAPY, SPEECH_THERAPY, NEUROTHERAPY, CARDIAC_REHAB
    }

    public enum ProgramStatus {
        ACTIVE, SUSPENDED, COMPLETED, CANCELLED
    }
}
