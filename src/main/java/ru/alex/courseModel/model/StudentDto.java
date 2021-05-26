package ru.alex.courseModel.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.alex.courseModel.entity.Student;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class StudentDto {

    private long id;
    private String name;
    private String address;
    private String phone;
    private String email;
    private int gradeBook;
    private float academicPerformance;

    public StudentDto(Student student) {
        this.id = student.getId();
        this.name = student.getName();
        this.address = student.getAddress();
        this.phone = student.getPhone();
        this.email = student.getEmail();
        this.gradeBook = student.getGradeBook();
        this.academicPerformance = student.getAcademicPerformance();
    }

    public static List<StudentDto> getStudentDtoList(List<Student> students){
        List<StudentDto> studentDtoList = new ArrayList<>();
        if (students != null) {
            for (Student student : students) {
                studentDtoList.add(new StudentDto(student));
            }
        }
        return studentDtoList;
    }
}
