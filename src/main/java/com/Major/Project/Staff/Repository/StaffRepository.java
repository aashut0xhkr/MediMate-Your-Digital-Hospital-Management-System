package com.Major.Project.Staff.Repository;

import com.Major.Project.Staff.Entity.Staff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StaffRepository extends JpaRepository<Staff,Long> {
    List<Staff> findByDepartment(String department);
    List<Staff> findByStatus(String status);
    List<Staff> findByRole(String role);
}
