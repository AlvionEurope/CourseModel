package ru.alex.courseModel.model;

import lombok.Data;
import ru.alex.courseModel.entity.StudentCourse;

import java.util.ArrayList;
import java.util.List;

@Data
public class GradeDto {
    private long id;
    private int value;
//    private StudentCourseDto studentCourseDto;
//
//    public List<StudentCourseDto> getStudentCourseDtoList(List<StudentCourse> studentCourses){
//        List<StudentCourseDto> studentCourseDtoList = new ArrayList<>();
//
//        for(StudentCourse studentCourse : studentCourses) {
//            StudentCourseDto studentCourseDto = new StudentCourseDto();
//
//            studentCourseDto.setId(studentCourse.getId());
//            studentCourseDto.setFinished(studentCourse.isFinished());
//
//            studentCourseDto.setStudentName(studentCourse.getStudent().getName());
//            studentCourseDto.setCourseName(studentCourse.getCourse().getName());
//
//            studentCourseDtoList.add(studentCourseDto);
//        }
//        return studentCourseDtoList;
//    }
}
