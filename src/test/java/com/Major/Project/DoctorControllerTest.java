package com.Major.Project;

import com.Major.Project.Doctor.Controller.DoctorController;
import com.Major.Project.Doctor.Entity.Doctor;
import com.Major.Project.Doctor.Service.DoctorService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(DoctorController.class)
public class DoctorControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private DoctorService doctorService;

    @Autowired
    private ObjectMapper objectMapper;

    private Doctor sampleDoctor() {
        return new Doctor(1L, "Dr. John", "Cardiology", "1234567890", "dr.john@example.com", null);
    }

    @Test
    void testGetAllDoctors() throws Exception {
        Mockito.when(doctorService.getDoctorList()).thenReturn(Collections.singletonList(sampleDoctor()));

        mockMvc.perform(get("/HMS/Doctor"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Dr. John"));
    }

    @Test
    void testGetDoctorById() throws Exception {
        Mockito.when(doctorService.getDoctorListById(1L)).thenReturn(sampleDoctor());

        mockMvc.perform(get("/HMS/Doctor/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.specialisation").value("Cardiology"));
    }

    @Test
    void testCreateDoctor() throws Exception {
        Doctor doctor = sampleDoctor();
        Mockito.when(doctorService.createDoctor(any(Doctor.class))).thenReturn(doctor);

        mockMvc.perform(post("/HMS/Doctor")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(doctor)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.email").value("dr.john@example.com"));
    }

    @Test
    void testUpdateDoctor_Success() throws Exception {
        Doctor updatedDoctor = sampleDoctor();
        updatedDoctor.setName("Dr. John Updated");

        Mockito.when(doctorService.updateDoctor(eq(1L), any(Doctor.class))).thenReturn(updatedDoctor);

        mockMvc.perform(put("/HMS/Doctor/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updatedDoctor)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Dr. John Updated"));
    }

    @Test
    void testUpdateDoctor_NotFound() throws Exception {
        Mockito.when(doctorService.updateDoctor(eq(1L), any(Doctor.class))).thenReturn(null);

        mockMvc.perform(put("/HMS/Doctor/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(sampleDoctor())))
                .andExpect(status().isNotFound());
    }

    @Test
    void testDeleteDoctor() throws Exception {
        mockMvc.perform(delete("/HMS/Doctor/1"))
                .andExpect(status().isNoContent());
    }
}
