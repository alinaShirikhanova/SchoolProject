package ru.hogwarts.school.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {
    Student getStudentById(long id);

//    void update(Student student);

    List<Student> findAllByAge(int studentAge);
    List<String> findByNameIsStartingWith(String str);

    List<Student> findByAgeBetween(int min, int max);




}
