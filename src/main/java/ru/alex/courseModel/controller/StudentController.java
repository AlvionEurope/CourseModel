package ru.alex.courseModel.controller;

import org.springframework.web.bind.annotation.*;
import ru.alex.courseModel.entity.*;
import ru.alex.courseModel.model.CourseDto;
import ru.alex.courseModel.model.StudentDto;
import ru.alex.courseModel.service.StudentService;

import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/all")
    public List<StudentDto> getAll() {
        return StudentDto.getStudentDtoList(studentService.getAll());
    }

    @GetMapping("/{id}")
    public StudentDto get(@PathVariable long id) {
        return new StudentDto(studentService.get(id));
    }

    @PostMapping
    public void add(@RequestBody Student student) {
        studentService.save(student);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable long id) {
        studentService.delete(id);
    }

    @PutMapping("/{id}")
    public void update(@PathVariable long id,
                       @RequestBody Student student) {
        studentService.update(id, student);
    }

    @GetMapping("/current-courses")
    public List<CourseDto> getStudentCourses(@RequestParam ("studentId") int studentId) {
        return CourseDto.getCourseDtoList(studentService.getStudentCourses(studentId));
    }
}

