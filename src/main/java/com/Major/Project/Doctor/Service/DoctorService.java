package com.Major.Project.Doctor.Service;

import com.Major.Project.Configuration.CustomException;
import com.Major.Project.Doctor.Entity.Doctor;
import com.Major.Project.Doctor.Repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DoctorService {
    @Autowired
    private DoctorRepository doctorRepository;

    public List<Doctor> getDoctorList(){
        return doctorRepository.findAll();
    }
    public Doctor getDoctorListById(Long docID) {
        return doctorRepository.findById(docID).orElseThrow(()->new CustomException("ID not found"));
    }
    public Doctor createDoctor(Doctor d){
        return doctorRepository.save(d);
    }

    public void DeleteDoctor(Long id){
        doctorRepository.deleteById(id);
    }
    public Doctor updateDoctor(Long id,Doctor updatedDoctor){
        Doctor Existing=getDoctorListById(id);
        if(Existing==null) return null;
        Existing.setName(updatedDoctor.getName());
        Existing.setContact(updatedDoctor.getContact());
        Existing.setEmail(updatedDoctor.getEmail());
        Existing.setSpecialisation(updatedDoctor.getSpecialisation());
        return doctorRepository.save(Existing);
    }
    }

