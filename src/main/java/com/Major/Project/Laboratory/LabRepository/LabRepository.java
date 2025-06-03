package com.Major.Project.Laboratory.LabRepository;

import com.Major.Project.Laboratory.Entity.LabTest;
import com.Major.Project.Patient.Entity.Patient;
import com.mysql.cj.protocol.PacketReceivedTimeHolder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LabRepository extends JpaRepository<LabTest,Long> {
    List<LabTest> findByPatient(Patient patientId);
    List<LabTest> findByStatus(String status);
}
