package com.Major.Project.Billing.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class BillDTO {
    private Long billID;
    private Double amount;
    private String description;
    private LocalDateTime billingDate;
    private String paymentStatus;
    private Long patientID;
    private Long staffId;


}
