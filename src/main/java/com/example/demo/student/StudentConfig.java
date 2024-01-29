package com.example.demo.student;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Configuration
public class StudentConfig {
    @Bean
    CommandLineRunner commandLineRunner(StudentRepository studentRepository) {
        return args -> {
            Student Mariam = new Student("Mariam", "mariam@gmail.com", LocalDate.of(2020, Month.APRIL, 1));
            Student Alex = new Student("Alex", "alex@gmail.com", LocalDate.of(2000, Month.DECEMBER, 3));
            studentRepository.saveAll(List.of(Mariam, Alex));
        };
    }
}
