package com.Major.Project.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Major.Project.Entity.Staff;

import java.util.List;

@Repository
public interface StaffRepository extends JpaRepository<Staff,Long> {
    List<Staff> findByDepartment(String department);
    List<Staff> findByStatus(String status);
    List<Staff> findByRole(String role);
}
