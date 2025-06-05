package com.Major.Project.Billing.Repository;

import com.Major.Project.Billing.Entity.Bill;
import com.Major.Project.Patient.Entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BillingRepository extends JpaRepository<Bill,Long> {
    List<Bill> findByPatient(Patient patient);
}
