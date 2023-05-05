package com.example.studentmanager.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity(name = "students")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String gender;
    private Date dob;
    private String phone;
    private  boolean deleted;
    @Column(updatable = false,insertable = false)
    private Integer department_id;
    @ManyToOne()
    private Department department;
    @OneToMany(mappedBy = "student")

    private List<StudentClassroom> studentClassroom;
}
