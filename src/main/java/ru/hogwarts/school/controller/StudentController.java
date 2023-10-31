package ru.hogwarts.school.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.service.StudentService;

import java.util.List;

@RequestMapping("/student")
@RestController
public class StudentController {
    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }


    @PostMapping
    public ResponseEntity<Student> createUser(@RequestBody Student student) {
        Student createdStudent = studentService.createStudent(student);
        return ResponseEntity.ok(createdStudent);
    }

    @GetMapping("filter/{studentAge}")
    public ResponseEntity<List<Student>> getStudentsByAge(@PathVariable int studentAge){
        List<Student> students = studentService.getStudentsByAge(studentAge);
        if (students.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(students);
    }

    @GetMapping("get/{studentId}")
    public ResponseEntity<Student> getStudent(@PathVariable Long studentId){
        Student student = studentService.getStudentById(studentId);
        if (student == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(student);
    }

    @PutMapping
    public ResponseEntity<Student> updateStudent(@RequestBody  Student student){
        Student updatedStudent = studentService.updateStudent(student.getId(), student);
    if (updatedStudent == null)
        return ResponseEntity.notFound().build();
    return ResponseEntity.ok(student);
    }

    @DeleteMapping("/delete/{studentId}")
    public ResponseEntity<Student> deleteStudent(@PathVariable Long studentId){
        Student deletedStudent = studentService.deleteStudent(studentId);
        if (deletedStudent == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(deletedStudent);
    }

}
