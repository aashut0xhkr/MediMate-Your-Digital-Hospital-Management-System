package com.Major.Project.Inventory.Entity;

import com.Major.Project.Staff.Entity.Staff;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.web.bind.annotation.GetMapping;

@Entity
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Inventory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long inventoryId;
    private String name;
    private String category;
    private int quantity;
    private String supplier;
    private String status;

//    @ManyToOne
//    @JoinColumn(name = "staff_id")
//    private Staff managedBy;
}
