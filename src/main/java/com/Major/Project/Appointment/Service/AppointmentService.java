package com.Major.Project.Appointment.Service;

import com.Major.Project.Appointment.Entity.Appointment;
import com.Major.Project.Appointment.Repository.AppointmentRepo;
import com.Major.Project.Configuration.CustomException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppointmentService {

    @Autowired
    private AppointmentRepo appointmentRepo;

    public List<Appointment> getAppointments(){
        return appointmentRepo.findAll();
    }
    public Appointment getAppointmentById(Long appointmentId){
        return appointmentRepo.findById(appointmentId).orElseThrow(()-> new CustomException("id not find"));
    }
    public List<Appointment> findByDoctorId(Long DocId){
        return appointmentRepo.findByDoctorDocId(DocId);
    }
    public List<Appointment> findByPatientId(Long patientId){
        return appointmentRepo. findByPatientPatientId(patientId);
    }
    public Appointment CreateAppointment(Appointment appointment){
        return appointmentRepo.save(appointment);
    }
    public void DeleteAppointment(Long id){
         appointmentRepo.deleteById(id);
    }
    public Appointment updateAppointment(Long appointmentId,Appointment appointment){
        Appointment appointmentById = getAppointmentById(appointmentId);
        if(appointmentById==null) return null;
        appointmentById.setPatientId(appointment.getPatientId());
        appointmentById.setAppointmentTime(appointment.getAppointmentTime());
        appointmentById.setDoctorId(appointment.getDoctorId());
        appointmentById.setStatus(appointment.getStatus());
        return appointmentRepo.save(appointmentById);

    }
}
