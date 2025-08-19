package com.Major.Project.Entity;

import com.Major.Project.Entity.Staff;
import com.Major.Project.Entity.Patient;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LabTest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String testName;
    private LocalDate testDate;
    private String result;
    private String status;

    @ManyToOne
    @JoinColumn(name = "patient_id")
    @JsonIgnoreProperties("laboratoryTests")
    private Patient patient;

    @ManyToOne
    @JoinColumn(name = "staff_id")
    @JsonIgnoreProperties("laboratoryTests")
    private Staff labTechnician;
}
