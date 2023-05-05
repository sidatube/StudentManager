package com.example.studentmanager.entity.filter;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ClassroomFilter {
    private int classroomId=0;
    private List<Integer> classroomIds ;
}
