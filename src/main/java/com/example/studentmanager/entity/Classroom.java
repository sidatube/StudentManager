package com.example.studentmanager.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity(name = "classrooms")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Classroom {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private boolean deleted;
    @OneToMany(mappedBy = "classroom")
    @JsonBackReference
    private List<StudentClassroom> studentClassroom;
}
