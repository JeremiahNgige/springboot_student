package com.example.demo.student;

import java.util.List;
import java.util.Optional;

public interface StudentDao {
    List<Student> selectAllStudents();

    Optional<Student> selectStudentById(Integer Id);

    void insertStudent(Student student);

    boolean existStudentWithEmail(String email);

    boolean existsStudentWithId(Integer id);

    void deleteStudentById(Integer studentId);
    void updateStudent(Student update);
}
