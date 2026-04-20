package com.Major.Project.DTO;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class BillDTO {
    private Long billID;

    @NotNull(message = "Billing amount is required")
    @Min(value = 0, message = "Billing amount must be a non-negative number")
    private Double amount;

    @NotBlank(message = "Bill description is required")
    private String description;

    @NotNull(message = "Billing date is required")
    private LocalDateTime billingDate;

    @NotBlank(message = "Payment status is required")
    private String paymentStatus;

    @NotNull(message = "Patient ID is required")
    private Long patientID;

    @NotNull(message = "Staff ID is required")
    private Long staffId;
}
