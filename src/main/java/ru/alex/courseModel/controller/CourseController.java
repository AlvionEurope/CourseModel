package ru.alex.courseModel.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.alex.courseModel.model.CourseDto;
import ru.alex.courseModel.model.StudentDto;
import ru.alex.courseModel.service.CourseService;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/courses")
public class CourseController {
    @Autowired
    private CourseService courseService;

    @GetMapping("/all")
    public List<CourseDto> getCourses() {
        CourseDto courseDto = new CourseDto();
        return courseDto.getCourseDtoList(courseService.getAll());
    }
}
