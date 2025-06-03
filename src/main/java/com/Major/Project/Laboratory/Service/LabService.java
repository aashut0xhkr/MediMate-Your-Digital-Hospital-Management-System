package com.Major.Project.Laboratory.Service;

import com.Major.Project.Laboratory.Entity.LabTest;
import com.Major.Project.Laboratory.LabRepository.LabRepository;
import com.Major.Project.Patient.Entity.Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LabService {
    @Autowired
    private LabRepository labRepository;

    public List<LabTest> getAllLabTest(){
        return labRepository.findAll();
    }
    public LabTest getLabTestByID(Long id){
        return labRepository.findById(id).orElse(null);
    }
    public List<LabTest> getByPatientID(Patient patientId){
        return labRepository.findByPatient(patientId);
    }
    public List<LabTest> getByStatus(String status){
        return labRepository.findByStatus(status);
    }
    public LabTest createLabTest(LabTest labTest){
        return labRepository.save(labTest);
    }
    public LabTest UpdateLabTest(Long id,LabTest labTest){
        LabTest byId = getLabTestByID(id);
        if(byId==null) return null;
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
