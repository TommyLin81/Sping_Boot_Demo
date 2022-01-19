package com.example.demo_test.service.impl;

import com.example.demo_test.bean.Student;
import com.example.demo_test.dao.StudentRepository;
import com.example.demo_test.exception.custom.ApiNotFoundException;
import com.example.demo_test.service.StudentService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class StudentServiceImplTest {

    @Autowired
    private StudentService studentService;

    @MockBean
    private StudentRepository studentRepo;

    @Test
    void getStudents() {
        List<Student> mockList = new ArrayList<>();
        Student mockStudent = new Student();

        mockStudent.setName("Test");
        mockStudent.setGraduate(true);

        mockList.add(mockStudent);

        Mockito.when(studentRepo.findAll())
                .thenReturn(mockList);

        List<Student> students = studentService.getStudents();
        assertNotNull(students);
        assertEquals(mockList, students);
    }

    @Test
    void getStudentById() {
        Student mockStudent = new Student();
        mockStudent.setName("Test");
        mockStudent.setGraduate(true);

        Mockito.when(studentRepo.findById(1))
                .thenReturn(Optional.of(mockStudent));

        Student student = studentService.getStudentById(1);

        assertNotNull(student);
        assertEquals(mockStudent, student);
    }

    @Test
    void createStudent() {
        Student mockStudent = new Student();
        studentService.createStudent(mockStudent);

        Mockito.verify(studentRepo, Mockito.times(1)).save(mockStudent);
    }

    @Test
    void updateStudentById() throws ApiNotFoundException {
        Student mockStudent = new Student();
        mockStudent.setName("Test");
        mockStudent.setGraduate(true);

        Mockito.when(studentRepo.findById(1))
                .thenReturn(Optional.of(new Student()));

        Student student = studentService.updateStudentById(1, mockStudent);

        assertEquals(mockStudent.getName(), student.getName());
        assertEquals(mockStudent.getGraduate(), student.getGraduate());
    }

    @Test
    void updateStudentByIdThrowNotFound() {
        Mockito.when(studentRepo.findById(1))
                .thenReturn(Optional.empty());

        Exception exception = assertThrows(
                ApiNotFoundException.class,
                () -> studentService.updateStudentById(1, new Student())
        );

        assertEquals("NOT_FOUND", exception.getMessage());
    }

    @Test
    void deleteById() throws ApiNotFoundException {
        Mockito.when(studentRepo.findById(1))
                .thenReturn(Optional.of(new Student()));

        studentService.deleteById(1);

        Mockito.verify(studentRepo, Mockito.times(1)).findById(1);
        Mockito.verify(studentRepo, Mockito.times(1)).deleteById(1);
    }

    @Test
    void deleteByIdThrowNotFound() {
        Mockito.when(studentRepo.findById(1))
                .thenReturn(Optional.empty());

        Exception exception = assertThrows(
                ApiNotFoundException.class,
                () -> studentService.deleteById(1)
        );

        assertEquals("NOT_FOUND", exception.getMessage());
    }
}