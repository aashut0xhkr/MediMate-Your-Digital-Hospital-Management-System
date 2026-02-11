package com.Major.Project.Controller;

import com.Major.Project.DTO.MedicineDTO;
import com.Major.Project.Entity.Medicine;
import com.Major.Project.Service.MedicineService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/HMS/Medicine")
public class MedicineController {
    @Autowired
    private MedicineService medicineService;

    @PreAuthorize("hasRole('DOCTOR')")
    @GetMapping
    public List<MedicineDTO> getAll() {
        return medicineService.getAll();
    }

    @PreAuthorize("hasRole('DOCTOR')")
    @GetMapping("/{id}")
    public ResponseEntity<MedicineDTO> getById(@PathVariable Long id) {
        MedicineDTO medicine = medicineService.getById(id);
        return medicine != null ? ResponseEntity.ok(medicine) : ResponseEntity.notFound().build();
    }

    @PreAuthorize("hasRole('DOCTOR')")
    @GetMapping("/status/{status}")
    public List<MedicineDTO> getByStatus(@PathVariable String status) {
        return medicineService.getByStatus(status);
    }

    @PreAuthorize("hasRole('DOCTOR')")
    @GetMapping("/search/{name}")
    public List<MedicineDTO> searchByName(@PathVariable String name) {
        return medicineService.searchByName(name);
    }

    @PreAuthorize("hasRole('DOCTOR')")
    @PostMapping
    public ResponseEntity<MedicineDTO> create(@RequestBody Medicine medicine) {
        return ResponseEntity.ok(medicineService.create(medicine));
    }

    @PreAuthorize("hasRole('DOCTOR')")
    @PutMapping("/{id}")
    public ResponseEntity<MedicineDTO> update(@PathVariable Long id, @RequestBody Medicine medicine) {
        MedicineDTO updated = medicineService.update(id, medicine);
        return updated != null ? ResponseEntity.ok(updated) : ResponseEntity.notFound().build();
    }

    @PreAuthorize("hasRole('DOCTOR')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        medicineService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
