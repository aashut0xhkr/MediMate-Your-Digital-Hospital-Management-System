package com.Major.Project.Inventory.Service;

import com.Major.Project.Configuration.CustomException;
import com.Major.Project.Inventory.Entity.Inventory;
import com.Major.Project.Inventory.Repository.InventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InventoryService {
    @Autowired
    private InventoryRepository inventoryRepository;

    public List<Inventory> getAllItems(){
        return inventoryRepository.findAll();
    }
    public Inventory getItemsById(Long inventoryId){
        return inventoryRepository.findById(inventoryId).orElseThrow(()->new CustomException("Item Not Found"));
    }
    public List<Inventory> getItemByStatus(String status){
        return inventoryRepository.findByStatus(status);
    }
    public List<Inventory> getItemByCategory(String category){
        return inventoryRepository.findByCategory(category);}

    public Inventory createItem(Inventory inventory){
        return inventoryRepository.save(inventory);
    }
    public void deleteItem(Long inventoryId){
        inventoryRepository.deleteById(inventoryId);
    }
    public Inventory updateItem(Long inventoryId,Inventory inventory){
        Inventory allItemsById = getItemsById(inventoryId);
        if(allItemsById==null) return null;
        allItemsById.setName(inventory.getName());
        allItemsById.setName(inventory.getName());
        allItemsById.setCategory(inventory.getCategory());
        allItemsById.setQuantity(inventory.getQuantity());
        allItemsById.setSupplier(inventory.getSupplier());
        allItemsById.setStatus(inventory.getStatus());
        return inventoryRepository.save(allItemsById);
    }


}
