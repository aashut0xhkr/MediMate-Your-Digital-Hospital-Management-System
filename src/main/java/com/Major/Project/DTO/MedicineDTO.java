package com.Major.Project.DTO;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MedicineDTO {
    private Long id;

    @NotBlank(message = "Medicine name is required")
    private String name;

    @NotBlank(message = "Manufacturer is required")
    private String manufacturer;

    @Min(value = 0, message = "Quantity must be a non-negative number")
    private int quantity;

    @NotNull(message = "Expiry date is required")
    private LocalDate expiryDate;

    @NotBlank(message = "Medicine status is required")
    private String status;

    @NotNull(message = "Patient ID is required")
    private Long patientId;

    @NotNull(message = "Pharmacist ID is required")
    private Long pharmacistId;
}
