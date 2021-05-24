package ru.alex.courseModel.model;

import lombok.Data;
import ru.alex.courseModel.entity.Course;
import ru.alex.courseModel.entity.Instructor;
import ru.alex.courseModel.entity.Student;
import ru.alex.courseModel.entity.StudentCourse;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
public class StudentDto {

    private long id;
    private String name;
    private String address;
    private String phone;
    private String email;
    private int gradeBook;
    private float academicPerformance;

    private List<StudentCourseDto> studentCourseDtoList;
    private List<CourseDto> courseDtoList;

    public List<StudentDto> getStudentDtoList(List<Student> students){
        List<StudentDto> studentDtoList = new ArrayList<>();

        for(Student student : students){
            courseDtoList = new ArrayList<>();
            studentCourseDtoList = new ArrayList<>();
            StudentDto studentDTO = new StudentDto();

            studentDTO.setId(student.getId());
            studentDTO.setName(student.getName());
            studentDTO.setAddress(student.getAddress());
            studentDTO.setEmail(student.getEmail());
            studentDTO.setPhone(student.getPhone());
            studentDTO.setGradeBook(student.getGradeBook());
            studentDTO.setAcademicPerformance(student.getAcademicPerformance());

            for(StudentCourse studentCourse : student.getStudentCourses()){
                StudentCourseDto studentCourseDto = new StudentCourseDto();

                studentCourseDto.setId(studentCourse.getId());
                studentCourseDto.setFinished(studentCourse.isFinished());
                studentCourseDto.setCourseName(studentCourse.getCourse().getName());
                studentCourseDto.setFinalGrade(studentCourse.getFinalGrade());

                studentCourseDtoList.add(studentCourseDto);
            }

//            for(Course course : student.getCourses()){
//                CourseDto courseDto = new CourseDto();
//                courseDto.setId(course.getId());
//                courseDto.setName(course.getName());
//                courseDto.setCost(course.getCost());
//                courseDtoList.add(courseDto);
//            }

            studentDTO.setStudentCourseDtoList(studentCourseDtoList);
            studentDTO.setCourseDtoList(courseDtoList);
            studentDtoList.add(studentDTO);
        }
        return studentDtoList;
    }
}
