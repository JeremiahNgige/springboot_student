package com.example.demo.student;

import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository("list")
public class StudentDataAccessService implements StudentDao {

    //db
    private static final List<Student> students;

    static {
        students = new ArrayList<>();

        Student Mariam = new Student("Mariam", "mariam@gmail.com", LocalDate.of(2020, Month.APRIL, 1));
        Student Alex = new Student("Alex", "alex@gmail.com", LocalDate.of(2000, Month.DECEMBER, 3));
        students.add(Mariam);
        students.add(Alex);
    }

    @Override
    public List<Student> selectAllStudents() {
        return students;
    }


    @Override
    public Optional<Student> selectStudentById(Integer Id) {
        return students.stream().filter(c -> c.getId().equals(Id)).findFirst();
    }

    @Override
    public void insertStudent(Student student) {
        students.add(student);
    }

    @Override
    public boolean existStudentWithEmail(String email) {
        return students.stream().anyMatch(c -> c.getEmail().equals(email));
    }

    @Override
    public boolean existsStudentWithId(Integer id) {
        return students.stream().anyMatch(c -> c.getId().equals(id));
    }

    @Override
    public void deleteStudentById(Integer studentId) {
        students.stream()
                .filter(c -> c.getId()
                        .equals(studentId))
                .findFirst().ifPresent(students::remove);
    }

    @Override
    public void updateStudent(Student update) {
        students.add(update);
    }
}
