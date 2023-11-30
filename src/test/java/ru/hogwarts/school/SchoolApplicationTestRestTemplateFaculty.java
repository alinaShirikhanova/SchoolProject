package ru.hogwarts.school;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import ru.hogwarts.school.controller.FacultyController;
import ru.hogwarts.school.controller.StudentController;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class SchoolApplicationTestRestTemplateFaculty {
    @LocalServerPort
    private int port;

    @Autowired
    private FacultyController facultyController;

    @Autowired
    private TestRestTemplate restTemplate;
    Faculty faculty;
    @BeforeEach
    public void setUp(){
        faculty = new Faculty();

        faculty.setName("Гриффиндор");
        faculty.setColor("Красный");
    }

    @Test
    void contextLoads() {
        assertNotNull(facultyController);
    }


    @Test
    public void testGetStudent() throws Exception {
        faculty.setId(1L);


        ResponseEntity<Faculty> response = this.restTemplate.getForEntity("http://localhost:" + port + "/faculty/get/1", Faculty.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());

        assertEquals(faculty, response.getBody());

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
    public void testCreateFaculty() throws Exception {
        ResponseEntity<Faculty> response = this.restTemplate.postForEntity("http://localhost:" + port + "/faculty", faculty, Faculty.class);

        assertThat(response).isNotNull();
        assertEquals(HttpStatus.OK, response.getStatusCode());


        assertEquals(faculty.getName(), response.getBody().getName());
        assertEquals(faculty.getColor(), response.getBody().getColor());
    }

    @Test
    public void testUpdateFaculty() throws Exception {
        faculty.setId(152L);
        faculty.setName("Пуфф");
        ResponseEntity<Faculty> response = this.restTemplate.postForEntity("http://localhost:" + port + "/faculty", faculty, Faculty.class);

        assertThat(response).isNotNull();
        assertEquals(HttpStatus.OK, response.getStatusCode());


        assertEquals(faculty.getName(), response.getBody().getName());
        assertEquals(faculty.getColor(), response.getBody().getColor());
    }


    @Test
    public void testGetFacultyById() throws Exception {
        faculty.setId(1L);
        ResponseEntity<Faculty> response = this.restTemplate.getForEntity("http://localhost:" + port + "/faculty/get/1", Faculty.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());

        assertEquals(faculty, response.getBody());
    }


    @Test
    public void testFilterFaculty() throws Exception {

        faculty.setColor("Синий");
        faculty.setId(3L);
        ResponseEntity<List<Faculty>> facultyRs =
                restTemplate.exchange("http://localhost:" + port + "/faculty/filter/Синий",
                        HttpMethod.GET, null, new ParameterizedTypeReference<>() {
                        });

        List<Faculty> expected = List.of(faculty);


        assertEquals(expected, facultyRs.getBody());
    }



    @Test
    public void testFilterStudentParam() throws Exception {

        ResponseEntity<List<Student>> studentRs =
                restTemplate.exchange("http://localhost:" + port + "/student/filterParam?studentAge=23",
                        HttpMethod.GET, null, new ParameterizedTypeReference<>() {
                        });
        List<Student> studentRsBody = studentRs.getBody();
    }

    @Test
    public void testFindByName() throws Exception {
        faculty.setId(152L);
        faculty.setName("Пуфф");
        ResponseEntity<List<Faculty>> facultiesRs = restTemplate.exchange("http://localhost:" + port + "/faculty/find?name=Пуфф", HttpMethod.GET, null,  new ParameterizedTypeReference<>() {});
        List<Faculty> expected = List.of(faculty);
        assertEquals(expected, facultiesRs.getBody());
    }

    @Test
    public void testFindByColor() throws Exception {
        faculty.setId(3L);
        faculty.setColor("Синий");
        ResponseEntity<List<Faculty>> facultiesRs = restTemplate.exchange("http://localhost:" + port + "/faculty/find?color=Синий", HttpMethod.GET, null,  new ParameterizedTypeReference<>() {});
        List<Faculty> expected = List.of(faculty);
        assertEquals(expected, facultiesRs.getBody());
    }



}