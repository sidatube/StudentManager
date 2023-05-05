package com.example.studentmanager.entity.dto;

import com.example.studentmanager.entity.Classroom;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ClassroomDto {
    private int id;
    private String name;
    private boolean deleted;

    public static ClassroomDto toDto(Classroom base){
        ClassroomDto dto = new ClassroomDto();
        dto.setId(base.getId());
        dto.setName(base.getName());
        dto.setDeleted(base.isDeleted());
        return dto;
    }
    
}
