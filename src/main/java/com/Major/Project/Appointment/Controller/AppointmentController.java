package com.Major.Project.Appointment.Controller;

import com.Major.Project.Appointment.Entity.Appointment;
import com.Major.Project.Appointment.Service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/HMS/Appointment")
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;

    @GetMapping()
    public List<Appointment> getAllAppointment(){
        return appointmentService.getAppointments();
    }
    @GetMapping("/{id}")
    public Appointment getAppointmentById(@PathVariable Long appointmentId){
        return appointmentService.getAppointmentById(appointmentId);
    }
    @PostMapping
    public Appointment createAppointment(@RequestBody Appointment appointment){
        return appointmentService.CreateAppointment(appointment);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> DeleteAppointment(@PathVariable Long appointmentId){
        appointmentService.DeleteAppointment(appointmentId);
        return ResponseEntity.noContent().build();
    }
    @PutMapping("/{id}")
    public ResponseEntity<Appointment> updateAppointment(@PathVariable Long appointmentId,Appointment appointment ){
        Appointment updated = appointmentService.updateAppointment(appointmentId, appointment);
        return updated != null ? ResponseEntity.ok(updated) : ResponseEntity.notFound().build();
    }
    @GetMapping("/doctors/{doctorId}")
    public List<Appointment> getByDoctor(@PathVariable Long doctorId) {
        return appointmentService.findByDoctorId(doctorId);
    }

    @GetMapping("/patients/{patientId}")
    public List<Appointment> getByPatient(@PathVariable Long patientId) {
        return appointmentService.findByPatientId(patientId);
    }

}
