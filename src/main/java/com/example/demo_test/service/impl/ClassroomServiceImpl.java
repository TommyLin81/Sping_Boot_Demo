package com.example.demo_test.service.impl;

import com.example.demo_test.bean.Classroom;
import com.example.demo_test.dao.ClassroomRepository;
import com.example.demo_test.service.ClassroomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@CacheConfig(cacheNames = "classroomService")
public class ClassroomServiceImpl implements ClassroomService {

    @Autowired
    private ClassroomRepository classroomRepo;

    @Override
    @Cacheable(cacheNames = "getClassrooms", keyGenerator = "wiselyKeyGenerator")
    public List<Classroom> getClassrooms() {
        return classroomRepo.findAll();
    }

    @Override
    @Cacheable(cacheNames = "getClassroomById", keyGenerator = "wiselyKeyGenerator")
    public Classroom getClassroomById(Integer classroomId) {
        return classroomRepo.findById(classroomId).orElse(null);
    }

    @Override
    @CacheEvict(value = "getClassrooms", allEntries = true)
    public void clearGetClassrooms() {

    }

    @Override
    @CacheEvict(value = "getClassroomById", allEntries = true)
    public void clearGetClassroomsById(){

    }
}
