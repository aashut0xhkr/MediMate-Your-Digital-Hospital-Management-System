package com.Major.Project.Inventory.Controller;

import com.Major.Project.Inventory.Entity.Inventory;
import com.Major.Project.Inventory.Service.InventoryService;
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
    public List<Inventory> getItems(){
        return inventoryService.getAllItems();
    }
    @GetMapping("/{inventoryId}")
    public Inventory getItemsById(@PathVariable Long inventoryId){
        return inventoryService.getItemsById(inventoryId);
    }
    @GetMapping("/status/{status}")
    public List<Inventory> getItemsByStatus(@PathVariable String status){
        return inventoryService.getItemByStatus(status);
    }
    @GetMapping("/category/{category}")
    public List<Inventory> getItemsByCategory(@PathVariable String category){
        return inventoryService.getItemByCategory(category);
    }
    @PostMapping
    public Inventory createItems(@RequestBody Inventory inventory){
        return inventoryService.createItem(inventory);
    }
    @DeleteMapping("/{inventoryId}")
    public ResponseEntity<Void> deleteItem(@PathVariable Long inventoryId){
        inventoryService.deleteItem(inventoryId);
        return ResponseEntity.noContent().build();
    }
    @PutMapping("/{inventoryId}")
    public ResponseEntity<Inventory> updateItems(@PathVariable Long inventoryId,@RequestBody Inventory inventory){
       Inventory updated= inventoryService.updateItem(inventoryId,inventory);
       return updated != null ? ResponseEntity.ok(updated) : ResponseEntity.notFound().build();
    }
}
