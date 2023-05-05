package com.example.studentmanager.controller;

import com.example.studentmanager.entity.dto.ClassroomDto;
import com.example.studentmanager.service.ClassroomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("classroom")
public class ClassroomController {
    @Autowired
    private ClassroomService service;
    @GetMapping
    public ResponseEntity<Object> getList(@RequestParam(defaultValue = "0") int pageIndex, @RequestParam(defaultValue = "10") int pageSize) {
        if (pageIndex>0)
            pageIndex=pageIndex-1;
        return ResponseEntity.ok(service.getList(pageIndex, pageSize));
    }
    @PostMapping("/add")
    public ResponseEntity<Object> addClassroom(@RequestBody ClassroomDto dto){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.addClassroom(dto));
    }
    @GetMapping("/{id}")
    public ResponseEntity<Object> findClassroom(@PathVariable int id){
        return ResponseEntity.ok(service.findById(id));
    }
    @PutMapping("/{id}/update")
    public ResponseEntity<Object> updateClassroom(@PathVariable int id,@RequestBody ClassroomDto dto){
        return ResponseEntity.ok(service.updateClassroom(id,dto));
    }
    @DeleteMapping("/{id}/delete")
    public ResponseEntity<Object> deleteClassroom(@PathVariable int id){
        return ResponseEntity.ok(service.deleteClassroom(id));
    }
}
