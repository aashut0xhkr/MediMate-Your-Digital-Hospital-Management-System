package com.Major.Project.Controller;

import com.Major.Project.DTO.DoctorDTO;
import com.Major.Project.Entity.Doctor;
import com.Major.Project.Service.DoctorService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/HMS/Doctor")
public class DoctorController {
    @Autowired
    private DoctorService doctorService;

    @PreAuthorize("hasRole('ADMIN') or hasRole('DOCTOR')")
    @GetMapping
    public List<DoctorDTO> getDoctor(){
        return doctorService.getDoctorList();
    }

    @PreAuthorize("hasRole('ADMIN') or hasRole('DOCTOR')")
    @GetMapping("/{id}")
    public DoctorDTO getDoctorById(@PathVariable Long id){
        return doctorService.getDoctorListById(id);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping()
    public DoctorDTO saveDoctor(@RequestBody Doctor doctor){
        return doctorService.createDoctor(doctor);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<DoctorDTO> updateDoctor(@PathVariable Long id,@RequestBody Doctor doctor){

        DoctorDTO updated = doctorService.updateDoctor(id, doctor);
        return updated != null ? ResponseEntity.ok(updated) : ResponseEntity.notFound().build();
    }
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDoctor(@PathVariable Long id){
        doctorService.DeleteDoctor(id);
        return ResponseEntity.noContent().build();
    }
}
