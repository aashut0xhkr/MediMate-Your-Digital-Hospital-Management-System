package com.Major.Project.Inventory.Repository;

import com.Major.Project.Inventory.Entity.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InventoryRepository extends JpaRepository<Inventory,Long> {
    List<Inventory> findByCategory(String category);
    List<Inventory> findByStatus(String status);
}
