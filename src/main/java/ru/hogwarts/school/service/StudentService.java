package ru.hogwarts.school.service;

import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.FacultyRepository;
import ru.hogwarts.school.repository.StudentRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
public class StudentService {
    private final StudentRepository studentRepository;
    private Map<Long, Student> students = new HashMap<>();

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }


    public Student createStudent(Student student) {
        return studentRepository.save(student);
    }


    public Student getStudentById(Long studentId) {
        return studentRepository.getStudentById(studentId);
    }

    public Student updateStudent( Student student) {
        return studentRepository.save(student);
    }

    public void deleteStudent(Long studentId) {
        studentRepository.deleteById(studentId);
    }

    public List<Student> getStudentsByAge(int studentAge) {
        return studentRepository.findAllByAge(studentAge);
    }

    public List<Student> getStudentsByAgeBetween(int min, int max){
        return studentRepository.findByAgeBetween(min, max);
    }

    public Faculty getStudentFaculty(Long studentId) {
        return studentRepository.getStudentById(studentId).getFaculty();
    }
    public int getAmountOfStudents() {
        return studentRepository.getAmountOfStudents();
    }



    public int getAverageAge() {

        return studentRepository.getAverageAge();
    }
    public List<Student> getFiveLastStudents() {
        return studentRepository.getFiveLastStudents();
    }
}
