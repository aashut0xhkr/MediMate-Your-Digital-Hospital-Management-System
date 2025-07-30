package com.Major.Project.Staff.Controller;

import com.Major.Project.Staff.DTO.StaffDTO;
import com.Major.Project.Staff.Entity.Staff;
import com.Major.Project.Staff.Service.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/HMS/Staff")
public class StaffController {
    @Autowired
    private StaffService staffService;
    @GetMapping
    public List<StaffDTO> getAll() {
        return staffService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<StaffDTO> getById(@PathVariable Long id) {
        StaffDTO staff = staffService.getById(id);
        return staff != null ? ResponseEntity.ok(staff) : ResponseEntity.notFound().build();
    }

    @GetMapping("/department/{department}")
    public List<StaffDTO> getByDepartment(@PathVariable String department) {
        return staffService.getByDepartment(department);
    }

    @GetMapping("/status/{status}")
    public List<StaffDTO> getByStatus(@PathVariable String status) {
        return staffService.getByStatus(status);
    }

    @GetMapping("/role/{role}")
    public List<StaffDTO> getByRole(@PathVariable String role) {
        return staffService.getByRole(role);
    }

    @PostMapping
    public ResponseEntity<StaffDTO> create(@RequestBody Staff staff) {
        return ResponseEntity.ok(staffService.create(staff));
    }

    @PutMapping("/{id}")
    public ResponseEntity<StaffDTO> update(@PathVariable Long id, @RequestBody Staff staff) {
        StaffDTO updated = staffService.update(id, staff);
        return updated != null ? ResponseEntity.ok(updated) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        staffService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
