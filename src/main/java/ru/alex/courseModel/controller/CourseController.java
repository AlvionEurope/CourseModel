package ru.alex.courseModel.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.alex.courseModel.entity.Course;
import ru.alex.courseModel.model.CourseDto;
import ru.alex.courseModel.model.StudentDto;
import ru.alex.courseModel.service.CourseService;

import java.util.List;

@RestController
@RequestMapping("/course")
public class CourseController {
    @Autowired
    private CourseService courseService;

    @GetMapping("/all")
    public List<CourseDto> getCourses() {
        return CourseDto.getCourseDtoList(courseService.getAll());
    }

    @GetMapping("/{id}")
    public CourseDto getCourseById(@PathVariable int id) {
        return new CourseDto(courseService.getCourseById(id));
    }

    @PostMapping("/add")
    public CourseDto addCourse(@RequestBody Course course) {
        return new CourseDto(courseService.saveCourse(course));
    }

    @DeleteMapping("/del-{id}")
    public int deleteCourse(@PathVariable int id){
        courseService.deleteCourse(id);
        return id;
    }

    @PutMapping("/update-{id}")
    public CourseDto updateCourse(@PathVariable int id, @RequestBody Course course){
        return new CourseDto(courseService.updateCourse(id, course));
    }

    @GetMapping("/add-student")
    public void addStudent(@RequestParam ("studentId") long studentId,
                          @RequestParam ("courseId") int courseId){
        courseService.addStudentCourse(studentId, courseId);
    }

    @GetMapping("/delete-student")
    public void deleteCourse(@RequestParam ("studentId") long studentId,
                             @RequestParam ("courseId") int courseId){
        courseService.deleteStudentCourse(studentId, courseId);
    }

    @GetMapping("/all-course-students")
    public List<StudentDto> allCourseStudents(@RequestParam ("courseId") int courseId){
        return StudentDto.getStudentDtoList(courseService.getCourseStudents(courseId));
    }

    @GetMapping("/add-instructor")
    public void addInstructor(@RequestParam ("courseId") int courseId,
                              @RequestParam ("instructorId") long instructorId){
        courseService.addInstructorToCourse(courseId, instructorId);
    }

    @GetMapping("/delete-instructor")
    public void deleteInstructor(@RequestParam ("courseId") int courseId,
                              @RequestParam ("instructorId") long instructorId){
        courseService.deleteInstructorFromCourse(courseId, instructorId);
    }
}
