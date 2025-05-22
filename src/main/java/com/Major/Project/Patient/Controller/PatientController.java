package com.Major.Project.Patient.Controller;

import com.Major.Project.Patient.Entity.Patient;
import com.Major.Project.Patient.Service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/HMS/Patient")
public class PatientController {
    @Autowired
    private PatientService patientService;

    @GetMapping
    public List<Patient> getPatient(){
        return patientService.getPatientList();
    }

    @GetMapping("/{id}")
    public Patient getPatientByid(@PathVariable Long id){
        return patientService.getPatientListById(id);
    }
    @PostMapping
    public Patient savePatient(@RequestBody Patient patient){
        return patientService.createPatient(patient);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePatient(@PathVariable Long id) {
        patientService.deletePatient(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Patient> updatePatient(@PathVariable Long id,@RequestBody Patient patient){
        Patient p=patientService.updatePatient(id,patient);
        return p!= null ? ResponseEntity.ok(p) : ResponseEntity.notFound().build();
    }
}
