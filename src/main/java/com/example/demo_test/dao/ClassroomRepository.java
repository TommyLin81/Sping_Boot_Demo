package com.example.demo_test.dao;

import com.example.demo_test.bean.Classroom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClassroomRepository extends JpaRepository<Classroom, Integer> {

}
