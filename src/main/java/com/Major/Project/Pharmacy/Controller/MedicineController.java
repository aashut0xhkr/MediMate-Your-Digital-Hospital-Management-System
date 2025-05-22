package com.Major.Project.Pharmacy.Controller;

import com.Major.Project.Pharmacy.Entity.Medicine;
import com.Major.Project.Pharmacy.Service.MedicineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/HMS/Medicine")
public class MedicineController {
    @Autowired
    private MedicineService medicineService;

    @GetMapping
    public List<Medicine> getAll() {
        return medicineService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Medicine> getById(@PathVariable Long id) {
        Medicine medicine = medicineService.getById(id);
        return medicine != null ? ResponseEntity.ok(medicine) : ResponseEntity.notFound().build();
    }

    @GetMapping("/status/{status}")
    public List<Medicine> getByStatus(@PathVariable String status) {
        return medicineService.getByStatus(status);
    }

    @GetMapping("/search")
    public List<Medicine> searchByName(@RequestParam String name) {
        return medicineService.searchByName(name);
    }

    @PostMapping
    public ResponseEntity<Medicine> create(@RequestBody Medicine medicine) {
        return ResponseEntity.ok(medicineService.create(medicine));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Medicine> update(@PathVariable Long id, @RequestBody Medicine medicine) {
        Medicine updated = medicineService.update(id, medicine);
        return updated != null ? ResponseEntity.ok(updated) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        medicineService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
