package com.Major.Project.Pharmacy.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MedicineDTO {
    private Long id;

    private String name;
    private String manufacturer;
    private int quantity;
    private LocalDate expiryDate;
    private String status;


    private Long patientId;
    private Long pharmacistId;
}
