package com.Major.Project;

import com.Major.Project.Configuration.CustomException;
import com.Major.Project.DTO.PatientDTO;
import com.Major.Project.Entity.Patient;
import com.Major.Project.Repository.PatientRepository;
import com.Major.Project.Service.PatientService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PatientServiceTest {

    @Mock
    private PatientRepository patientRepo;

    @InjectMocks
    private PatientService patientService;

    private Patient patient;

    @BeforeEach
    void setUp() {
        patient = new Patient();
        patient.setPatientId(1L);
        patient.setName("John Doe");
        patient.setAge(30);
        patient.setGender("Male");
        patient.setContact("9876543210");
    }

    @Test
    @DisplayName("Should return list of patients")
    void testGetPatientList() {
        when(patientRepo.findAll()).thenReturn(List.of(patient));

        List<PatientDTO> result = patientService.getPatientList();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("John Doe", result.get(0).getName());
        verify(patientRepo, times(1)).findAll();
    }

    @Test
    @DisplayName("Should return patient when ID exists")
    void testGetPatientListById_Success() {
        when(patientRepo.findById(1L)).thenReturn(Optional.of(patient));

        PatientDTO result = patientService.getPatientListById(1L);

        assertNotNull(result);
        assertEquals("John Doe", result.getName());
        verify(patientRepo, times(1)).findById(1L);
    }

    @Test
    @DisplayName("Should throw exception when patient ID not found")
    void testGetPatientListById_NotFound() {
        when(patientRepo.findById(99L)).thenReturn(Optional.empty());

        CustomException exception = assertThrows(
                CustomException.class,
                () -> patientService.getPatientListById(99L)
        );

        assertEquals("ID Not Found", exception.getMessage());
        verify(patientRepo, times(1)).findById(99L);
    }

    @Test
    @DisplayName("Should create and return patient")
    void testCreatePatient() {
        when(patientRepo.save(patient)).thenReturn(patient);

        PatientDTO result = patientService.createPatient(patient);

        assertNotNull(result);
        assertEquals("John Doe", result.getName());
        verify(patientRepo, times(1)).save(patient);
    }

    @Test
    @DisplayName("Should update patient when ID exists")
    void testUpdatePatient_Success() {
        Patient updatedPatient = new Patient();
        updatedPatient.setName("Updated Name");
        updatedPatient.setAge(40);
        updatedPatient.setGender("Male");
        updatedPatient.setContact("1234567890");

        when(patientRepo.findById(1L)).thenReturn(Optional.of(patient));
        when(patientRepo.save(any(Patient.class))).thenReturn(patient);

        PatientDTO result = patientService.updatePatient(1L, updatedPatient);

        assertNotNull(result);
        assertEquals("Updated Name", result.getName());
        assertEquals(40, result.getAge());
        verify(patientRepo, times(1)).findById(1L);
        verify(patientRepo, times(1)).save(any(Patient.class));
    }

    @Test
    @DisplayName("Should throw exception when updating non-existing patient")
    void testUpdatePatient_NotFound() {
        when(patientRepo.findById(99L)).thenReturn(Optional.empty());

        CustomException exception = assertThrows(
                CustomException.class,
                () -> patientService.updatePatient(99L, patient)
        );

        assertEquals("ID Not Found", exception.getMessage());
        verify(patientRepo, times(1)).findById(99L);
    }

    @Test
    @DisplayName("Should delete patient by ID")
    void testDeletePatient() {
        doNothing().when(patientRepo).deleteById(1L);

        patientService.deletePatient(1L);

        verify(patientRepo, times(1)).deleteById(1L);
    }
}