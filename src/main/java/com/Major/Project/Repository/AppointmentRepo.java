package com.Major.Project.Repository;

import com.Major.Project.Entity.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface AppointmentRepo extends JpaRepository<Appointment,Long> {
    List<Appointment> findByDoctor_DocId(Long DocId);
    List<Appointment> findByPatient_PatientId(Long patientId);
}
