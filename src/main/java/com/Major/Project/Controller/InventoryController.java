package com.Major.Project.Controller;

import com.Major.Project.DTO.InventoryDTO;
import com.Major.Project.Entity.Inventory;
import com.Major.Project.Service.InventoryService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/HMS/Inventory")
public class InventoryController {
    @Autowired
    private InventoryService inventoryService;

    @GetMapping
    public List<InventoryDTO> getItems(){
        return inventoryService.getAllItems();
    }
    @GetMapping("/{inventoryId}")
    public InventoryDTO getItemsById(@PathVariable Long inventoryId){
        return inventoryService.getItemsById(inventoryId);
    }
    @GetMapping("/status/{status}")
    public List<InventoryDTO> getItemsByStatus(@PathVariable String status){
        return inventoryService.getItemByStatus(status);
    }
    @GetMapping("/category/{category}")
    public List<InventoryDTO> getItemsByCategory(@PathVariable String category){
        return inventoryService.getItemByCategory(category);
    }
    @PostMapping
    public InventoryDTO createItems(@RequestBody Inventory inventory){
        return inventoryService.createItem(inventory);
    }
    @DeleteMapping("/{inventoryId}")
    public ResponseEntity<Void> deleteItem(@PathVariable Long inventoryId){
        inventoryService.deleteItem(inventoryId);
        return ResponseEntity.noContent().build();
    }
    @PutMapping("/{inventoryId}")
    public ResponseEntity<InventoryDTO> updateItems(@PathVariable Long inventoryId,@RequestBody Inventory inventory){
       InventoryDTO updated= inventoryService.updateItem(inventoryId,inventory);
       return updated != null ? ResponseEntity.ok(updated) : ResponseEntity.notFound().build();
    }
}
