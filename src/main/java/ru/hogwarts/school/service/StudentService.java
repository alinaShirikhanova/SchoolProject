package ru.hogwarts.school.service;

import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Student;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
public class StudentService {

    private Map<Long, Student> students = new HashMap<>();

    public Student createStudent(Student student) {
        students.put(student.getId(), student);
        return student;
    }

    public Student getStudentById(Long studentId) {
        return students.get(studentId);
    }

    public Student updateStudent(Long studentId, Student student) {
        students.put(studentId, student);
        return student;
    }

    public Student deleteStudent(Long studentId) {
        return students.remove(studentId);
    }

    public List<Student> getStudentsByAge(int studentAge) {
        return students.values().stream()
                .filter(s -> s.getAge() == studentAge)
                .toList();
    }
}
