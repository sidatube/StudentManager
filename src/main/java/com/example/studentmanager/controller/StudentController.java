package com.example.studentmanager.controller;

import com.example.studentmanager.entity.dto.StudentDto;
import com.example.studentmanager.entity.filter.ClassroomFilter;
import com.example.studentmanager.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {
    @Autowired
    private StudentService service;

    @GetMapping("")
    public ResponseEntity<Object> getList(@RequestParam(defaultValue = "0") int pageIndex, @RequestParam(defaultValue = "10") int pageSize) {
        if (pageIndex>0)
            pageIndex=pageIndex-1;
        return ResponseEntity.ok(service.getList(pageIndex, pageSize));
    }

    @PostMapping("/add")
    public ResponseEntity<Object> addStudent(@RequestBody StudentDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.addStudent(dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> findStudent(@PathVariable int id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateStudent(@PathVariable int id,@RequestBody StudentDto dto) {
        return ResponseEntity.ok(service.updateStudent(id,dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteStudent(@PathVariable int id) {
        return ResponseEntity.ok(service.deleteStudent(id));
    }

    @PutMapping("/{id}/selectDepartment")
    public ResponseEntity<Object> selectDepartment(@PathVariable("id") int studentId, @RequestParam(defaultValue = "0") int departmentId) {
        return ResponseEntity.ok(service.addDepartment(studentId, departmentId));
    }

    @DeleteMapping("/{id}/removeDepartment")
    public ResponseEntity<Object> removeDepartment(@PathVariable("id") int studentId) {
        return ResponseEntity.ok(service.removeDepartment(studentId));
    }

    @PutMapping("/{id}/selectClassroom")
    public ResponseEntity<Object> selectClassroom(@PathVariable("id") int studentId, @RequestBody ClassroomFilter filter) {
        if (filter.getClassroomIds() == null)
            filter.setClassroomIds(new ArrayList<>());
        return ResponseEntity.ok(service.addClassrooms(studentId, filter.getClassroomIds()));
    }

    @DeleteMapping("/{id}/removeClassroom")
    public ResponseEntity<Object> removeClassroom(@PathVariable("id") int studentId, @RequestBody ClassroomFilter filter) {

        return ResponseEntity.ok(service.removeClassroom(studentId, filter.getClassroomId()));
    }

}
