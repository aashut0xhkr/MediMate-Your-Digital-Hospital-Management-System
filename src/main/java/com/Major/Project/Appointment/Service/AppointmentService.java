package com.Major.Project.Appointment.Service;

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

@Service
public class AppointmentService {

    @Autowired
    private AppointmentRepo appointmentRepo;

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private DoctorRepository doctorRepository;

    public List<Appointment> getAppointments(){
        return appointmentRepo.findAll();
    }
    public Appointment getAppointmentById(Long appointmentId){
        return appointmentRepo.findById(appointmentId).orElseThrow(()-> new CustomException("id not find"));
    }
    public List<Appointment> findByDoctorId(Long DocId){
        return appointmentRepo.findByDoctor_DocId(DocId);
    }
    public List<Appointment> findByPatientId(Long patientId){
        return appointmentRepo.findByPatient_PatientId(patientId);
    }
    public void DeleteAppointment(Long id){
         appointmentRepo.deleteById(id);
    }
    public Appointment CreateAppointment(Appointment appointment) {
        if (appointment.getPatient() == null || appointment.getPatient().getPatientId() == null) {
            throw new CustomException("Patient ID must not be null");
        }
        if (appointment.getDoctor() == null || appointment.getDoctor().getDocId() == null) {
            throw new CustomException("Doctor ID must not be null");
        }

        Long patientId = appointment.getPatient().getPatientId();
        Long docId = appointment.getDoctor().getDocId();

        Patient patient = patientRepository.findById(patientId)
                .orElseThrow(() -> new CustomException("Patient not found"));
        Doctor doctor = doctorRepository.findById(docId)
                .orElseThrow(() -> new CustomException("Doctor not found"));

        appointment.setPatient(patient);
        appointment.setDoctor(doctor);
        return appointmentRepo.save(appointment);
    }



    public Appointment updateAppointment(Long appointmentId,Appointment appointment){
            Appointment appointmentById = getAppointmentById(appointmentId);
            if(appointmentById == null) return null;

            Long patientId = appointment.getPatient().getPatientId();
            Long doctorId = appointment.getDoctor().getDocId();

            // Fetch full Patient and Doctor from DB
            var patient = patientRepository.findById(patientId)
                    .orElseThrow(() -> new CustomException("Patient not found with id: " + patientId));
            var doctor = doctorRepository.findById(doctorId)
                    .orElseThrow(() -> new CustomException("Doctor not found with id: " + doctorId));

            appointmentById.setPatient(patient);
            appointmentById.setDoctor(doctor);
            appointmentById.setAppointmentTime(appointment.getAppointmentTime());
            appointmentById.setStatus(appointment.getStatus());

            return appointmentRepo.save(appointmentById);
        }

    }
