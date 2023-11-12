package ru.hogwarts.school.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;

import java.awt.print.Pageable;
import java.util.List;

@Repository
public interface FacultyRepository extends JpaRepository<Faculty, Long> {
    Faculty getFacultyById(long id);
    List<Faculty> findByNameOrColorIgnoreCase(String name, String color);

    List<Faculty> findAllByColor(String facultyColor);
    @Query(value = "SELECT * from student as s where s.faculty_id = :id", nativeQuery = true)
    List<Student> getStudents(Long id);
}
