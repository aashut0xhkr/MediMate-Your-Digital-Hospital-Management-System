package com.Major.Project.Repository;

import com.Major.Project.Entity.LabTest;
import com.Major.Project.Entity.Patient;
import com.mysql.cj.protocol.PacketReceivedTimeHolder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LabRepository extends JpaRepository<LabTest,Long> {
    List<LabTest> findByPatient(Patient patient);
    List<LabTest> findByStatus(String status);
}
