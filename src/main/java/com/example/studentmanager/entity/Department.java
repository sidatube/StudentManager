package com.example.studentmanager.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity(name = "departments")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private boolean deleted;
    @OneToMany( mappedBy = "department")
    @JsonBackReference
    private List<Student> students;
}
