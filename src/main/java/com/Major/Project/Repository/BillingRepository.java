package com.Major.Project.Repository;

import com.Major.Project.Entity.Bill;
import com.Major.Project.Entity.Patient;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BillingRepository extends JpaRepository<Bill,Long> {
    List<Bill> findByPatient(Patient patient);
}
