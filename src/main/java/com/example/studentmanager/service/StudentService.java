package com.example.studentmanager.service;

import com.example.studentmanager.entity.Classroom;
import com.example.studentmanager.entity.Department;
import com.example.studentmanager.entity.Student;
import com.example.studentmanager.entity.StudentClassroom;
import com.example.studentmanager.entity.dto.StudentDto;
import com.example.studentmanager.repository.ClassroomRepository;
import com.example.studentmanager.repository.DepartmentRepository;
import com.example.studentmanager.repository.StudentClassroomRepository;
import com.example.studentmanager.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StudentService {
    @Autowired
    private StudentRepository repository;
    @Autowired
    private DepartmentRepository departmentRepository;
    @Autowired
    private ClassroomRepository classroomRepository;
    @Autowired
    private StudentClassroomRepository thirdRepository;
    public Object getList(int pageIndex,int pageSize){
        Page<Student> students= repository.findAll(PageRequest.of(pageIndex,pageSize));
        return students;
    }
    public Object addStudent(StudentDto dto) {
        Student student = new Student();
        student.setName(dto.getName());
        student.setDob(dto.getDob());
        student.setGender(dto.getGender());
        student.setPhone(dto.getPhone());
        return StudentDto.toDto(repository.save(student));
    }

    private Student findStudent(int id) {
        Optional<Student> find = repository.findById(id);
        if (!find.isPresent()) {
            return null;
        }
        Student student = find.get();
        if (student.isDeleted())
            return null;
        return student;
    }
    public StudentDto updateStudent(int studentId,StudentDto dto){
        Optional<Student> find = repository.findById(studentId);
        if (!find.isPresent()) {
            return null;
        }
        Student student = find.get();
        student.setName(dto.getName());
        student.setGender(dto.getGender());
        student.setDob(dto.getDob());
        student.setPhone(dto.getPhone());
        student.setDeleted(dto.isDeleted());
        return StudentDto.toDto(repository.save(student));
    }
    public Object findById(int id) {
        Student student = findStudent(id);
        if (student == null)
            return null;
        return StudentDto.toDto(student);
    }

    public Object addDepartment(int studentId, int department_id) {
        Student student = findStudent(studentId);
        if (student == null)
            return null;
        Optional<Department> departmentFind = departmentRepository.findById(department_id);
        if (!departmentFind.isPresent()) {
            return null;
        }
        Department department = departmentFind.get();
        if (department.isDeleted())
            return null;
        student.setDepartment(department);
        repository.save(student);
        return true;
    }
    public Object removeDepartment(int studentId){
        Student student = findStudent(studentId);
        if (student == null)
            return null;
        student.setDepartment(null);
        repository.save(student);
        return true;
    }
    public Object addClassrooms(int studentId, List<Integer> classroomIds) {
        Student student = findStudent(studentId);
        if (student == null)
            return null;
        List<Classroom> classrooms = classroomRepository.findAllById(classroomIds);
        classrooms = classrooms.stream().filter(e -> !e.isDeleted()).collect(Collectors.toList());
        List<StudentClassroom> classroomList = new ArrayList<>();
        classrooms.forEach(e->{
            StudentClassroom item = new StudentClassroom();
            item.setClassroom(e);
            item.setStudent(student);
            classroomList.add(item);
        });
        thirdRepository.saveAll(classroomList);
        return true;
    }
    public Object removeClassroom(int studentId,int classroomId){
        Student student = findStudent(studentId);
        if (student == null)
            return null;
        Optional<StudentClassroom> optional = thirdRepository.findByStudent_idAndClassroom_id(studentId,classroomId);
        if (optional.isPresent()){
            StudentClassroom studentClassroom = optional.get();
            studentClassroom.setDeleted(true);
            thirdRepository.save(studentClassroom);
            return true;
        }
        return false;
    }
    public Object deleteStudent(int id){
        Student student = findStudent(id);
        if (student == null|| student.isDeleted())
            return null;
        student.setDeleted(true);
        repository.save(student);
        return true;
    }


}
