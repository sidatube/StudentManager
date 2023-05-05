package com.example.studentmanager.repository;

import com.example.studentmanager.entity.StudentClassroom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface StudentClassroomRepository extends JpaRepository<StudentClassroom,Integer> {
 Optional<StudentClassroom> findByStudent_idAndClassroom_id(int student_id,int classroom_id);
}
