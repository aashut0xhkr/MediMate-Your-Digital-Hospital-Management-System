package com.Major.Project.Laboratory.DTO;

import com.Major.Project.Patient.Entity.Patient;
import com.Major.Project.Staff.Entity.Staff;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LabTestDTO {
    private Long id;
    private String testName;
    private LocalDate testDate;
    private String result;
    private String status;
    private Long patientId;
    private Long labTechnicianId;
}
