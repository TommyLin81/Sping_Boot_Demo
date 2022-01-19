package com.example.demo_test.controller;

import com.example.demo_test.bean.Student;
import com.example.demo_test.dao.StudentRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Transactional;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class StudentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private StudentRepository studentRepo;

    @Test
    void getStudents() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/v1/students");

        mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[0].id", equalTo(1)))
                .andExpect(jsonPath("$[0].attendances[0].id", equalTo(1)));
    }

    @Test
    void getStudent() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/v1/students/1");

        mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", equalTo(1)))
                .andExpect(jsonPath("$.name", equalTo("Amy")));
    }

    @Test
    void getStudentNotFound() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/v1/students/6");

        mockMvc.perform(requestBuilder)
                .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void createStudent() throws Exception {
        String requestBody = "";
        ObjectMapper objectMapper = new ObjectMapper();
        Student student = new Student();

        student.setName("Test");
        student.setGraduate(true);

        requestBody = objectMapper.writeValueAsString(student);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/v1/students")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody);

        mockMvc.perform(requestBuilder)
                .andExpect(status().isCreated());

        Student createdStudent = studentRepo.findById(5).orElse(null);
        assert createdStudent != null;
        assertEquals("Test", createdStudent.getName());
        assertEquals(true, createdStudent.getGraduate());
    }

    @Test
    @Transactional
    void updateStudent() throws Exception {
        String requestBody = "";
        ObjectMapper objectMapper = new ObjectMapper();
        Student student = new Student();

        student.setName("Tommy");
        student.setGraduate(false);

        requestBody = objectMapper.writeValueAsString(student);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.put("/api/v1/students/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody);

        mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", equalTo(1)))
                .andExpect(jsonPath("$.name", equalTo("Tommy")))
                .andExpect(jsonPath("$.graduate", equalTo(false)));
    }

    @Test
    @Transactional
    void deleteStudent() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/api/v1/students/1");

        mockMvc.perform(requestBuilder)
                .andExpect(status().isOk());

        Student deletedStudent = studentRepo.findById(1).orElse(null);
        assert deletedStudent == null;
    }
}