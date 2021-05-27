package ru.alex.courseModel.controller;

import org.springframework.web.bind.annotation.*;
import ru.alex.courseModel.entity.Grade;
import ru.alex.courseModel.entity.StudentProgressOnCourseId;
import ru.alex.courseModel.model.CourseDto;
import ru.alex.courseModel.model.StudentProgressOnCourseDto;
import ru.alex.courseModel.model.StudentDto;
import ru.alex.courseModel.service.StudentProgressOnCourseService;

import java.util.List;

@RestController
@RequestMapping("/active-courses")
public class StudentProgressOnCourseController {

    private final StudentProgressOnCourseService studentProgressOnCourseService;

    public StudentProgressOnCourseController(StudentProgressOnCourseService studentProgressOnCourseService) {
        this.studentProgressOnCourseService = studentProgressOnCourseService;
    }

    @GetMapping("/add-course-to-student")
    public void addCourseToStudent(@RequestParam ("courseId") int courseId,
                                   @RequestParam ("studentId") long studentId) {
        studentProgressOnCourseService.addStudentCourse(courseId, studentId);
    }

    @DeleteMapping("/delete-course-from-student")
    public void deleteCourseFromStudent(@RequestParam ("courseId") int courseId,
                                        @RequestParam ("studentId") long studentId) {
        studentProgressOnCourseService.removeStudentCourse(courseId, studentId);
    }



    @GetMapping("/current-student-courses")
    public List<CourseDto> getCurrentStudentCourses(@RequestParam ("studentId") long studentId) {
        return CourseDto.getCourseDtoList(studentProgressOnCourseService.getFinishedCourses(studentId, false));
    }

    @GetMapping("/current-course-students")
    public List<StudentDto> getCourseStudents(@RequestParam ("courseId") int courseId) {
        return StudentDto.getStudentDtoList(studentProgressOnCourseService.getAllCourseStudents(courseId));
    }

    @GetMapping("/finished-student-courses")
    public List<CourseDto> getFinishedStudentCourses(@RequestParam ("studentId") long studentId) {
        return CourseDto.getCourseDtoList(studentProgressOnCourseService.getFinishedCourses(studentId, true));
    }

    @GetMapping("/all-student-courses")
    public List<CourseDto> getAllStudentCourses(@RequestParam ("studentId") long studentId) {
        return CourseDto.getCourseDtoList(studentProgressOnCourseService.getAllStudentCourses(studentId));
    }

    @GetMapping("/grades")
    public List<Grade> getGradesOfStudentCourse(@RequestParam ("studentId") long studentId,
                                                   @RequestParam ("courseId") int courseId) {
        StudentProgressOnCourseId id = new StudentProgressOnCourseId(studentId, courseId);
        return studentProgressOnCourseService.getStudentCourse(id).getGrades();
    }

    @PostMapping("/grades/add")
    public void addGrade(@RequestParam ("studentId") long studentId,
                                   @RequestParam ("courseId") int courseId,
                                   @RequestBody Grade grade) {
        studentProgressOnCourseService.addGrade(studentId, courseId, grade);
    }

    @PutMapping("/grades/update")
    public Grade updateGrade(@RequestParam ("id") int gradeId,
                                @RequestBody Grade grade) {
        return studentProgressOnCourseService.updateGradeById(gradeId, grade);
    }

    @DeleteMapping("/grades/delete-{id}")
    public void deleteGrade(@PathVariable long id) {
        studentProgressOnCourseService.deleteGradeById(id);
    }

    @PutMapping("/finalize-active-course")
    public StudentProgressOnCourseDto finalizeActiveCourse(@RequestParam ("finalGrade") int finalGrade,
                                                           @RequestBody StudentProgressOnCourseId studentProgressOnCourseId) {
        return new StudentProgressOnCourseDto(studentProgressOnCourseService.setFinalStudentCourse(studentProgressOnCourseId, finalGrade));
    }
}
