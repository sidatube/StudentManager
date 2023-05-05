package com.example.studentmanager.service;

import com.example.studentmanager.entity.Department;
import com.example.studentmanager.entity.Student;
import com.example.studentmanager.entity.dto.DepartmentDto;
import com.example.studentmanager.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DepartmentService {
    @Autowired
    DepartmentRepository repository;
    public Object getList(int pageIndex,int pageSize){
        Page<Department> departments= repository.findAll(PageRequest.of(pageIndex,pageSize));
        return departments;
    }
    public Object addDepartment(DepartmentDto dto){
        Optional<Department> find = repository.findByName(dto.getName());
        if (find.isPresent())
            return null;
        Department add = new Department();
        add.setName(dto.getName());
        add.setDeleted(false);
        return DepartmentDto.toDto(repository.save(add));
    }
    public Object findById(int id){
        Optional<Department> find = repository.findById(id);
        return find.orElse(null);
    }
    public Object updateDepartment(int id,DepartmentDto dto){
        Optional<Department> find = repository.findById(id);
        if (!find.isPresent())
            return null;
        Department old = find.get();
        old.setName(dto.getName());
        old.setDeleted(dto.isDeleted());
        return DepartmentDto.toDto(repository.save(old));
    }
    public Object deleteDepartment(int id){
        Optional<Department> find = repository.findById(id);
        if (!find.isPresent())
            return null;
        Department old = find.get();
        old.setDeleted(true);
        DepartmentDto.toDto(repository.save(old));
        return true ;
    }
}
