package com.example.demo.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddStudentRequestDto {
    @Size(min = 4, max = 50,message = "neame should be between 3 to 50 characters")
    private String firstName;
    private String lastName;

    @Email
    @NotBlank(message = "Email is required")
    private String email;

    @NotBlank(message = "phone number is required")
    private String phoneNumber;
}