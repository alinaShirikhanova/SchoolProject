package ru.hogwarts.school.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.service.StudentService;

import java.util.ArrayList;
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


        return ResponseEntity.ok(studentService.createStudent(student));


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
    public ResponseEntity<Long> deleteStudent(@PathVariable Long studentId){
        studentService.deleteStudent(studentId);
        return ResponseEntity.ok(studentId);
    }

    @GetMapping("/find/{min}/{max}")
    public ResponseEntity<List<Student>> getStudent(@PathVariable("min") int min, @PathVariable("max") int max ){
        return new ResponseEntity<>(studentService.getStudentsByAgeBetween(min, max), HttpStatus.OK);
    }

    @GetMapping("/get/faculty/{studentId}")
    public ResponseEntity<Faculty> getStudentsFaculty(@PathVariable("studentId") Long studentId){
        return ResponseEntity.ok(studentService.getStudentFaculty(studentId));
    }

    @GetMapping("/amount")
    public int getAmountOfStudents() {
        return studentService.getAmountOfStudents();
    }

    @GetMapping("/average")
    public int getAverageAge() {
        return studentService.getAverageAge();
    }


}
