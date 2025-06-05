package com.Major.Project.Staff.Entity;

import com.Major.Project.Inventory.Entity.Inventory;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Staff {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String role;
    private String department;
    private String phone;
    private String email;
    private String status;

    @OneToMany(mappedBy = "managedBy", cascade = CascadeType.ALL)
    @JsonIgnoreProperties("managedBy")
    private List<Inventory> inventory;


}
