package com.example.demo.student;

import com.example.demo.exception.ResourceNotFound;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getStudents() {
        return studentRepository.findAll();
    }

    public Optional<Student> getStudentsById(Long id) {
        return studentRepository.findById(id);
    }

    public void addNewStudent(Student student) {
        Optional<Student> studentOptional = studentRepository.finaStudentByEmail(student.getEmail());
        if (studentOptional.isPresent()) {
            throw new ResourceNotFound("email taken");
        }
        studentRepository.save(student);
    }

    public void deleteStudent(Long id) {
        boolean exists = studentRepository.existsById(id);
        if (!exists) {
            throw new ResourceNotFound("student with" + id + " does not exits");
        }
        studentRepository.deleteById(id);
    }

    @Transactional
    public void updateStudent(Long id, String name, String email) {
        Student student = studentRepository.findById(id).orElseThrow(() -> new ResourceNotFound(
                "student with id " + id + " does not exits"));

        if (name != null && !name.isEmpty() && !Objects.equals(student.getName(), name)) {
            student.setName(name);
        }

        if (email != null && !email.isEmpty() && !Objects.equals(student.getEmail(), email)) {
            Optional<Student> studentOptional = studentRepository.finaStudentByEmail(email);
            if (studentOptional.isPresent()) {
                throw new ResourceNotFound("email taken");
            }
            student.setEmail(email);
        }
    }
}
