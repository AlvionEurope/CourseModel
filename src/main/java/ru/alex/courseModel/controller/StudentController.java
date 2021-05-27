package ru.alex.courseModel.controller;

import org.springframework.web.bind.annotation.*;
import ru.alex.courseModel.entity.*;
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
    public List<StudentDto> getStudents() {
        return StudentDto.getStudentDtoList(studentService.getAllStudents());
    }

    @GetMapping("/{id}")
    public StudentDto getStudentById(@PathVariable long id) {
        return new StudentDto(studentService.getStudentById(id));
    }

    @PostMapping("/add")
    public StudentDto addStudent(@RequestBody Student student) {
        return new StudentDto(studentService.saveStudent(student));
    }

    @DeleteMapping("/del-{id}")
    public long deleteStudent(@PathVariable long id) {
        studentService.deleteStudent(id);
        return id;
    }

    @PutMapping("/update-{id}")
    public StudentDto updateStudent(@PathVariable long id,
                                    @RequestBody Student student) {
        return new StudentDto(studentService.updateStudent(id, student));
    }
}

