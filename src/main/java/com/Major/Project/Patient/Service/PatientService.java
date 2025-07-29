package com.Major.Project.Patient.Service;

import com.Major.Project.Configuration.CustomException;
import com.Major.Project.Patient.DTO.PatientDTO;
import com.Major.Project.Patient.Entity.Patient;
import com.Major.Project.Patient.Repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientService {

    @Autowired
    private PatientRepository patientRepo;

    public List<PatientDTO> getPatientList(){
        return patientRepo.findAll()
                .stream()
                .map(this::convertToDTO)
                .toList();
    }
    public PatientDTO getPatientListById(Long id){
        Patient patient = patientRepo.findById(id)
                .orElseThrow(() -> new CustomException("ID Not Found"));
        return convertToDTO(patient);
    }
    public PatientDTO createPatient(Patient patient){
        Patient saved = patientRepo.save(patient);
        return convertToDTO(saved);
    }
    public void deletePatient(Long id){
       patientRepo.deleteById(id);
    }
    public PatientDTO updatePatient(Long id, Patient updatedPatient){
        Patient Existing = getPatientEntityById(id);
        if(Existing == null) return null;

        Existing.setName(updatedPatient.getName());
        Existing.setAge(updatedPatient.getAge());
        Existing.setGender(updatedPatient.getGender());
        Existing.setContact(updatedPatient.getContact());

        Patient saved = patientRepo.save(Existing);
        return convertToDTO(saved);
    }
    private Patient getPatientEntityById(Long id){
        return patientRepo.findById(id).orElseThrow(() -> new CustomException("ID Not Found"));
    } //helper to fetch the patient from DB

    private PatientDTO convertToDTO(Patient patient){
        PatientDTO patientDTO=new PatientDTO();
        patientDTO.setPatientId(patient.getPatientId());
        patientDTO.setName(patient.getName());
        patientDTO.setAge(patient.getAge());
        patientDTO.setGender(patient.getGender());
        if(patient.getAppointments()!=null){
            patientDTO.setAppointmentIds(patient.getAppointments().stream()
                    .map(a->a.getAppointmentId())
                    .toList()

            );
        }
        if(patient.getBillings()!=null){
            patientDTO.setBillIds(patient.getBillings().stream().map(b->b.getBillID()).toList());

        }
        if (patient.getLaboratoryTests() != null) {
            patientDTO.setLabTestIds(
                    patient.getLaboratoryTests().stream()
                            .map(l -> l.getId())
                            .toList()
            );
        }
        if (patient.getPharmacyOrders() != null) {
            // Assuming Medicine entity has getMedicineId()
            patientDTO.setPharmacyOrderIds(
                    patient.getPharmacyOrders().stream()
                            .map(m -> m.getId())
                            .toList()
            );
        }
        return patientDTO;

    }
}
