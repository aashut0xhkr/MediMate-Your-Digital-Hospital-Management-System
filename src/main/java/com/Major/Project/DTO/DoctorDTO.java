package com.Major.Project.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class DoctorDTO {
    private Long docId;

    @NotBlank(message = "Doctor name is required")
    private String name;

    @NotBlank(message = "Specialisation is required")
    private String specialisation;

    @NotBlank(message = "Contact number is required")
    private String contact;

    @NotBlank(message = "Email is required")
    @Email(message = "Please provide a valid email address")
    private String email;
}