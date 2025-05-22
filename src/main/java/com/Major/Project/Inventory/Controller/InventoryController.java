package com.Major.Project.Inventory.Controller;

import com.Major.Project.Inventory.Entity.Inventory;
import com.Major.Project.Inventory.Service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/HMS")
public class InventoryController {
    @Autowired
    private InventoryService inventoryService;

    @GetMapping("/Inventory")
    public List<Inventory> getItems(){
        return inventoryService.getAllItems();
    }
    @GetMapping("/Inventory/{id}")
    public Inventory getItemsById(@PathVariable Long inventoryId){
        return inventoryService.getAllItemsById(inventoryId);
    }
    @GetMapping("/status/{status}")
    public List<Inventory> getItemsByStatus(@PathVariable String status){
        return inventoryService.getItemByStatus(status);
    }
    @GetMapping("/category/{category}")
    public List<Inventory> getItemsByCategory(@PathVariable String category){
        return inventoryService.getItemByCategory(category);
    }
    @PostMapping("/Inventory")
    public Inventory createItems(@RequestBody Inventory inventory){
        return inventoryService.createItem(inventory);
    }
    @DeleteMapping("/Inventory/{id}")
    public ResponseEntity<Void> deleteItem(@PathVariable Long inventoryId){
        inventoryService.deleteItem(inventoryId);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/Inventory/{id}")
    public ResponseEntity<Inventory> updateItems(@PathVariable Long inventoryId,@RequestBody Inventory inventory){
       Inventory updated= inventoryService.updateItem(inventoryId,inventory);
       return updated != null ? ResponseEntity.ok(updated) : ResponseEntity.notFound().build();
    }
}
