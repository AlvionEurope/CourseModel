package ru.alex.courseModel.controller;

import org.springframework.web.bind.annotation.*;
import ru.alex.courseModel.entity.Grade;
import ru.alex.courseModel.entity.AcademicPerformanceId;
import ru.alex.courseModel.model.CourseDto;
import ru.alex.courseModel.model.GradeDto;
import ru.alex.courseModel.model.AcademicPerformanceDto;
import ru.alex.courseModel.model.StudentDto;
import ru.alex.courseModel.service.AcademicPerformanceService;

import java.util.List;

@RestController
@RequestMapping("/active-courses")
public class AcademicPerformanceController {

    private final AcademicPerformanceService academicPerformanceService;

    public AcademicPerformanceController(AcademicPerformanceService academicPerformanceService) {
        this.academicPerformanceService = academicPerformanceService;
    }

    @GetMapping("/add-course-to-student")
    public void addCourseToStudent(@RequestParam ("courseId") int courseId,
                                   @RequestParam ("studentId") long studentId) {
        academicPerformanceService.addStudentCourse(courseId, studentId);
    }

    @DeleteMapping("/delete-course-from-student")
    public void deleteCourseFromStudent(@RequestParam ("courseId") int courseId,
                                        @RequestParam ("studentId") long studentId) {
        academicPerformanceService.removeStudentCourse(courseId, studentId);
    }

    @GetMapping("/current-student-courses")
    public List<CourseDto> getCurrentStudentCourses(@RequestParam ("studentId") long studentId) {
        return CourseDto.getCourseDtoList(academicPerformanceService.getFinishedCourses(studentId, false));
    }

    @GetMapping("/current-course-students")
    public List<StudentDto> getCourseStudents(@RequestParam ("courseId") int courseId) {
        return StudentDto.getStudentDtoList(academicPerformanceService.getAllCourseStudents(courseId));
    }

    @GetMapping("/finished-student-courses")
    public List<CourseDto> getFinishedStudentCourses(@RequestParam ("studentId") long studentId) {
        return CourseDto.getCourseDtoList(academicPerformanceService.getFinishedCourses(studentId, true));
    }

    @GetMapping("/all-student-courses")
    public List<CourseDto> getAllStudentCourses(@RequestParam ("studentId") long studentId) {
        return CourseDto.getCourseDtoList(academicPerformanceService.getAllStudentCourses(studentId));
    }

    @GetMapping("/grades")
    public List<GradeDto> getGradesOfStudentCourse(@RequestParam ("studentId") long studentId,
                                                   @RequestParam ("courseId") int courseId) {
        AcademicPerformanceId id = new AcademicPerformanceId(studentId, courseId);
        return GradeDto.getGradeDtoList(academicPerformanceService.get(id).getGrades());
    }

    @PostMapping("/grades/add")
    public void addGrade(@RequestParam ("studentId") long studentId,
                                   @RequestParam ("courseId") int courseId,
                                   @RequestBody Grade grade) {
        academicPerformanceService.addGrade(studentId, courseId, grade);
    }

    @PutMapping("/grades/update")
    public GradeDto updateGrade(@RequestBody Grade grade) {
        return new GradeDto(academicPerformanceService.updateGradeById(grade));
    }

    @DeleteMapping("/grades/delete-{id}")
    public void deleteGrade(@PathVariable long id) {
        academicPerformanceService.deleteGradeById(id);
    }

    @PutMapping("/finalize-active-course")
    public AcademicPerformanceDto finalizeActiveCourse(@RequestParam ("finalGrade") int finalGrade,
                                                       @RequestBody AcademicPerformanceId academicPerformanceId) {
        return new AcademicPerformanceDto(academicPerformanceService.finalizeStudentCourse(academicPerformanceId, finalGrade));
    }

    @GetMapping("/average-grade")
    public float getAverageGrade(@RequestParam ("studentId") long studentId,
                                 @RequestParam ("courseId") int courseId) {
        AcademicPerformanceId id = new AcademicPerformanceId(studentId, courseId);
        return academicPerformanceService.getCurrentAverageGrade(id);
    }

    @GetMapping("/final-grade")
    public Integer getFinalGrade(@RequestParam ("studentId") long studentId,
                                 @RequestParam ("courseId") int courseId) {
        AcademicPerformanceId id = new AcademicPerformanceId(studentId, courseId);
        return academicPerformanceService.getFinalGrade(id);
    }
}
