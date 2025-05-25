package com.Major.Project.Laboratory.Entity;

import com.Major.Project.Patient.Entity.Patient;
import com.Major.Project.Staff.Entity.Staff;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class LabTest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long patientId;
    private String testName;
    private LocalDate testDate;
    private String result;
    private String status;

//    @ManyToOne
//    @JoinColumn(name = "patient_id")
//    private Patient patient;
//
//    @ManyToOne
//    @JoinColumn(name = "staff_id")
//    private Staff labTechnician;
}
