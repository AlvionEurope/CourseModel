package ru.alex.courseModel.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.alex.courseModel.entity.Course;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class CourseDto {
    private int id;
    private String name;
    private float cost;

    public CourseDto(Course course) {
        this.id = course.getId();
        this.name = course.getName();
        this.cost = course.getCost();
    }

    public static List<CourseDto> getCourseDtoList(List<Course> courses){
        List<CourseDto> courseDtoList = new ArrayList<>();
        for(Course course : courses){
            courseDtoList.add(new CourseDto(course));
        }
        return courseDtoList;
    }
}
