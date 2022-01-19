package com.example.demo_test.controller;

import com.example.demo_test.bean.Classroom;
import com.example.demo_test.exception.custom.ApiNotFoundException;
import com.example.demo_test.service.ClassroomService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static java.util.Objects.isNull;

@Api(tags = "Classroom API")
@RestController
@RequestMapping("${v1API}/classroom")
public class ClassroomController {

    @Autowired
    private ClassroomService classroomService;

    @ApiOperation("取得所有教室資料（cache）")
    @GetMapping(value = "")
    public ResponseEntity<List<Classroom>> getClassrooms() {
        List<Classroom> list = classroomService.getClassrooms();

        return ResponseEntity.status(200).body(list);
    }

    @ApiOperation("取得指定教室資料（cache）")
    @ApiResponses({
            @ApiResponse(code=404,message="找不到指定教室")
    })
    @GetMapping(value = "/{classroomId}")
    public ResponseEntity<Classroom> getClassroom(@PathVariable Integer classroomId) throws ApiNotFoundException {
        Classroom classroom = classroomService.getClassroomById(classroomId);

        if (isNull(classroom)) {
            throw new ApiNotFoundException();
        }

        return ResponseEntity.status(HttpStatus.OK).body(classroom);
    }
}
