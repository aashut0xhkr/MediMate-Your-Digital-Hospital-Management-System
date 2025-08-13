package com.Major.Project.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Major.Project.Entity.Medicine;

import java.util.List;

public interface MedicineRepository extends JpaRepository<Medicine, Long> {
    List<Medicine> findByStatus(String status);
    List<Medicine> findByNameContainingIgnoreCase(String name);
}
