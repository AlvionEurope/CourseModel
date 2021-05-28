package ru.alex.courseModel.controller;

import org.springframework.web.bind.annotation.*;
import ru.alex.courseModel.entity.Course;
import ru.alex.courseModel.model.CourseDto;
import ru.alex.courseModel.model.ProfessorDto;
import ru.alex.courseModel.service.CourseService;

import java.util.List;

@RestController
@RequestMapping("/course")
public class CourseController {

    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping("/all")
    public List<CourseDto> getAll() {
        return CourseDto.getCourseDtoList(courseService.getAll());
    }

    @GetMapping("/{id}")
    public CourseDto get(@PathVariable int id) {
        return new CourseDto(courseService.get(id));
    }

    @PostMapping
    public void add(@RequestBody Course course) {
        courseService.save(course);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        courseService.delete(id);
    }

    @PutMapping("/{id}")
    public void update(@PathVariable int id,
                       @RequestBody Course course) {
        courseService.update(id, course);
    }

    @PutMapping("/add-professor")
    public void addProfessor(@RequestParam ("courseId") int courseId,
                             @RequestParam ("professorId") long professorId) {
        courseService.addProfessorToCourse(courseId, professorId);
    }

    @DeleteMapping("/delete-professor")
    public void deleteProfessor(@RequestParam ("courseId") int courseId,
                                @RequestParam ("professorId") long professorId) {
        courseService.deleteProfessorFromCourse(courseId, professorId);
    }

    @GetMapping("/course-professors")
    public List<ProfessorDto> getCourseProfessors(@RequestParam ("courseId") int courseId) {
        return ProfessorDto.getProfessorDtoList(courseService.getCourseProfessors(courseId));
    }

    @GetMapping("/current-student-courses")
    public List<CourseDto> getStudentCourses(@RequestParam ("studentId") int studentId) {
        return CourseDto.getCourseDtoList(courseService.getStudentCourses(studentId));
    }

    @GetMapping("/available-student-courses")
    public List<CourseDto> getAvailableStudentCourses(@RequestParam ("studentId") long studentId) {
        return CourseDto.getCourseDtoList(courseService.getAvailableStudentCourses(studentId));
    }
}
