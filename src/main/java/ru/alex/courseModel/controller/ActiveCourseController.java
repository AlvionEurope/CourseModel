package ru.alex.courseModel.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.alex.courseModel.entity.Grade;
import ru.alex.courseModel.entity.ActiveCourse;
import ru.alex.courseModel.entity.ActiveCourseId;
import ru.alex.courseModel.model.CourseDto;
import ru.alex.courseModel.model.GradeDto;
import ru.alex.courseModel.model.ActiveCourseDto;
import ru.alex.courseModel.model.StudentDto;
import ru.alex.courseModel.service.ActiveCourseService;

import java.util.List;

@RestController
@RequestMapping("/active-courses")
public class ActiveCourseController {

    @Autowired
    private ActiveCourseService activeCourseService;

    @GetMapping("/add-course-to-student")
    public void addCourseToStudent (@RequestParam ("courseId") int courseId,
                                    @RequestParam ("studentId") long studentId){
        activeCourseService.addStudentCourse(courseId, studentId);
    }

    @DeleteMapping("/delete-course-from-student")
    public void deleteCourseFromStudent (@RequestParam ("courseId") int courseId,
                                         @RequestParam ("studentId") long studentId){
        activeCourseService.removeStudentCourse(courseId, studentId);
    }

    @GetMapping("/available-student-courses")
    public List<CourseDto> getAvailableStudentCourses (@RequestParam ("studentId") long studentId){
        return CourseDto.getCourseDtoList(activeCourseService.getAvailableCourse(studentId));
    }

    @GetMapping("/current-student-courses")
    public List<CourseDto> getCurrentStudentCourses(@RequestParam ("studentId") long studentId){
        return CourseDto.getCourseDtoList(activeCourseService.getCurrentCourses(studentId));
    }

    @GetMapping("/current-cours-students")
    public List<StudentDto> getCourseStudents(@RequestParam ("courseId") int courseId){
        return StudentDto.getStudentDtoList(activeCourseService.getAllCourseStudents(courseId));
    }

    @GetMapping("/finished-student-courses")
    public List<CourseDto> getFinishedStudentCourses(@RequestParam ("studentId") long studentId){
        return CourseDto.getCourseDtoList(activeCourseService.getFinishedCourses(studentId));
    }

    @GetMapping("/all-student-courses")
    public List<CourseDto> getAllStudentCourses(@RequestParam ("studentId") long studentId){
        return CourseDto.getCourseDtoList(activeCourseService.getAllStudentCourses(studentId));
    }

    @GetMapping("/grades")
    public List<GradeDto> getGradesOfStudentCourse(@RequestParam ("studentId") long studentId,
                                                   @RequestParam ("courseId") int courseId){
        ActiveCourseId id = new ActiveCourseId(studentId, courseId);
        return GradeDto.getGradeDtoList(activeCourseService.getStudentCourse(id).getGrades());
    }

    @PostMapping("/grades/add")
    public List<GradeDto> addGrade(@RequestParam ("studentId") long studentId,
                                   @RequestParam ("courseId") int courseId,
                                   @RequestBody Grade grade){
        ActiveCourseId id = new ActiveCourseId(studentId, courseId);
        activeCourseService.addGrade(id, grade);
        return GradeDto.getGradeDtoList(activeCourseService.getStudentCourse(id).getGrades());
    }

    @PutMapping("/grades/update")
    public GradeDto updateGrade(@RequestParam ("id") int gradeId,
                                @RequestBody Grade grade){
        return new GradeDto(activeCourseService.updateGradeById(gradeId, grade));
    }

    @DeleteMapping("/grades/delete-{id}")
    public long deleteGrade(@PathVariable long id){
        activeCourseService.deleteGradeById(id);
        return id;
    }

    @PutMapping("/finalize-active-course")
    public ActiveCourseDto finalizeActiveCourse(@RequestParam ("finalGrade") int finalGrade,
                                                @RequestBody ActiveCourse activeCourse){
        return new ActiveCourseDto(activeCourseService.setFinalStudentCourse(activeCourse, finalGrade));
    }
}
