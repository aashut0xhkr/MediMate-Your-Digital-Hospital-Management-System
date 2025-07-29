package com.Major.Project.Laboratory.Service;

import com.Major.Project.Configuration.CustomException;
import com.Major.Project.Laboratory.DTO.LabTestDTO;
import com.Major.Project.Laboratory.Entity.LabTest;
import com.Major.Project.Laboratory.LabRepository.LabRepository;
import com.Major.Project.Patient.Entity.Patient;
import com.Major.Project.Patient.Repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LabService {
    @Autowired
    private LabRepository labRepository;

    @Autowired
    private PatientRepository patientRepository;

    public List<LabTestDTO> getAllLabTest(){
        return labRepository.findAll()
                .stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }
    public LabTestDTO getLabTestByID(Long id){
        LabTest labTest = labRepository.findById(id)
                .orElseThrow(() -> new CustomException("Lab test not found"));
        return convertToDto(labTest);
    }
    public List<LabTestDTO> getByPatientId(Long patientId) {
        Patient patient = patientRepository.findById(patientId)
                .orElseThrow(() -> new CustomException("Patient not found with ID: " + patientId));
        return labRepository.findByPatient(patient)
                .stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public List<LabTestDTO> getByStatus(String status){
        return labRepository.findByStatus(status)
                .stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }
    public LabTestDTO createLabTest(LabTest labTest) {
        if (labTest.getPatient() != null && labTest.getPatient().getPatientId() != null) {
            Patient fullPatient = patientRepository.findById(labTest.getPatient().getPatientId())
                    .orElseThrow(() -> new CustomException(
                            "Patient not found with ID: " + labTest.getPatient().getPatientId() + " while creating lab test"));
            labTest.setPatient(fullPatient);
        }
        LabTest saved = labRepository.save(labTest);
        return convertToDto(saved);
    }
    public LabTestDTO UpdateLabTest(Long id, LabTest labTest){
        LabTest byId = labRepository.findById(id)
                .orElseThrow(() -> new CustomException("Lab test not found"));
        byId.setTestName(labTest.getTestName());
        byId.setResult(labTest.getResult());
        byId.setTestDate(labTest.getTestDate());
        byId.setStatus(labTest.getStatus());
        LabTest updated = labRepository.save(byId);
        return convertToDto(updated);
    }
    public void deleteLabTest(Long id){
        labRepository.deleteById(id);
    }
    private LabTestDTO convertToDto(LabTest labTest){
        LabTestDTO labTestDTO=new LabTestDTO();
        labTestDTO.setId(labTest.getId());
        labTestDTO.setTestName(labTest.getTestName());
        labTestDTO.setTestDate(labTest.getTestDate());
        labTestDTO.setResult(labTest.getResult());
        labTestDTO.setStatus(labTest.getStatus());
        labTestDTO.setPatientId(labTest.getPatient().getPatientId());
        labTestDTO.setLabTechnicianId(labTest.getLabTechnician().getId());
        return labTestDTO;

    }
}
