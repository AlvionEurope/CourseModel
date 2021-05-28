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
@RequestMapping("/academic-performance")
public class AcademicPerformanceController {

    private final AcademicPerformanceService academicPerformanceService;

    public AcademicPerformanceController(AcademicPerformanceService academicPerformanceService) {
        this.academicPerformanceService = academicPerformanceService;
    }

    @PostMapping
    public void add(@RequestParam ("courseId") int courseId,
                    @RequestParam ("studentId") long studentId) {
        academicPerformanceService.addStudentCourse(courseId, studentId);
    }

    @DeleteMapping
    public void delete(@RequestParam ("courseId") int courseId,
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

    @PutMapping("/finalize")
    public void finalizeActiveCourse(@RequestParam ("finalGrade") int finalGrade,
                                                       @RequestBody AcademicPerformanceId academicPerformanceId) {
        academicPerformanceService.finalizeStudentCourse(academicPerformanceId, finalGrade);
    }

    @GetMapping("/grades")
    public List<GradeDto> getGradesOfStudentCourse(@RequestParam ("studentId") long studentId,
                                                   @RequestParam ("courseId") int courseId) {
        AcademicPerformanceId id = new AcademicPerformanceId(studentId, courseId);
        return GradeDto.getGradeDtoList(academicPerformanceService.get(id).getGrades());
    }

    @PostMapping("/grades")
    public void addGrade(@RequestParam ("studentId") long studentId,
                                   @RequestParam ("courseId") int courseId,
                                   @RequestBody Grade grade) {
        academicPerformanceService.addGrade(studentId, courseId, grade);
    }

    @PutMapping("/grades")
    public void updateGrade(@RequestBody Grade grade) {
        academicPerformanceService.updateGradeById(grade);
    }

    @DeleteMapping("/grades/{id}")
    public void deleteGrade(@PathVariable long id) {
        academicPerformanceService.deleteGradeById(id);
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
