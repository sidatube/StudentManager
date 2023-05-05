package com.example.studentmanager.repository;

import com.example.studentmanager.entity.Classroom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public interface ClassroomRepository extends JpaRepository<Classroom,Integer> {
    Optional<Classroom> findByName(String  name);


}
