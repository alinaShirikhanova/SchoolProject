package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.FacultyRepository;

import java.util.List;
import java.util.Set;

@Service
public class FacultyService {
    private final FacultyRepository facultyRepository;

    public FacultyService(FacultyRepository facultyRepository) {
        this.facultyRepository = facultyRepository;
    }


    public void createFaculty(Faculty faculty) {
        facultyRepository.save(faculty);
    }


    public Faculty getFacultyById(Long facultyId) {
        return facultyRepository.getFacultyById(facultyId);
    }

    public Faculty updateFaculty(Long facultyId, Faculty faculty) {
        facultyRepository.save(faculty);
        return faculty;
    }

    public void deleteFaculty(Long facultyId) {
        facultyRepository.deleteById(facultyId);

    }

    public List<Faculty> getFacultiesByColor(String facultyColor) {
        return facultyRepository.findAllByColor(facultyColor);
    }

    public List<Faculty> getFacultiesByNameOrColor(String name, String color){
        return facultyRepository.findByNameIgnoreCaseOrColorIgnoreCase(name, color);
    }


    public Set<Student> getStudents(Long facultyId) {
        return facultyRepository.findById(facultyId).get().getStudents();
    }
}
