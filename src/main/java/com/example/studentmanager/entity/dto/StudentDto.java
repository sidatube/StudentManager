package com.example.studentmanager.entity.dto;

import com.example.studentmanager.entity.Student;
import com.example.studentmanager.entity.StudentClassroom;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class StudentDto {
    private int id;
    private String name;
    private String gender;
    private Date dob;
    private String phone;
    private boolean deleted;
    private List<ClassroomDto> classroom;
    private DepartmentDto department;
    public static StudentDto toDto(Student base){
        StudentDto dto = new StudentDto();
        dto.setId(base.getId());
        dto.setName(base.getName());
        dto.setGender(base.getGender());
        dto.setDob(base.getDob());
        dto.setPhone(base.getPhone());
        dto.setDeleted(base.isDeleted());
        dto.setClassroom(new ArrayList<>());
        if (base.getStudentClassroom()!=null&& !base.getStudentClassroom().isEmpty()){
            dto.setClassroom(base.getStudentClassroom().stream().filter(e->!(e.isDeleted()||e.getClassroom().isDeleted())).map(StudentClassroom::getClassroom).map(ClassroomDto::toDto).collect(Collectors.toList()));
        }
        if (base.getDepartment()!=null&&base.isDeleted()){
            dto.setDepartment(DepartmentDto.toDto(base.getDepartment()));
        }
        return dto;
    }
}
