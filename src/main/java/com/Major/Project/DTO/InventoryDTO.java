package com.Major.Project.DTO;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InventoryDTO {

    private Long inventoryID;
    private String name;
    private String category;
    private int quantity;
    private String supplier;
    private String status;
    private Long staffId;
}
