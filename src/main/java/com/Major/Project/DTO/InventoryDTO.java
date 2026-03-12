package com.Major.Project.DTO;


import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InventoryDTO {

    private Long inventoryID;

    @NotBlank(message = "Item name is required")
    private String name;

    @NotBlank(message = "Category is required")
    private String category;

    @Min(value = 0, message = "Quantity must be a non-negative number")
    private int quantity;

    @NotBlank(message = "Supplier is required")
    private String supplier;

    @NotBlank(message = "Inventory status is required")
    private String status;

    @NotNull(message = "Staff ID is required")
    private Long staffId;
}
