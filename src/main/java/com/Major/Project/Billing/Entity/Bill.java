package com.Major.Project.Billing.Entity;

import com.Major.Project.Patient.Entity.Patient;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Bill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long billID;
    private Double amount;
    private String description;
    private LocalDateTime billingDate;
    private String paymentStatus;

    @ManyToOne
    @JoinColumn(name = "patient_id")
    @JsonIgnoreProperties("patient")
    private Patient patientId;
//    @ManyToOne
//    @JoinColumn(name = "staff_id")
//    private Staff handledBy;
}
