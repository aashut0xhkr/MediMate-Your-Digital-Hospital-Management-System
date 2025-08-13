package com.Major.Project.Service;

import com.Major.Project.DTO.StaffDTO;
import com.Major.Project.Entity.Staff;
import com.Major.Project.Repository.StaffRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StaffService {
    @Autowired
    private StaffRepository staffRepository;

    public List<StaffDTO> getAll() {
        return staffRepository.findAll()
                .stream()
                .map(this::convertToDTO)
                .toList();
    }

    public StaffDTO getById(Long id) {
        Staff staff = staffRepository.findById(id).orElse(null);
        return staff == null ? null : convertToDTO(staff);
    }

    public List<StaffDTO> getByDepartment(String department) {
        return staffRepository.findByDepartment(department)
                .stream()
                .map(this::convertToDTO)
                .toList();
    }

    public List<StaffDTO> getByStatus(String status) {
        return staffRepository.findByStatus(status)
                .stream()
                .map(this::convertToDTO)
                .toList();
    }

    public List<StaffDTO> getByRole(String role) {
        return staffRepository.findByRole(role)
                .stream()
                .map(this::convertToDTO)
                .toList();
    }

    public StaffDTO create(Staff staff) {
        Staff saved = staffRepository.save(staff);
        return convertToDTO(saved);
    }

    public StaffDTO update(Long id, Staff updated) {
        Staff existing = staffRepository.findById(id).orElse(null);
        if (existing == null) return null;

        existing.setName(updated.getName());
        existing.setRole(updated.getRole());
        existing.setDepartment(updated.getDepartment());
        existing.setPhone(updated.getPhone());
        existing.setEmail(updated.getEmail());
        existing.setStatus(updated.getStatus());

        Staff saved = staffRepository.save(existing);
        return convertToDTO(saved);
    }

    public void delete(Long id) {
        staffRepository.deleteById(id);
    }

    private StaffDTO convertToDTO(Staff staff){
        StaffDTO staffDTO=new StaffDTO();
        staffDTO.setId(staff.getId());
        staffDTO.setName(staff.getName());
        staffDTO.setRole(staff.getRole());
        staffDTO.setDepartment(staff.getDepartment());
        staffDTO.setPhone(staff.getPhone());
        staffDTO.setEmail(staff.getEmail());
        staffDTO.setStatus(staff.getStatus());
        if (staff.getInventory() != null) {
            staffDTO.setInventoryIds(
                    staff.getInventory().stream()
                            .map(i -> i.getInventoryId())
                            .toList()
            );
        }
        return staffDTO;
    }

}
