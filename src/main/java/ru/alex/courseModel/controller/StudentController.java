package ru.alex.courseModel.controller;

import org.springframework.web.bind.annotation.*;
import ru.alex.courseModel.entity.*;
import ru.alex.courseModel.model.StudentDto;
import ru.alex.courseModel.service.StudentService;

import java.util.List;

@RestController
@RequestMapping("/students")
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
    public StudentDto add(@RequestBody Student student) {
        return new StudentDto(studentService.save(student));
    }

    @DeleteMapping("/{id}")
    public long delete(@PathVariable long id) {
        studentService.delete(id);
        return id;
    }

    @PutMapping("/{id}")
    public StudentDto update(@PathVariable long id,
                             @RequestBody Student student) {
        return new StudentDto(studentService.update(id, student));
    }
}

