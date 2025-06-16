package com.Major.Project.Laboratory.Service;

import com.Major.Project.Configuration.CustomException;
import com.Major.Project.Laboratory.Entity.LabTest;
import com.Major.Project.Laboratory.LabRepository.LabRepository;
import com.Major.Project.Patient.Entity.Patient;
import com.Major.Project.Patient.Repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LabService {
    @Autowired
    private LabRepository labRepository;

    @Autowired
    private PatientRepository patientRepository;

    public List<LabTest> getAllLabTest(){
        return labRepository.findAll();
    }
    public LabTest getLabTestByID(Long id){
        return labRepository.findById(id).orElseThrow(()->new CustomException("Lab test not found"));
    }
    public List<LabTest> getByPatientId(Long patientId) {
        Patient patient = patientRepository.findById(patientId).orElseThrow(()->new CustomException("Patient not found with ID: " + patientId));
        return patient != null ? labRepository.findByPatient(patient) : List.of();
    }
    public List<LabTest> getByStatus(String status){
        return labRepository.findByStatus(status);
    }
    public LabTest createLabTest(LabTest labTest) {
        if (labTest.getPatient() != null && labTest.getPatient().getPatientId() != null) {
            Patient fullPatient = patientRepository.findById(labTest.getPatient().getPatientId())
                    .orElseThrow(()->new CustomException("Patient not found with ID: " + labTest.getPatient().getPatientId() + " while creating lab test"));
            labTest.setPatient(fullPatient);
        }
        return labRepository.save(labTest);
    }
    public LabTest UpdateLabTest(Long id,LabTest labTest){
        LabTest byId = getLabTestByID(id);
        byId.setTestName(labTest.getTestName());
        byId.setResult(labTest.getResult());
        byId.setTestDate(labTest.getTestDate());
        byId.setStatus(labTest.getStatus());
        return labRepository.save(byId);
    }
    public void deleteLabTest(Long id){
        labRepository.deleteById(id);
    }
}
