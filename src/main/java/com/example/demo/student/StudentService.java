package com.example.demo.student;

import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.DuplicateResourceException;
import com.example.demo.exception.ResourceNotFound;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    private final StudentDao studentDao;


    public StudentService(@Qualifier("jpa") StudentDao studentDao) {
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


    public void addNewStudent(StudentRegistrationRequest studentRegistrationRequest) {

        //check if email exists
        if (studentDao.existStudentWithEmail(studentRegistrationRequest.email())) {
            throw new DuplicateResourceException("Email taken");
        }

        // add student
        Student student = new Student(
                studentRegistrationRequest.name(),
                studentRegistrationRequest.email(),
                studentRegistrationRequest.dob()
        );
        studentDao.insertStudent(student);
    }

    public void deleteStudent(Integer studentId) {
        boolean exists = studentDao.existsStudentWithId(studentId);
        if (!exists) {
            throw new ResourceNotFound("student with" + studentId + " does not exits");
        }
        studentDao.deleteStudentById(studentId);
    }

    public void updateStudent(Integer id, StudentUpdateRequest studentUpdateRequest) {

        Student student = getStudentById(id);
        boolean changes = false;

        if (studentUpdateRequest.name() != null
                && !studentUpdateRequest.name().equals(student.getName())) {
            student.setName(studentUpdateRequest.name());
            changes = true;
        }

        if (studentUpdateRequest.age() != null
                && !studentUpdateRequest.age().equals(student.getAge())) {
            student.setAge(studentUpdateRequest.age());
            changes = true;
        }

        if (studentUpdateRequest.email() != null
                && !studentUpdateRequest.email().equals(student.getEmail())) {
            if (studentDao.existStudentWithEmail(studentUpdateRequest.email())) {
                throw new DuplicateResourceException("Email Already taken");
            }
            student.setEmail(studentUpdateRequest.email());
            changes = true;
        }

        if (!changes) {
            throw new BadRequestException("No Changes were found");
        }
        studentDao.updateStudent(student);
    }

}
