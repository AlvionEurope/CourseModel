package com.example.course.controller;

import com.example.course.model.Course;
import com.example.course.model.Student;
import com.example.course.service.StudentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class StudentController {

    final StudentService service;

    public StudentController(StudentService service) {
        this.service = service;
    }


    @PostMapping("/student")
    public Student postStudent(@RequestBody Student student) {
        return service.postStudent(student);
    }

    @GetMapping("/student/{id}")
    public Student getStudent(@PathVariable Long id) {
        return service.getStudentId(id);
    }

    @DeleteMapping("/student/{id}")
    public void deleteStudent(@PathVariable Long id) {
        service.deleteStudent(id);
    }

    @PutMapping("/student/{id}")
    public Student putStudent(@RequestBody Student student,
                              @PathVariable Long id) throws Exception {
        return service.putStudent(student, id);
    }

    //Добавить оценку
    @PostMapping("/student/{id}")
    public void addPointCourse(@RequestParam(value = "courseId") Long courseId,
                               @RequestParam(value = "point") Integer point,
                               @PathVariable Long id) {
        service.addPointCourse(id, courseId, point);
    }

    //Получить список оценок на курсе
    @GetMapping("/student/{id}/course")
    public List<Integer> getPointsCourse(@RequestParam(value = "courseId") Long courseId,
                                         @PathVariable Long id) {
        return service.getPointCourse(id, courseId);
    }

    //Получить текущий средний балл на курсе
    @GetMapping("/student/course")
    public Integer getAvgPointCourse(@RequestParam(value = "courseId") Long courseId,
                                     @RequestParam(value = "studentId") Long studentId) {
        return service.getAvgPointCourse(courseId, studentId);
    }

    //Получить финальную оценку за курс
    @GetMapping("/student/course/final")
    public Integer getFinalPointCourse(@RequestParam(value = "courseId") Long courseId,
                                       @RequestParam(value = "studentId") Long studentId) {
        return service.getFinalPointCourse(courseId, studentId);
    }

    //Получить список прослушанных курсов
    @GetMapping("/student/{id}/course/all")
    public List<Course> getListCoursesTaken(@PathVariable Long id) {
        return service.getListCourseTaken(id);
    }

    //Получить список курсов на которые студент может записатся
    @GetMapping("/student/{id}/course/reg")
    public List<Course> getListCoursesCanSignUp(@PathVariable Long id) {
        return service.canSignUpCourse(id);
    }
}
