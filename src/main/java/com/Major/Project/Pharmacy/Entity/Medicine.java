package com.Major.Project.Pharmacy.Entity;


import com.Major.Project.Patient.Entity.Patient;
import com.Major.Project.Staff.Entity.Staff;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Medicine {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String manufacturer;
    private int quantity;
    private LocalDate expiryDate;
    private String status;

//    @ManyToOne
//    @JoinColumn(name = "patient_id")
//    private Patient patient;
//
//    @ManyToOne
//    @JoinColumn(name = "staff_id")
//    private Staff pharmacist;

}

