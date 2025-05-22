package com.Major.Project.Laboratory.LabRepository;

import com.Major.Project.Laboratory.Entity.LabTest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LabRepository extends JpaRepository<LabTest,Long> {
    List<LabTest> findByPatientId(Long patientId);
    List<LabTest> findByStatus(String status);
}
