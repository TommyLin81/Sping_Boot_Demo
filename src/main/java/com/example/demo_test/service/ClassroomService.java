package com.example.demo_test.service;

import com.example.demo_test.bean.Classroom;

import java.util.List;

public interface ClassroomService {
    List<Classroom> getClassrooms();
    Classroom getClassroomById(Integer classroomId);
    void clearGetClassrooms();
    void clearGetClassroomsById();
}
