package ru.alex.courseModel.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.alex.courseModel.entity.*;
import ru.alex.courseModel.model.CourseDto;
import ru.alex.courseModel.model.GradeDto;
import ru.alex.courseModel.model.StudentDto;
import ru.alex.courseModel.service.StudentService;

import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private StudentService studentService;

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
    public long deleteStudent(@PathVariable long id){
        studentService.deleteStudent(id);
        return id;
    }

    @PutMapping("/update-{id}")
    public StudentDto updateStudent(@PathVariable long id,
                                    @RequestBody Student student){
        return new StudentDto(studentService.updateStudent(id, student));
    }

    @GetMapping("/add-course")
    public void addCourse(@RequestParam ("courseId") int courseId,
                          @RequestParam ("studentId") long studentId){
        studentService.addStudentCourse(courseId, studentId);
    }

    @GetMapping("/delete-course")
    public void deleteCourse(@RequestParam ("courseId") int courseId,
                             @RequestParam ("studentId") long studentId){
        studentService.deleteStudentCourse(courseId, studentId);
    }

    @GetMapping("/available-courses")
    public List<CourseDto> allAvailableCourses(@RequestParam ("studentId") long studentId){
        return CourseDto.getCourseDtoList(studentService.getAvailableCourses(studentId));
    }

    @GetMapping("/current-courses")
    public List<CourseDto> allCurrentCourses(@RequestParam ("studentId") long studentId){
        return CourseDto.getCourseDtoList(studentService.getCurrentCourses(studentId));
    }

    @GetMapping("/finished-courses")
    public List<CourseDto> allFinishedCourses(@RequestParam ("studentId") long studentId){
        return CourseDto.getCourseDtoList(studentService.getFinishedCourses(studentId));
    }

    @GetMapping("/all-student-courses")
    public List<CourseDto> allStudentCourses(@RequestParam ("studentId") long studentId){
        return CourseDto.getCourseDtoList(studentService.getAllStudentCourses(studentId));
    }


    /*-----------------------------------------------------------------------------------*/



    @GetMapping("/grades")
    public List<GradeDto> allGradesOfStudentCourse(@RequestParam ("studentId") long studentId,
                                                   @RequestParam ("courseId") int courseId){
        StudentCourseId id = new StudentCourseId(studentId, courseId);
        return GradeDto.getGradeDtoList(studentService.getStudentCourse(id).getGrades());
    }

    @GetMapping("/grades/add")
    public List<GradeDto> addGrade(@RequestParam ("studentId") long studentId,
                                   @RequestParam ("courseId") int courseId,
                                   @RequestParam ("grade") int grade){
        StudentCourseId id = new StudentCourseId(studentId, courseId);
        studentService.addGrade(id, grade);
        return GradeDto.getGradeDtoList(studentService.getStudentCourse(id).getGrades());
    }

    @GetMapping("/grades/update")
    public GradeDto updateGrade(@RequestParam ("gradeId") int gradeId,
                                   @RequestParam ("value") int value){
        return new GradeDto(studentService.updateStudentCourseGrade(gradeId, value));
    }

    @GetMapping("/grades/delete-{id}")
    public long deleteGrade(@PathVariable long id){
        studentService.deleteGrade(id);
        return id;
    }
}

