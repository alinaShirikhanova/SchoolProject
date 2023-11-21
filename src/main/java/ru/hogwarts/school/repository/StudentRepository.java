package ru.hogwarts.school.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {
    Student getStudentById(Long id);

//    void update(Student student);

    List<Student> findAllByAge(int studentAge);
    List<String> findByNameIsStartingWith(String str);

    List<Student> findByAgeBetween(int min, int max);
    @Query(value = "SELECT COUNT(*) FROM student", nativeQuery = true)
    int getAmountOfStudents();


    @Query(value = "SELECT AVG(age) from student", nativeQuery = true)
    int getAverageAge();
}
