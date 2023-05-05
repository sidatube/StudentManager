package com.example.studentmanager.entity.dto;

import com.example.studentmanager.entity.Department;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class DepartmentDto {
    private int id;
    private String name;
    private boolean deleted;
    public static DepartmentDto toDto(Department base){
        DepartmentDto dto = new DepartmentDto();
        dto.setId(base.getId());
        dto.setName(base.getName());
        return dto;
    }
}
