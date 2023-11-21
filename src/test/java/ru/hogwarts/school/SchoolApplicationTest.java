package ru.hogwarts.school;

import org.assertj.core.api.Assertions;
import org.assertj.core.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import ru.hogwarts.school.controller.StudentController;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class SchoolApplicationTest {
    @LocalServerPort
    private int port;

    @Autowired
    private StudentController studentController;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void contextLoads() {
        assertNotNull(studentController);
    }


    @Test
    public void testGetStudent() throws Exception {
        Student testStudent = new Student();
        testStudent.setId(1L);
        testStudent.setAge(12);
        testStudent.setName("Вася");
        testStudent.setFaculty(new Faculty(1L, "Гриффиндор", "Красный"));

        ResponseEntity<Student> response = this.restTemplate.getForEntity("http://localhost:" + port + "/student/get/" + 1, Student.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());

        assertEquals(testStudent, response.getBody());
    }


//    @Test
//    public void testDeleteStudent() throws Exception {
//        Student testStudent = new Student();
//        testStudent.setId(1L);
//        testStudent.setAge(12);
//        testStudent.setName("Вася");
//        testStudent.setFaculty(new Faculty(1L, "Гриффиндор", "Красный"));
//
//        this.restTemplate.delete("http://localhost:" + port + "/student/delete/",testStudent, Student.class);
//
//        assertEquals(HttpStatus.OK, response.getStatusCode());
//
//        assertEquals(testStudent, response.getBody());
//    }


    @Test
    public void testCreateStudent() throws Exception {
        Student testStudent = new Student();
        testStudent.setId(100L);
        testStudent.setAge(100);
        testStudent.setName("Эдуард");


        ResponseEntity<Student> response = this.restTemplate.postForEntity("http://localhost:" + port + "/student", testStudent, Student.class);

        assertThat(response).isNotNull();
    }

    @Test
    public void testUpdateStudent() throws Exception {
        Student testStudent = new Student();
        testStudent.setId(252L);
        testStudent.setAge(100);
        testStudent.setName("Эдуардддд");


        ResponseEntity<Student> response = this.restTemplate.postForEntity("http://localhost:" + port + "/student", testStudent, Student.class);

        assertThat(response).isNotNull();
    }


    @Test
    public void testFilterStudent() throws Exception {
        Student testStudent = new Student();
        testStudent.setId(102L);
        testStudent.setAge(23);
        testStudent.setName("Ваня");


        ResponseEntity<Student[]> response = this.restTemplate.getForEntity("http://localhost:" + port + "/filter/",  Student[].class );
        Student[] students = response.getBody();
        Student[] students2 = {testStudent};


        assertThat(students).isEqualTo(students2);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }





}