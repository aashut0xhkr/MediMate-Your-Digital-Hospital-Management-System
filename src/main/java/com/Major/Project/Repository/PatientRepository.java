package com.Major.Project.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Major.Project.Entity.Patient;

@Repository
public interface PatientRepository extends JpaRepository<Patient,Long> {
}
