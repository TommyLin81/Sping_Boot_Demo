package com.example.demo_test.controller;

import com.example.demo_test.bean.Student;
import com.example.demo_test.exception.custom.ApiNotFoundException;
import com.example.demo_test.service.StudentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static java.util.Objects.isNull;

@Api(tags = "Student API")
@RestController
@RequestMapping("${v1API}/students")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @ApiOperation("取得所有學生資料")
    @GetMapping("")
    public ResponseEntity<List<Student>> getStudents() {
        List<Student> list = studentService.getStudents();

        return ResponseEntity.status(200).body(list);
    }

    @ApiOperation("取得指定學生資料")
    @ApiResponses({
            @ApiResponse(code=404,message="找不到指定學生")
    })
    @GetMapping("/{studentId}")
    public ResponseEntity<Student> getStudent(@PathVariable Integer studentId) throws ApiNotFoundException {
        Student student = studentService.getStudentById(studentId);

        if (isNull(student)) {
            throw new ApiNotFoundException();
        }

        return ResponseEntity.status(HttpStatus.OK).body(student);
    }

    @ApiOperation("建立單一學生資料")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("")
    public ResponseEntity<Object> createStudent(@RequestBody Student student) {
        studentService.createStudent(student);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @ApiOperation("更新指定學生資料")
    @ApiResponses({
            @ApiResponse(code=404,message="找不到指定學生")
    })
    @PutMapping("/{studentId}")
    public ResponseEntity<Student> updateStudent(@PathVariable Integer studentId,
                                                 @RequestBody Student student) throws ApiNotFoundException {
        Student result = studentService.updateStudentById(studentId, student);

        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @ApiOperation("移除指定學生資料")
    @ApiResponses({
            @ApiResponse(code=404,message="找不到指定學生")
    })
    @DeleteMapping("/{studentId}")
    public ResponseEntity<Object> deleteStudent(@PathVariable Integer studentId) throws ApiNotFoundException {
        studentService.deleteById(studentId);

        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
