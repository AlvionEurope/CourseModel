package com.example.demo.controller;

import com.example.demo.entity.Course;
import com.example.demo.entity.CourseCompletion;
import com.example.demo.entity.Student;
import com.example.demo.entity.Teacher;
import com.example.demo.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;


@RestController
public class MyController {

    @Autowired
    private CourseCompetitionService services;
    @Autowired
    private StudentService studentService;
    @Autowired
    private TeacherService teacherService;
    @Autowired
    CourseService courseService;
    @Autowired
    private ReportService reportService;

    // создаем студента
    @PostMapping("/students")
    public String addStudent(@RequestBody Student student) {
        studentService.create(student);
        return "Студент добавлен!";
    }

    //Создаем курс
    @PostMapping("/courses")
    public Course addcourse(@RequestBody Course course) {
        courseService.create(course);
        return course;
    }
    // создаем учителя
    @PostMapping("/teachers")
    public Teacher addTeachers(@RequestBody Teacher teacher) {
        teacherService.create(teacher);
        return teacher;
    }

    //создаем прохождение курса
    @PostMapping("/compets")
    public CourseCompletion addCompets(@RequestBody CourseCompletion completion) {
        services.create(completion);
        return completion;
    }

    //Удаляем студента с курса
    @GetMapping("/delete/studentName/{studentName}/course/{course}")
    public String removeStudent(@PathVariable String studentName,@PathVariable String course) {
        services.removeStudentToCourse(studentName,course);
        return "Студент с именем " + studentName + " удален с курса " + course;
    }

    // Получаем список курсов на которые записан студент
    @GetMapping("/complets/{studentName}")
    public List<String> getCourse(@PathVariable String studentName) {
        return services.getCurrentStudentCourses(studentName);
    }

    //Получаем средний бал по всем курсам
    @GetMapping("/average/studentname/{studentName}/courseName/{courseName}")
    public String getAverage(@PathVariable String studentName, @PathVariable String courseName) {
       return "Средний балл студента " + studentName + " составляет = " +  services.getAwerageGrade(studentName);
    }

    //получение финальной оценки по всем курсам
    @GetMapping("/final/{studentName}")
    public String getFinal(@PathVariable String studentName) {
        int res = services.getFinalGrade(studentName);
        return "Финальная оценка " + studentName + " по всем курсам = " + res;
    }

    @GetMapping("/report")
    public void getReport(HttpServletResponse response) {
         reportService.getReport(response);
    }
}
