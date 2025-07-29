package com.Major.Project.Patient.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class PatientDTO {
    private Long patientId;
    private String name;
    private Integer age;
    private String gender;
    private String contact;

    private List<Long> appointmentIds;
    private List<Long> billIds;
    private List<Long> labTestIds;
    private List<Long> pharmacyOrderIds;
}
