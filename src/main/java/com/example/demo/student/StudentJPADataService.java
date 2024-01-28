package com.example.demo.student;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


public class StudentJPADataService implements StudentDao {

    public final StudentRepository studentRepository;

    public StudentJPADataService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public List<Student> selectAllStudents() {
        return studentRepository.findAll();
    }

    @Override
    public Optional<Student> selectStudentById(Integer Id) {
        return Optional.empty();
    }


    @Override
    public void insertStudent(Student student) {
        studentRepository.save(student);
    }
}
