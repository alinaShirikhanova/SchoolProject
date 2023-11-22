package ru.hogwarts.school;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.*;

import ru.hogwarts.school.controller.StudentController;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import org.springframework.core.ParameterizedTypeReference;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class SchoolApplicationTestRestTemplate {
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
//        testStudent.setId(352L);
//        testStudent.setAge(100);
//        testStudent.setName("Эдуард");
//        testStudent.setFaculty(new Faculty(1L, "Гриффиндор", "Красный"));
//
//        this.restTemplate.delete("http://localhost:" + port + "/student/delete/", testStudent, Student.class);
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
    public void testGetFaculty() throws Exception {
        Faculty faculty = new Faculty();
        faculty.setColor("Красный");
        faculty.setName("Гриффиндор");
        faculty.setId(1L);


        ResponseEntity<Faculty> response = this.restTemplate.getForEntity("http://localhost:" + port + "/student/get/faculty/" + 252, Faculty.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());

        assertEquals(faculty, response.getBody());
    }


    @Test
    public void testFilterStudent() throws Exception {
        Student testStudent = new Student();
        testStudent.setId(102L);
        testStudent.setAge(23);
        testStudent.setName("Ваня");
        testStudent.setFaculty(new Faculty(1L, "Гриффиндор", "Красный"));


        List<Student> dtoList = this.exchangeAsList("http://localhost:" + port + "/student/filter/" + 23, new ParameterizedTypeReference<>() {});

        List<Student> expected = List.of(testStudent);


        assertEquals(expected, dtoList);
    }

    public <T> List<T> exchangeAsList(String uri, ParameterizedTypeReference<List<T>> responseType) {
        return restTemplate.exchange(uri, HttpMethod.GET, null, responseType).getBody();
    }


    @Test
    public void testFindByAgeBetween() throws Exception {
        Student testStudent = new Student();
        testStudent.setId(102L);
        testStudent.setAge(23);
        testStudent.setName("Ваня");
        Faculty faculty = new Faculty(1L, "Гриффиндор", "Красный");
        testStudent.setFaculty(faculty);



        Student testStudent2 = new Student();
        testStudent2.setId(2L);
        testStudent2.setAge(13);
        testStudent2.setName("Коля");
        testStudent2.setFaculty(faculty);


        List<Student> dtoList = this.exchangeAsList("http://localhost:" + port + "/student/find/" + 13 + "/" + 23, new ParameterizedTypeReference<>() {});
        List<Student> expected = List.of(testStudent2, testStudent);
        assertEquals(expected, dtoList);
    }
}