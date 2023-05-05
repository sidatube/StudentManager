package com.example.studentmanager.controller;

import com.example.studentmanager.entity.dto.DepartmentDto;
import com.example.studentmanager.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("department")
public class DepartmentController {
    @Autowired
    private DepartmentService service;

    @GetMapping
    public ResponseEntity<Object> getList(@RequestParam(defaultValue = "0") int pageIndex, @RequestParam(defaultValue = "10") int pageSize) {
        if (pageIndex>0)
            pageIndex=pageIndex-1;
        return ResponseEntity.ok(service.getList(pageIndex, pageSize));
    }
    @PostMapping("/add")
    public ResponseEntity<Object> addDepartment(@RequestBody DepartmentDto dto){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.addDepartment(dto));
    }
    @GetMapping("/{id}")
    public ResponseEntity<Object> findDepartment(@PathVariable int id){
        return ResponseEntity.ok(service.findById(id));
    }
    @PutMapping("/{id}/update")
    public ResponseEntity<Object> updateDepartment(@PathVariable int id,@RequestBody DepartmentDto dto){
        return ResponseEntity.ok(service.updateDepartment(id,dto));
    }
    @DeleteMapping("/{id}/delete")
    public ResponseEntity<Object> deleteDepartment(@PathVariable int id){
        return ResponseEntity.ok(service.deleteDepartment(id));
    }
}
