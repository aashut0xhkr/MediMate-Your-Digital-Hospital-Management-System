package com.Major.Project.Patient.Service;

import com.Major.Project.Configuration.CustomException;
import com.Major.Project.Patient.Entity.Patient;
import com.Major.Project.Patient.Repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientService {

    @Autowired
    private PatientRepository patientRepo;

    public List<Patient> getPatientList(){
        return patientRepo.findAll();
    }
    public Patient getPatientListById(Long id){
        return patientRepo.findById(id).orElseThrow(()->new CustomException("ID Not Found"));
    }
    public Patient createPatient(Patient patient){
        return patientRepo.save(patient);
    }
    public void deletePatient(Long id){
       patientRepo.deleteById(id);
    }
    public Patient updatePatient(Long id,Patient updatedPatient){
       Patient Existing= getPatientListById(id);
       if(Existing== null) return null;
       Existing.setName(updatedPatient.getName());
       Existing.setAge(updatedPatient.getAge());
       Existing.setGender(updatedPatient.getGender());
       Existing.setContact(updatedPatient.getContact());
       return patientRepo.save(Existing);
    }
}
