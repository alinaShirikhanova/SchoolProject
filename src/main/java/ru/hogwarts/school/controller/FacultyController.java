package ru.hogwarts.school.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.FacultyRepository;
import ru.hogwarts.school.service.FacultyService;


import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/faculty")
public class FacultyController {
    private final FacultyService facultyService;

    public FacultyController(FacultyService facultyService) {
        this.facultyService = facultyService;
    }

    @PostMapping
    public ResponseEntity<Faculty> createFaculty(@RequestBody Faculty faculty) {
        facultyService.createFaculty(faculty);

        return new ResponseEntity<>(HttpStatus.OK);


    }

    @GetMapping("filter/{facultyColor}")
    public ResponseEntity<List<Faculty>> getStudentsByAge(@PathVariable("facultyColor") String facultyColor){
        List<Faculty> faculties = facultyService.getFacultiesByColor(facultyColor);
        if (faculties.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(faculties);
    }

    @GetMapping("get/{facultyId}")
    public ResponseEntity<Faculty> getStudent(@PathVariable("facultyId") Long facultyId){
        Faculty faculty = facultyService.getFacultyById(facultyId);
        if (faculty == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(faculty);
    }

    @PutMapping
    public ResponseEntity<Faculty> updateFaculty(@RequestBody Faculty faculty){
        Faculty updatedFaculty = facultyService.updateFaculty(faculty.getId(), faculty);
        if (updatedFaculty == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(faculty);
    }

    @DeleteMapping("/delete/{facultyId}")
    public ResponseEntity<Long> deleteStudent(@PathVariable("facultyId") Long facultyId){
        facultyService.deleteFaculty(facultyId);
        return ResponseEntity.ok(facultyId);
    }

    @GetMapping("/find/{name}/{color}")
    public ResponseEntity<List<Faculty>> getStudent(@PathVariable("name") String name, @PathVariable("color") String color ){
        return ResponseEntity.ok(facultyService.getFacultiesByNameOrColor(name, color));
    }


    @GetMapping("/get/students/{facultyId}")
    public ResponseEntity<List<Student>> getStudents(@PathVariable("facultyId") long facultyId){
        return ResponseEntity.ok(facultyService.getStudents(facultyId));
    }
 

}
