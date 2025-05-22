package com.Major.Project.Patient.Entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="Patient")
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long patientId;
    private String name;
    private Integer age;
    private String Gender;
    private String Contact;

}
