package com.Major.Project.Inventory.Service;

import com.Major.Project.Configuration.CustomException;
import com.Major.Project.Inventory.DTO.InventoryDTO;
import com.Major.Project.Inventory.Entity.Inventory;
import com.Major.Project.Inventory.Repository.InventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class InventoryService {
    @Autowired
    private InventoryRepository inventoryRepository;

    public List<InventoryDTO> getAllItems(){
        return inventoryRepository.findAll()
                .stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public InventoryDTO getItemsById(Long inventoryId){
        Inventory item = inventoryRepository.findById(inventoryId)
                .orElseThrow(() -> new CustomException("Item Not Found"));
        return convertToDto(item);
    }
    public List<InventoryDTO> getItemByStatus(String status){
        return inventoryRepository.findByStatus(status)
                .stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }
    public List<InventoryDTO> getItemByCategory(String category){
        return inventoryRepository.findByCategory(category)
                .stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public InventoryDTO createItem(Inventory inventory){
        Inventory saved = inventoryRepository.save(inventory);
        return convertToDto(saved);
    }
    public void deleteItem(Long inventoryId){
        inventoryRepository.deleteById(inventoryId);
    }
    public InventoryDTO updateItem(Long inventoryId, Inventory incoming){
        // Fetch entity (not DTO) from DB
        Inventory existing = inventoryRepository.findById(inventoryId)
                .orElseThrow(() -> new CustomException("Item Not Found"));

        // Copy incoming fields onto the entity
        existing.setName(incoming.getName());
        existing.setCategory(incoming.getCategory());
        existing.setQuantity(incoming.getQuantity());
        existing.setSupplier(incoming.getSupplier());
        existing.setStatus(incoming.getStatus());

        // Persist entity, then convert to DTO for the response
        Inventory updated = inventoryRepository.save(existing);
        return convertToDto(updated);
    }

    private InventoryDTO convertToDto(Inventory inventory){
        InventoryDTO inventoryDTO=new InventoryDTO();
        inventoryDTO.setInventoryID(inventory.getInventoryId());
        inventoryDTO.setName(inventory.getName());
        inventoryDTO.setCategory(inventory.getCategory());
        inventoryDTO.setQuantity(inventory.getQuantity());
        inventoryDTO.setSupplier(inventory.getSupplier());
        inventoryDTO.setStatus(inventory.getStatus());
        inventoryDTO.setStaffId(inventory.getManagedBy().getId());
        return inventoryDTO;
    }


}
