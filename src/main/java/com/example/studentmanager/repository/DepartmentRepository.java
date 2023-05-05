package com.example.studentmanager.repository;

import com.example.studentmanager.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface DepartmentRepository extends JpaRepository<Department,Integer> {
    Optional<Department> findByName(String name);
}
