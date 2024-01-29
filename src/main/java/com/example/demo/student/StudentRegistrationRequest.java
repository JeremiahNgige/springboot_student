package com.example.demo.student;

import java.time.LocalDate;

public record StudentRegistrationRequest(String name,
                                         String email,
                                         LocalDate dob) {
}
