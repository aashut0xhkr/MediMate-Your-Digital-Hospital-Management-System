package com.Major.Project.Doctor.Repository;

import com.Major.Project.Doctor.Entity.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DoctorRepository extends JpaRepository<Doctor,Long> {
}
