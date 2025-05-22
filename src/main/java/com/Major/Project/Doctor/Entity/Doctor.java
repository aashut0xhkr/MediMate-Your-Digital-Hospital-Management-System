package com.Major.Project.Doctor.Entity;

import com.Major.Project.Appointment.Entity.Appointment;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Doctor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long DocId;
    private String name;
    private String specialisation;
    private String contact;
    private String email;

    @OneToMany(mappedBy = "doctor", cascade = CascadeType.ALL)
    private List<Appointment> appointments;

}
