package com.Major.Project.DTO;

import com.Major.Project.Entity.Patient;
import com.Major.Project.Entity.Staff;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LabTestDTO {
    private Long id;

    @NotBlank(message = "Test name is required")
    private String testName;

    @NotNull(message = "Test date is required")
    private LocalDate testDate;

    private String result;

    @NotBlank(message = "Test status is required")
    private String status;

    @NotNull(message = "Patient ID is required")
    private Long patientId;

    @NotNull(message = "Lab technician ID is required")
    private Long labTechnicianId;
}
