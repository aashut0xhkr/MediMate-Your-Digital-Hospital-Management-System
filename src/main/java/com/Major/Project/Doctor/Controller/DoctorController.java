package com.Major.Project.Doctor.Controller;

import com.Major.Project.Doctor.DTO.DoctorDTO;
import com.Major.Project.Doctor.Entity.Doctor;
import com.Major.Project.Doctor.Service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/HMS/Doctor")
public class DoctorController {
    @Autowired
    private DoctorService doctorService;

    @GetMapping
    public List<DoctorDTO> getDoctor(){
        return doctorService.getDoctorList();
    }
    @GetMapping("/{id}")
    public DoctorDTO getDoctorById(@PathVariable Long id){
        return doctorService.getDoctorListById(id);
    }
    @PostMapping()
    public DoctorDTO saveDoctor(@RequestBody Doctor doctor){
        return doctorService.createDoctor(doctor);
    }
    @PutMapping("/{id}")
    public ResponseEntity<DoctorDTO> updateDoctor(@PathVariable Long id,@RequestBody Doctor doctor){

        DoctorDTO updated = doctorService.updateDoctor(id, doctor);
        return updated != null ? ResponseEntity.ok(updated) : ResponseEntity.notFound().build();
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDoctor(@PathVariable Long id){
        doctorService.DeleteDoctor(id);
        return ResponseEntity.noContent().build();
    }
}
