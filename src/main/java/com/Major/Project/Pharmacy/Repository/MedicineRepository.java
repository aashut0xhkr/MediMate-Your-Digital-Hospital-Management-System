package com.Major.Project.Pharmacy.Repository;

import com.Major.Project.Pharmacy.Entity.Medicine;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MedicineRepository extends JpaRepository<Medicine, Long> {
    List<Medicine> findByStatus(String status);
    List<Medicine> findByNameContainingIgnoreCase(String name);
}
