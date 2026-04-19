package com.Major.Project.Repository;

import com.Major.Project.Entity.Appointment;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface AppointmentRepo extends JpaRepository<Appointment, Long> {

    List<Appointment> findByDoctor_DocId(Long docId);
    List<Appointment> findByPatient_PatientId(Long patientId);

    // New method to prevent double-booking
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("SELECT a FROM Appointment a WHERE a.doctor.docId = :docId AND a.appointmentTime = :time")
    Optional<Appointment> findForUpdate(@Param("docId") Long docId, @Param("time") LocalDateTime time);
}