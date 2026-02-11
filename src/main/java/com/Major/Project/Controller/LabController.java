package com.Major.Project.Controller;

import com.Major.Project.DTO.LabTestDTO;
import com.Major.Project.Entity.LabTest;
import com.Major.Project.Service.LabService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/HMS/LabTest")
public class LabController {
    @Autowired
    private LabService labService;

    @PreAuthorize("hasRole('DOCTOR')")
    @GetMapping
    public List<LabTestDTO> getAllLabTest() {
        return labService.getAllLabTest();
    }

    @PreAuthorize("hasRole('DOCTOR') or hasRole('PATIENT')")
    @GetMapping("/{id}")
    public LabTestDTO getLabTestByID(@PathVariable Long id) {
        return labService.getLabTestByID(id);
    }

    @PreAuthorize("hasRole('DOCTOR') or hasRole('PATIENT')")
    @GetMapping("/patient/{patientId}")
    public List<LabTestDTO> getByPatientID(@PathVariable Long patientId){
        return labService.getByPatientId(patientId);
    }

    @PreAuthorize("hasRole('DOCTOR') or hasRole('PATIENT')")
    @GetMapping("/status/{status}")
    public List<LabTestDTO> getByStatus(@PathVariable String status){
        return labService.getByStatus(status);
    }

    @PreAuthorize("hasRole('DOCTOR')")
    @PostMapping
    public LabTestDTO createLabTest(@RequestBody LabTest labTest){
        return labService.createLabTest(labTest);
    }

    @PreAuthorize("hasRole('DOCTOR')")
    @PutMapping("/{id}")
    public ResponseEntity<LabTestDTO> UpdateLabTest(@PathVariable Long id, @RequestBody LabTest labTest){
        LabTestDTO labTest1 = labService.UpdateLabTest(id, labTest);
        return labTest1 != null ? ResponseEntity.ok(labTest1) : ResponseEntity.notFound().build();
    }

    @PreAuthorize("hasRole('DOCTOR')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLabTest(@PathVariable Long id){
        labService.deleteLabTest(id);
        return ResponseEntity.noContent().build();
    }
}
