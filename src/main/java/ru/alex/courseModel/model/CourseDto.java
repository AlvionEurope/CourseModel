package ru.alex.courseModel.model;

import lombok.Data;
import ru.alex.courseModel.entity.Course;
import ru.alex.courseModel.entity.Instructor;
import ru.alex.courseModel.entity.StudentCourse;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
public class CourseDto {
    private long id;
    private String name;
    private float cost;

    private List<InstructorDto> instructorDtoList;
    private List<StudentCourseDto> studentCourseDtoList;

    public List<CourseDto> getCourseDtoList(List<Course> courses){
        List<CourseDto> courseDtoList = new ArrayList<>();
        for(Course course : courses){
            instructorDtoList = new ArrayList<>();
            studentCourseDtoList = new ArrayList<>();
            CourseDto courseDto = new CourseDto();
            courseDto.setId(course.getId());
            courseDto.setName(course.getName());
            courseDto.setCost(course.getCost());

            for(Instructor instructor : course.getInstructors()){
                InstructorDto instructorDto = new InstructorDto();
                instructorDto.setId(instructor.getId());
                instructorDto.setName(instructor.getName());
                instructorDto.setAddress(instructor.getAddress());
                instructorDto.setPhone(instructor.getPhone());
                instructorDto.setPayment(instructor.getPayment());

                instructorDtoList.add(instructorDto);
            }

            for (StudentCourse studentCourse : course.getStudentCourses()){
                StudentCourseDto studentCourseDto = new StudentCourseDto();
                studentCourseDto.setId(studentCourseDto.getId());
                studentCourseDto.setStudentName(studentCourse.getStudent().getName());
                studentCourseDto.setCourseName(studentCourse.getCourse().getName());
                studentCourseDto.setFinished(studentCourse.isFinished());

                studentCourseDtoList.add(studentCourseDto);
            }

            courseDto.setInstructorDtoList(instructorDtoList);
            courseDto.setStudentCourseDtoList(studentCourseDtoList);
            courseDtoList.add(courseDto);
        }

        return courseDtoList;
    }
}
