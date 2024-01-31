package com.example.demo.student;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository("jpa")
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
        return studentRepository.findById(Long.valueOf(Id));
    }


    @Override
    public void insertStudent(Student student) {
        studentRepository.save(student);
    }

    @Override
    public boolean existStudentWithEmail(String email) {
        return studentRepository.existsStudentByEmail(email);
    }

    @Override
    public boolean existsStudentWithId(Integer id) {
        return studentRepository.existsById(Long.valueOf(id));
    }

    @Override
    public void deleteStudentById(Integer studentId) {
        studentRepository.deleteById(Long.valueOf(studentId));
    }

    @Override
    public void updateStudent(Student update) {
        studentRepository.save(update);
    }
}
