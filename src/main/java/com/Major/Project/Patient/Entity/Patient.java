package com.Major.Project.Patient.Entity;

import com.Major.Project.Appointment.Entity.Appointment;
import com.Major.Project.Billing.Entity.Bill;
import com.Major.Project.Laboratory.Entity.LabTest;
import com.Major.Project.Pharmacy.Entity.Medicine;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name="Patient")
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties("appointments")
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long patientId;
    private String name;
    private Integer age;
    private String gender;
    private String contact;

    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL)
    private List<Appointment> appointments;

//    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL)
//    private List<Bill> billings;

//    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL)
//    private List<LabTest> laboratoryTests;
//
//    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL)
//    private List<Medicine> pharmacyOrders;

}
