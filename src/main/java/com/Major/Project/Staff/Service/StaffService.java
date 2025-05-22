package com.Major.Project.Staff.Service;

import com.Major.Project.Staff.Entity.Staff;
import com.Major.Project.Staff.Repository.StaffRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StaffService {
    @Autowired
    private StaffRepository staffRepository;

    public List<Staff> getAll() {
        return staffRepository.findAll();
    }

    public Staff getById(Long id) {
        return staffRepository.findById(id).orElse(null);
    }

    public List<Staff> getByDepartment(String department) {
        return staffRepository.findByDepartment(department);
    }

    public List<Staff> getByStatus(String status) {
        return staffRepository.findByStatus(status);
    }

    public List<Staff> getByRole(String role) {
        return staffRepository.findByRole(role);
    }

    public Staff create(Staff staff) {
        return staffRepository.save(staff);
    }

    public Staff update(Long id, Staff updated) {
        Staff existing = getById(id);
        if (existing == null) return null;
        existing.setName(updated.getName());
        existing.setRole(updated.getRole());
        existing.setDepartment(updated.getDepartment());
        existing.setPhone(updated.getPhone());
        existing.setEmail(updated.getEmail());
        existing.setStatus(updated.getStatus());
        return staffRepository.save(existing);
    }

    public void delete(Long id) {
        staffRepository.deleteById(id);
    }

}
