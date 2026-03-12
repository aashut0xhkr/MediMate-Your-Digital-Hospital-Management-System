package com.Major.Project.DTO;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class PatientDTO {
    private Long patientId;

    @NotBlank(message = "Patient name is required")
    private String name;

    @NotNull(message = "Age is required")
    @Min(value = 0, message = "Age must be a non-negative number")
    private Integer age;

    @NotBlank(message = "Gender is required")
    private String gender;

    @NotBlank(message = "Contact number is required")
    private String contact;

    private List<Long> appointmentIds;
    private List<Long> billIds;
    private List<Long> labTestIds;
    private List<Long> pharmacyOrderIds;
}
