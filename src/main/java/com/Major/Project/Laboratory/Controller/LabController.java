package com.Major.Project.Laboratory.Controller;

import com.Major.Project.Laboratory.Entity.LabTest;
import com.Major.Project.Laboratory.Service.LabService;
import com.Major.Project.Patient.Entity.Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/HMS/LabTest")
public class LabController {
    @Autowired
    private LabService labService;

    @GetMapping
    public List<LabTest> getAllLabTest() {
        return labService.getAllLabTest();
    }
    @GetMapping("/{id}")
    public LabTest getLabTestByID(@PathVariable Long id) {
        return labService.getLabTestByID(id);
    }
    @GetMapping("/patient/{patientId}")
    public List<LabTest> getByPatientID(@PathVariable Long patientId){
        return labService.getByPatientId(patientId);
    }
    @GetMapping("/status/{status}")
    public List<LabTest> getByStatus(@PathVariable String status){
        return labService.getByStatus(status);
    }
    @PostMapping
    public LabTest createLabTest(@RequestBody LabTest labTest){
        return labService.createLabTest(labTest);
    }
    @PutMapping("/{id}")
    public ResponseEntity<LabTest> UpdateLabTest(@PathVariable Long id, @RequestBody LabTest labTest){
        LabTest labTest1 = labService.UpdateLabTest(id, labTest);
        return labTest1 != null ? ResponseEntity.ok(labTest1) : ResponseEntity.notFound().build();
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLabtest(@PathVariable Long id){
        labService.deleteLabTest(id);
        return ResponseEntity.noContent().build();
    }
}
