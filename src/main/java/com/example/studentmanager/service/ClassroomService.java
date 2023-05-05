package com.example.studentmanager.service;

import com.example.studentmanager.entity.Classroom;
import com.example.studentmanager.entity.Department;
import com.example.studentmanager.entity.dto.ClassroomDto;
import com.example.studentmanager.repository.ClassroomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClassroomService {
    @Autowired
    private ClassroomRepository repository;
    public Object getList(int pageIndex,int pageSize){
        Page<Classroom> classrooms= repository.findAll(PageRequest.of(pageIndex,pageSize));
        return classrooms;
    }
    public Object addClassroom(ClassroomDto dto) {
        Optional<Classroom> find = repository.findByName(dto.getName());
        if (find.isPresent())
            return null;
        Classroom newC = new Classroom();
        newC.setName(dto.getName());
        newC.setDeleted(false);
        return ClassroomDto.toDto(repository.save(newC));
    }

    public Object findById(int id){
        Optional<Classroom> find = repository.findById(id);
        return find.orElse(null);
    }
    public Object updateClassroom(int id ,ClassroomDto dto){
        Optional<Classroom> find = repository.findById(id);
        if (!find.isPresent())
            return null;
        Classroom old = find.get();
        old.setName(dto.getName());
        old.setDeleted(dto.isDeleted());
        return ClassroomDto.toDto(repository.save(old));
    }
    public Object deleteClassroom(int id){
        Optional<Classroom> find = repository.findById(id);
        if (!find.isPresent())
            return null;
        Classroom old = find.get();
        old.setDeleted(true);
        ClassroomDto.toDto(repository.save(old));
        return true ;
    }
}
