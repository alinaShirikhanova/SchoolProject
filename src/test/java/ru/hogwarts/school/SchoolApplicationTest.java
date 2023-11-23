package ru.hogwarts.school;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.hogwarts.school.controller.StudentController;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.StudentRepository;
import ru.hogwarts.school.service.AvatarService;
import ru.hogwarts.school.service.FacultyService;
import ru.hogwarts.school.service.StudentService;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Optional;


import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
public class SchoolApplicationTest {


    @Autowired
    private MockMvc mockMvc;
    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    private StudentRepository userRepository;

    @MockBean
    private AvatarService avatarService;
    @MockBean
    private FacultyService facultyService;
    @SpyBean
    private StudentService userService;

    @InjectMocks
    private StudentController userController;

    @Test
    public void saveStudentTest() throws Exception {
        Long id = 1L;
        String name = "Bob";
        int age = 23;


        JSONObject userObject = new JSONObject();
        userObject.put("name", name);
        userObject.put("age", age);


        Student student = new Student();
        student.setId(id);
        student.setName(name);
        student.setAge(age);




        when(userRepository.save(any(Student.class))).thenReturn(student);


        mockMvc.perform(MockMvcRequestBuilders
                        .post("/student") //send
                        .content(userObject.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()) //receive
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.name").value(name));

    }


    @Test
    public void getStudentByIdTest() throws Exception {
        Long id = 1L;
        String name = "Bob";
        int age = 23;


        Student student = new Student();
        student.setId(id);
        student.setName(name);
        student.setAge(age);


        when(userRepository.save(any(Student.class))).thenReturn(student);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/student/1")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()) //receive
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.name").value(name));
    }

    @Test
    public void updateStudent() throws Exception {
        Long id = 1L;
        String name = "Bob";
        int age = 23;


        JSONObject userObject = new JSONObject();
        userObject.put("name", name);
        userObject.put("age", age);


        Student student = new Student();
        student.setId(id);
        student.setName(name);
        student.setAge(age);


        when(userRepository.save(any(Student.class))).thenReturn(student);

        mockMvc.perform(MockMvcRequestBuilders
                        .put("/student") //send
                        .content(userObject.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()) //receive
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.name").value(name));
    }


    @Test
    public void deleteStudentById() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/student/delete/1") //send
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()); //receive;
    }


//    @Test
//    public void getStudentsByAge() throws Exception {
//        Long id = 252L;
//        String name = "Эдуардддд";
//        int age = 100;
//
//        Long id2 = 302L;
//        String name2 = "Эдуардддд";
//        int age2 = 100;
//
//        Long id3 = 352L;
//        String name3 = "Эдуардддд";
//        int age3 = 100;
//
//
//        Student student = new Student();
//        student.setId(id);
//        student.setName(name);
//        student.setAge(age);
//
//        Student student2 = new Student();
//        student.setId(id2);
//        student.setName(name2);
//        student.setAge(age2);
//
//        Student student3 = new Student();
//        student.setId(id3);
//        student.setName(name3);
//        student.setAge(age3);
//
//
//        when(userRepository.findAllByAge(any(Integer.class))).thenReturn(List.of(student, student2, student3));
//
//
//        mockMvc.perform(MockMvcRequestBuilders
//                        .get("/student/filter/100")
//                        .accept(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk()) //receive
//                .andExpect(jsonPath("$.*", hasSize(3)));
////                .andExpect(jsonPath("$.name").value(name));
//    }

        @Test
    public void getStudentsByAge() throws Exception {
        Long id = 252L;
        String name = "Alex";
        int age = 100;

        Long id2 = 302L;
        String name2 = "Alex";
        int age2 = 100;

        Long id3 = 352L;
        String name3 = "Alex";
        int age3 = 100;


        Student student = new Student();
        student.setId(id);
        student.setName(name);
        student.setAge(age);

        Student student2 = new Student();
        student2.setId(id2);
        student2.setName(name2);
        student2.setAge(age2);

        Student student3 = new Student();
        student3.setId(id3);
        student3.setName(name3);
        student3 .setAge(age3);


        when(userRepository.findAllByAge(any(Integer.class))).thenReturn(List.of(student, student2, student3));


            MvcResult result = mockMvc.perform(MockMvcRequestBuilders
                        .get("/student/filter/100")
                        .accept(MediaType.APPLICATION_JSON)).andReturn();

            ObjectMapper mapper = new ObjectMapper();

            List<Student> actual = mapper.readValue(result.getResponse().getContentAsString(), new TypeReference<>() {});
            assertThat(actual).isEqualTo(List.of(student, student2, student3));
    }

}
