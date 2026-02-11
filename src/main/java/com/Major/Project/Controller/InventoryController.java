package com.Major.Project.Controller;

import com.Major.Project.DTO.InventoryDTO;
import com.Major.Project.Entity.Inventory;
import com.Major.Project.Service.InventoryService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/HMS/Inventory")
public class InventoryController {
    @Autowired
    private InventoryService inventoryService;

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public List<InventoryDTO> getItems(){
        return inventoryService.getAllItems();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/{inventoryId}")
    public InventoryDTO getItemsById(@PathVariable Long inventoryId){
        return inventoryService.getItemsById(inventoryId);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/status/{status}")
    public List<InventoryDTO> getItemsByStatus(@PathVariable String status){
        return inventoryService.getItemByStatus(status);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/category/{category}")
    public List<InventoryDTO> getItemsByCategory(@PathVariable String category){
        return inventoryService.getItemByCategory(category);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public InventoryDTO createItems(@RequestBody Inventory inventory){
        return inventoryService.createItem(inventory);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{inventoryId}")
    public ResponseEntity<Void> deleteItem(@PathVariable Long inventoryId){
        inventoryService.deleteItem(inventoryId);
        return ResponseEntity.noContent().build();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{inventoryId}")
    public ResponseEntity<InventoryDTO> updateItems(@PathVariable Long inventoryId,@RequestBody Inventory inventory){
       InventoryDTO updated= inventoryService.updateItem(inventoryId,inventory);
       return updated != null ? ResponseEntity.ok(updated) : ResponseEntity.notFound().build();
    }
}
