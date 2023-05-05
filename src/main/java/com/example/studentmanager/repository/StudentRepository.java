package com.example.studentmanager.repository;

import com.example.studentmanager.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public interface StudentRepository extends JpaRepository<Student,Integer> {
}
