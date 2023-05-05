package com.example.studentmanager.seeder;

import com.example.studentmanager.entity.Classroom;
import com.example.studentmanager.entity.Department;
import com.example.studentmanager.entity.Student;
import com.example.studentmanager.entity.StudentClassroom;
import com.example.studentmanager.repository.ClassroomRepository;
import com.example.studentmanager.repository.DepartmentRepository;
import com.example.studentmanager.repository.StudentClassroomRepository;
import com.example.studentmanager.repository.StudentRepository;
import com.github.javafaker.Faker;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class Seeder implements CommandLineRunner {
    private final Faker faker = new Faker();
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private DepartmentRepository departmentRepository;
    @Autowired
    private ClassroomRepository classroomRepository;
    @Autowired
    private StudentClassroomRepository studentClassroomRepository;

    @Override
    public void run(String... args) throws Exception {
        seeding();
    }

    private void seeding() {
        if (departmentRepository.findAll().isEmpty()){
            List<Department> departments = new ArrayList<>();
            departments.add(Department.builder().name("Kỹ thuật cơ khí").deleted(false).build());
            departments.add(Department.builder().name("Kỹ thuật cơ điện tử").deleted(false).build());
            departments.add(Department.builder().name("Kỹ thuật cơ khí (động lực)").deleted(false).build());
            departments.add(Department.builder().name("Kỹ thuật hàng không").deleted(false).build());
            departments.add(Department.builder().name("Kỹ thuật tàu thủy").deleted(false).build());
            departments.add(Department.builder().name("Kỹ thuật nhiệt").deleted(false).build());
            departments.add(Department.builder().name("Kỹ thuật điện, điện tử").deleted(false).build());
            departments.add(Department.builder().name("Kỹ thuật điều khiển và tự động hóa").deleted(false).build());
            departments.add(Department.builder().name("Kỹ thuật máy tính").deleted(false).build());
            departments.add(Department.builder().name("Khoa học máy tính").deleted(false).build());
            departments.add(Department.builder().name("Kỹ thuật phần mềm").deleted(false).build());
            departments.add(Department.builder().name("Hệ thống thông tin").deleted(false).build());
            departmentRepository.saveAll(departments);
        }
        if (studentRepository.findAll().isEmpty()) {
            List<Student> students = new ArrayList<>();
            List<Department> departments = departmentRepository.findAll();

            for (int i = 0; i < 101; i++) {
                Student student = new Student();
                student.setName(faker.name().nameWithMiddle());
                if (i % 3 == 0||i%4==0)
                    student.setGender("Male");
                else
                    student.setGender("Female");
                student.setPhone(faker.phoneNumber().phoneNumber());
                student.setDeleted(false);
                student.setDob(faker.date().birthday(19,22));
                students.add(student);
                student.setDepartment(departments.get(faker.random().nextInt(1,11)));
            }
            studentRepository.saveAll(students);
        }
        if (classroomRepository.findAll().isEmpty()){
            List<Classroom> classrooms = new ArrayList<>();
            for (char c: "ABCDE".toCharArray()
                 ) {
                for (int i = 1; i <9 ; i++) {
                    Classroom classroom = new Classroom();
                    classroom.setName(c+"0"+i);
                    classroom.setDeleted(false);
                    classrooms.add(classroom);
                }
            }
            classroomRepository.saveAll(classrooms);
        }
        if (studentClassroomRepository.findAll().isEmpty()){
            List<Student> students = studentRepository.findAll();
            List<Classroom> classrooms = classroomRepository.findAll();
            Set<StudentClassroom> studentClassrooms = new HashSet<>();
            for (Student st: students
                 ) {
                for (int i = 0; i < faker.random().nextInt(3,5); i++) {
                    StudentClassroom item = new StudentClassroom();
                    item.setId(0);
                    item.setStudent(st);
                    item.setStudent_id(st.getId());
                    Classroom classroom = classrooms.get(faker.random().nextInt(0,classrooms.size()-1));
                    item.setClassroom(classroom);
                    item.setClassroom_id(classroom.getId());
                    studentClassrooms.add(item);
                }
            }
            studentClassroomRepository.saveAll(studentClassrooms);
        }
    }
}
