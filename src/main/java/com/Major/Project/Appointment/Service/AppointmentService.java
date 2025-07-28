package com.Major.Project.Appointment.Service;

import com.Major.Project.Appointment.DTO.AppointmentDTO;
import com.Major.Project.Appointment.Entity.Appointment;
import com.Major.Project.Appointment.Repository.AppointmentRepo;
import com.Major.Project.Configuration.CustomException;
import com.Major.Project.Doctor.Entity.Doctor;
import com.Major.Project.Doctor.Repository.DoctorRepository;
import com.Major.Project.Patient.Entity.Patient;
import com.Major.Project.Patient.Repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AppointmentService {

    @Autowired
    private AppointmentRepo appointmentRepo;

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private DoctorRepository doctorRepository;

    public List<AppointmentDTO> getAppointments() {
        List<Appointment> appointments = appointmentRepo.findAll();
        return appointments.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    public AppointmentDTO getAppointmentById(Long appointmentId) {
        Appointment appointment = appointmentRepo.findById(appointmentId)
                .orElseThrow(() -> new CustomException("Appointment not found with ID: " + appointmentId));
        return convertToDto(appointment);
    }

    public List<AppointmentDTO> findByDoctorId(Long docId) {
        List<Appointment> appointments = appointmentRepo.findByDoctor_DocId(docId);
        return appointments.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    public List<AppointmentDTO> findByPatientId(Long patientId) {
        List<Appointment> appointments = appointmentRepo.findByPatient_PatientId(patientId);
        return appointments.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    public void deleteAppointment(Long id) {
        appointmentRepo.deleteById(id);
    }

    public AppointmentDTO createAppointment(Appointment appointment) {
        if (appointment.getPatient() == null || appointment.getPatient().getPatientId() == null) {
            throw new CustomException("Patient ID must not be null");
        }
        if (appointment.getDoctor() == null || appointment.getDoctor().getDocId() == null) {
            throw new CustomException("Doctor ID must not be null");
        }

        Long patientId = appointment.getPatient().getPatientId();
        Long docId = appointment.getDoctor().getDocId();

        Patient patient = patientRepository.findById(patientId)
                .orElseThrow(() -> new CustomException("Patient not found with ID: " + patientId + " while creating appointment"));
        Doctor doctor = doctorRepository.findById(docId)
                .orElseThrow(() -> new CustomException("Doctor not found with ID: " + docId + " while creating appointment"));

        appointment.setPatient(patient);
        appointment.setDoctor(doctor);
        Appointment savedAppointment = appointmentRepo.save(appointment);
        return convertToDto(savedAppointment);
    }

    public AppointmentDTO updateAppointment(Long appointmentId, Appointment appointment) {
        Appointment appointmentById = appointmentRepo.findById(appointmentId)
                .orElseThrow(() -> new CustomException("Appointment not found with ID: " + appointmentId));

        Long patientId = appointment.getPatient().getPatientId();
        Long doctorId = appointment.getDoctor().getDocId();

        Patient patient = patientRepository.findById(patientId)
                .orElseThrow(() -> new CustomException("Patient not found with ID: " + patientId));
        Doctor doctor = doctorRepository.findById(doctorId)
                .orElseThrow(() -> new CustomException("Doctor not found with ID: " + doctorId));

        appointmentById.setPatient(patient);
        appointmentById.setDoctor(doctor);
        appointmentById.setAppointmentTime(appointment.getAppointmentTime());
        appointmentById.setStatus(appointment.getStatus());

        Appointment updated = appointmentRepo.save(appointmentById);
        return convertToDto(updated);
    }

    private AppointmentDTO convertToDto(Appointment appointment) {
        AppointmentDTO appointmentDTO = new AppointmentDTO();
        appointmentDTO.setAppointmentID(appointment.getAppointmentId());
        appointmentDTO.setAppointmentTime(appointment.getAppointmentTime());
        appointmentDTO.setStatus(appointment.getStatus());
        appointmentDTO.setPatientName(appointment.getPatient().getName());
        appointmentDTO.setDoctorName(appointment.getDoctor().getName());
        return appointmentDTO;
    }
}
