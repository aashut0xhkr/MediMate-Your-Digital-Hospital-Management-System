package com.Major.Project.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class AppointmentDTO {
    private Long appointmentID;
    private LocalDateTime appointmentTime;
    private String status;
    private String patientName;
    private String doctorName;

}
