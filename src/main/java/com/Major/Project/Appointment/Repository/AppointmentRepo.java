package com.Major.Project.Appointment.Repository;

import com.Major.Project.Appointment.Entity.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface AppointmentRepo extends JpaRepository<Appointment,Long> {
    List<Appointment> findByDoctorId(Long DocId);
    List<Appointment> findByPatientId(Long patientId);
}
