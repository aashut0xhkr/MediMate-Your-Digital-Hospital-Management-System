    package com.Major.Project.Controller;

    import com.Major.Project.Entity.Appointment;
    import com.Major.Project.Service.AppointmentService;
    import com.Major.Project.DTO.AppointmentDTO;

    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.http.ResponseEntity;
    import org.springframework.security.access.prepost.PreAuthorize;
    import org.springframework.web.bind.annotation.*;

    import java.util.List;

    @RestController
    @RequestMapping("/HMS/Appointment")
    public class AppointmentController {

        @Autowired
        private AppointmentService appointmentService;

        @PreAuthorize("hasRole('DOCTOR') or hasRole('PATIENT')")
        @GetMapping
        public List<AppointmentDTO> getAllAppointment(){
            return appointmentService.getAppointments();
        }

        @PreAuthorize("hasRole('DOCTOR') or hasRole('PATIENT')")
        @GetMapping("/{id}")
        public AppointmentDTO getAppointmentById(@PathVariable Long id){
            return appointmentService.getAppointmentById(id);
        }

        @PreAuthorize("hasRole('DOCTOR') or hasRole('PATIENT')")
        @PostMapping
        public AppointmentDTO createAppointment(@RequestBody Appointment appointment){
            return appointmentService.createAppointment(appointment);
        }

        @PreAuthorize("hasRole('DOCTOR') or hasRole('PATIENT')")
        @DeleteMapping("/{id}")
        public ResponseEntity<Void> DeleteAppointment(@PathVariable Long id){
            appointmentService.deleteAppointment(id);
            return ResponseEntity.noContent().build();
        }

        @PreAuthorize("hasRole('DOCTOR') or hasRole('PATIENT')")
        @PutMapping("/{id}")
        public ResponseEntity<AppointmentDTO> updateAppointment(@PathVariable Long id,@RequestBody Appointment appointment ){
            AppointmentDTO updated = appointmentService.updateAppointment(id, appointment);
            return updated != null ? ResponseEntity.ok(updated) : ResponseEntity.notFound().build();
        }

        @PreAuthorize("hasRole('DOCTOR')")
        @GetMapping("/doctor/{doctorId}")
        public List<AppointmentDTO> getByDoctor(@PathVariable Long doctorId) {
            return appointmentService.findByDoctorId(doctorId);
        }

        @PreAuthorize("hasRole('DOCTOR') or hasRole('PATIENT')")
        @GetMapping("/patient/{patientId}")
        public List<AppointmentDTO> getByPatient(@PathVariable Long patientId) {
            return appointmentService.findByPatientId(patientId);
        }

    }
