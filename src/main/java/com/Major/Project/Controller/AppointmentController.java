package com.Major.Project.Controller;

import com.Major.Project.Entity.Appointment;
import com.Major.Project.Service.AppointmentService;
import com.Major.Project.DTO.AppointmentDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/HMS/Appointment")
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;

    @GetMapping
    public List<AppointmentDTO> getAllAppointment(){
        return appointmentService.getAppointments();
    }
    @GetMapping("/{id}")
    public AppointmentDTO getAppointmentById(@PathVariable Long id){
        return appointmentService.getAppointmentById(id);
    }
    @PostMapping
    public AppointmentDTO createAppointment(@RequestBody Appointment appointment){
        return appointmentService.createAppointment(appointment);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> DeleteAppointment(@PathVariable Long id){
        appointmentService.deleteAppointment(id);
        return ResponseEntity.noContent().build();
    }
    @PutMapping("/{id}")
    public ResponseEntity<AppointmentDTO> updateAppointment(@PathVariable Long id,@RequestBody Appointment appointment ){
        AppointmentDTO updated = appointmentService.updateAppointment(id, appointment);
        return updated != null ? ResponseEntity.ok(updated) : ResponseEntity.notFound().build();
    }
    @GetMapping("/doctor/{doctorId}")
    public List<AppointmentDTO> getByDoctor(@PathVariable Long doctorId) {
        return appointmentService.findByDoctorId(doctorId);
    }

    @GetMapping("/patient/{patientId}")
    public List<AppointmentDTO> getByPatient(@PathVariable Long patientId) {
        return appointmentService.findByPatientId(patientId);
    }

}
