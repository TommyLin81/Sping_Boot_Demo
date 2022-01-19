package com.example.demo_test.service;

import com.example.demo_test.bean.Student;
import com.example.demo_test.exception.custom.ApiNotFoundException;

import java.util.List;

public interface StudentService {
    List<Student> getStudents();
    Student getStudentById(Integer studentId);
    void createStudent(Student student);
    Student updateStudentById(Integer studentId, Student student) throws ApiNotFoundException;
    void deleteById(Integer studentId) throws ApiNotFoundException;
}
