package ru.wxw.coursemodel.wxw.controller;


import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.wxw.coursemodel.wxw.dto.StudentDTO;
import ru.wxw.coursemodel.wxw.dto.StudentToCourseDTO;
import ru.wxw.coursemodel.wxw.dto.СompletedСoursesDTO;
import ru.wxw.coursemodel.wxw.service.StudentService;

import java.util.List;


@RestController
@RequestMapping("/student")
public class StudentController {
    @Autowired
    private StudentService studentService;
    @Autowired
    private final ModelMapper modelMapper;

    public StudentController(StudentService studentService, ModelMapper modelMapper) {
        this.studentService = studentService;
        this.modelMapper = modelMapper;
    }

    @PostMapping("/addToCourse")
    public ResponseEntity<StudentToCourseDTO> addStudentToCourse(@RequestParam(value = "bookGrade") int bookGrade,
                                                                 @RequestParam(value = "numberCourse") int courseNumber) {
        StudentToCourseDTO studentToCourseDTO = studentService.addStudentToCourse(bookGrade, courseNumber);
        return studentToCourseDTO != null
                ? new ResponseEntity<>(studentToCourseDTO, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PostMapping("courseFinished/{gradeBook}")
    public List<СompletedСoursesDTO> getFinishedCourse(@PathVariable("gradeBook") int gradeBook) {
        return studentService.getFinishedCourse(gradeBook);
    }

    @PostMapping()
    public ResponseEntity<StudentDTO> create(@RequestBody StudentDTO studentDTO) {
        StudentDTO studentDTOReturn = studentService.save(studentDTO);
        return studentDTOReturn != null
                ? new ResponseEntity<>(studentDTOReturn, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable("id") Long id) {
        studentService.delete(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<StudentDTO>> getAll() {
        return ResponseEntity
                .ok()
                .headers(new HttpHeaders())
                .body(studentService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentDTO> getById(@PathVariable("id") Long id) {
        StudentDTO studentDTO = studentService.findById(id);
        return studentDTO != null
                ? new ResponseEntity<>(studentDTO, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PutMapping("/{id}")
    public ResponseEntity<StudentDTO> updateStudent(
            @PathVariable(value = "id", required = false) Long id,
            @RequestBody StudentDTO studentDTO) {
        StudentDTO studentDTOReturn = studentService.update(studentDTO, id);
        return studentDTOReturn != null
                ? new ResponseEntity<>(studentDTOReturn, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

}
