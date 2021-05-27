package ru.alex.courseModel.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.alex.courseModel.entity.Course;
import ru.alex.courseModel.model.CourseDto;
import ru.alex.courseModel.model.ProfessorDto;
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


    @GetMapping("/add-professor")
    public void addProfessor(@RequestParam ("courseId") int courseId,
                             @RequestParam ("professorId") long professorId){
        courseService.addProfessorToCourse(courseId, professorId);
    }

    @DeleteMapping("/delete-professor")
    public void deleteProfessor(@RequestParam ("courseId") int courseId,
                                @RequestParam ("professorId") long professorId){
        courseService.deleteProfessorFromCourse(courseId, professorId);
    }

    @GetMapping("/all-course-professors")
    public List<ProfessorDto> getCourseProfessors(@RequestParam ("courseId") int courseId){
        return ProfessorDto.getProfessorDtoList(courseService.getCourseProfessors(courseId));
    }
}
