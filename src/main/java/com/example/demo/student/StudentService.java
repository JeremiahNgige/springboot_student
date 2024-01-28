package com.example.demo.student;

import com.example.demo.exception.ResourceNotFound;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class StudentService {

    private final StudentDao studentDao;


    public StudentService(StudentDao studentDao) {
        this.studentDao = studentDao;
    }

    public List<Student> getAllStudents() {
        return studentDao.selectAllStudents();
    }

    //Business Logic Level
    public Student getStudentById(Integer Id) {
        return studentDao.selectStudentById(Id).orElseThrow(
                (() -> new ResourceNotFound("Student with id [%id] not found")));
    }


    public void addNewStudent(Student student) {
//        Optional<Student> studentOptional = studentRepository.finaStudentByEmail(student.getEmail());
//        if (studentOptional.isPresent()) {
//            throw new ResourceNotFound("email taken");
//        }
//        studentRepository.save(student);
    }

    public void deleteStudent(Long id) {
//        boolean exists = studentRepository.existsById(id);
//        if (!exists) {
//            throw new ResourceNotFound("student with" + id + " does not exits");
//        }
//        studentRepository.deleteById(id);
    }

//    @Transactional
//    public void updateStudent(Long id, String name, String email) {
//        Student student = studentDao.findById(id).orElseThrow(() -> new ResourceNotFound(
//                "student with id " + id + " does not exits"));
//
//        if (name != null && !name.isEmpty() && !Objects.equals(student.getName(), name)) {
//            student.setName(name);
//        }
//
//        if (email != null && !email.isEmpty() && !Objects.equals(student.getEmail(), email)) {
//            Optional<Student> studentOptional = studentRepository.finaStudentByEmail(email);
//            if (studentOptional.isPresent()) {
//                throw new ResourceNotFound("email taken");
//            }
//            student.setEmail(email);
//        }
//    }

}
