package com.Major.Project.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class StaffDTO {
    private Long id;
    private String name;
    private String role;
    private String department;
    private String phone;
    private String email;
    private String status;


    private List<Long> inventoryIds;
}
