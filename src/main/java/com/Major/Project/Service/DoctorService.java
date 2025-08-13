package com.Major.Project.Service;

import com.Major.Project.Configuration.CustomException;
import com.Major.Project.DTO.DoctorDTO;
import com.Major.Project.Entity.Doctor;
import com.Major.Project.Repository.DoctorRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DoctorService {
    @Autowired
    private DoctorRepository doctorRepository;
    public List<DoctorDTO> getDoctorList() {
        return doctorRepository.findAll()
                .stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public DoctorDTO getDoctorListById(Long docID) {
        Doctor doctor = doctorRepository.findById(docID)
                .orElseThrow(() -> new CustomException("ID not found"));
        return convertToDto(doctor);
    }
    public DoctorDTO createDoctor(Doctor d) {
        return convertToDto(doctorRepository.save(d));
    }

    public void DeleteDoctor(Long id) {
        doctorRepository.deleteById(id);
    }
    public DoctorDTO updateDoctor(Long id, Doctor updatedDoctor) {
        Doctor Existing = doctorRepository.findById(id)
                .orElseThrow(() -> new CustomException("ID not found"));
        Existing.setName(updatedDoctor.getName());
        Existing.setContact(updatedDoctor.getContact());
        Existing.setEmail(updatedDoctor.getEmail());
        Existing.setSpecialisation(updatedDoctor.getSpecialisation());
        return convertToDto(doctorRepository.save(Existing));
    }

    public DoctorDTO convertToDto(Doctor doctor) {
        DoctorDTO dto = new DoctorDTO();
        dto.setDocId(doctor.getDocId());
        dto.setName(doctor.getName());
        dto.setSpecialisation(doctor.getSpecialisation());
        dto.setContact(doctor.getContact());
        dto.setEmail(doctor.getEmail());
        return dto;
    }
    }

