package ru.alex.courseModel.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.alex.courseModel.entity.Course;
import ru.alex.courseModel.entity.Instructor;
import ru.alex.courseModel.entity.StudentCourse;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
public class CourseDto {
    private long id;
    private String name;
    private float cost;

//    private List<InstructorDto> instructorDtoList;
//    private List<StudentCourseDto> studentCourseDtoList;

    public CourseDto(Course course) {
        this.id = course.getId();
        this.name = course.getName();
        this.cost = course.getCost();
    }

    public List<CourseDto> getCourseDtoList(List<Course> courses){
        List<CourseDto> courseDtoList = new ArrayList<>();
        for(Course course : courses){
//            instructorDtoList = new ArrayList<>();
//            studentCourseDtoList = new ArrayList<>();
            CourseDto courseDto = new CourseDto(course);

//            for(Instructor instructor : course.getInstructors()){
//                InstructorDto instructorDto = new InstructorDto(instructor);
//                instructorDtoList.add(instructorDto);
//            }
//
//            for (StudentCourse studentCourse : course.getStudentCourses()){
//                StudentCourseDto studentCourseDto = new StudentCourseDto(studentCourse);
//                studentCourseDtoList.add(studentCourseDto);
//            }

//            courseDto.setInstructorDtoList(instructorDtoList);
//            courseDto.setStudentCourseDtoList(studentCourseDtoList);
            courseDtoList.add(courseDto);
        }

        return courseDtoList;
    }
}
