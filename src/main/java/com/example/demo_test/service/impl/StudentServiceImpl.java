package com.example.demo_test.service.impl;

import com.example.demo_test.bean.Student;
import com.example.demo_test.dao.StudentRepository;
import com.example.demo_test.exception.custom.ApiNotFoundException;
import com.example.demo_test.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

import static java.util.Objects.isNull;

@Component
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepository studentRepo;

    @Override
    public List<Student> getStudents() {
        return studentRepo.findAll();
    }

    @Override
    public Student getStudentById(Integer studentId) {
        return studentRepo.findById(studentId).orElse(null);
    }

    @Override
    public void createStudent(Student student) {
        studentRepo.save(student);
    }

    @Override
    public Student updateStudentById(Integer studentId, Student student) throws ApiNotFoundException {
        Student specifiedStudent = studentRepo.findById(studentId).orElseThrow(ApiNotFoundException::new);

        if (!isNull(student.getName())) {
            specifiedStudent.setName(student.getName());
        }

        if (!isNull(student.getGraduate())) {
            specifiedStudent.setGraduate(student.getGraduate());
        }

        studentRepo.saveAndFlush(specifiedStudent);

        return specifiedStudent;
    }

    @Override
    public void deleteById(Integer studentId) throws ApiNotFoundException {
        studentRepo.findById(studentId).orElseThrow(ApiNotFoundException::new);
        studentRepo.deleteById(studentId);
    }
}
