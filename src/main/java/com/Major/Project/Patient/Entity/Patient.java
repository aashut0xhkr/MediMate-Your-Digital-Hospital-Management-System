package com.Major.Project.Patient.Entity;

import com.Major.Project.Appointment.Entity.Appointment;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name="Patient")
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long patientId;
    private String name;
    private Integer age;
    private String gender;
    private String contact;

    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Appointment> appointments;

//    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL)
//    private List<Bill> billings;

//    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL)
//    private List<LabTest> laboratoryTests;
//
//    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL)
//    private List<Medicine> pharmacyOrders;

}
