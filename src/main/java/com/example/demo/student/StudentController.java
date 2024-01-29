package com.example.demo.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "api/v1/student")
public class StudentController {

    private final StudentService studentService;


    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public List<Student> getStudents() {
        return studentService.getAllStudents();
    }

    @GetMapping(path = "{studentId}")
    public Student getStudentById(@PathVariable("studentId") Integer id) {
        return studentService.getStudentById(id);
    }

    @PostMapping
    public void addStudent(@RequestBody StudentRegistrationRequest studentRegistrationRequest) {
        studentService.addNewStudent(studentRegistrationRequest);
    }
//
//    @DeleteMapping(path = "{studentId}")
//    public void deleteStudent(@PathVariable("studentId") Long id) {
//        studentService.deleteStudent(id);
//    }

//    @PutMapping(path = "{studentId}")
//    public void updateStudent(@PathVariable("studentId") Long id, @RequestParam(required = false) String name, @RequestParam(required = false) String email) {
//        studentService.updateStudent(id, name, email);
//    }
}
