package com.Major.Project.Pharmacy.Controller;

import com.Major.Project.Pharmacy.DTO.MedicineDTO;
import com.Major.Project.Pharmacy.Entity.Medicine;
import com.Major.Project.Pharmacy.Service.MedicineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/HMS/Medicine")
public class MedicineController {
    @Autowired
    private MedicineService medicineService;

    @GetMapping
    public List<MedicineDTO> getAll() {
        return medicineService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<MedicineDTO> getById(@PathVariable Long id) {
        MedicineDTO medicine = medicineService.getById(id);
        return medicine != null ? ResponseEntity.ok(medicine) : ResponseEntity.notFound().build();
    }

    @GetMapping("/status/{status}")
    public List<MedicineDTO> getByStatus(@PathVariable String status) {
        return medicineService.getByStatus(status);
    }

    @GetMapping("/search/{name}")
    public List<MedicineDTO> searchByName(@PathVariable String name) {
        return medicineService.searchByName(name);
    }

    @PostMapping
    public ResponseEntity<MedicineDTO> create(@RequestBody Medicine medicine) {
        return ResponseEntity.ok(medicineService.create(medicine));
    }

    @PutMapping("/{id}")
    public ResponseEntity<MedicineDTO> update(@PathVariable Long id, @RequestBody Medicine medicine) {
        MedicineDTO updated = medicineService.update(id, medicine);
        return updated != null ? ResponseEntity.ok(updated) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        medicineService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
