package com.example.studentmanager.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Objects;

@Entity(name = "student_classroom")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class StudentClassroom {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(updatable = false,insertable = false)
    private Integer student_id;
    @ManyToOne()
    @JsonBackReference
    private Student student;
    @Column(updatable = false,insertable = false)
    private Integer classroom_id;
    @ManyToOne()
    @JsonManagedReference
    private Classroom classroom;
    private boolean deleted;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StudentClassroom that = (StudentClassroom) o;
        return student_id == that.student_id && classroom_id == that.classroom_id && deleted == that.deleted;
    }

    @Override
    public int hashCode() {
        return Objects.hash(student_id, classroom_id, deleted);
    }
}
