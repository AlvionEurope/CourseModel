package ru.alex.courseModel.controller;

import org.springframework.web.bind.annotation.*;
import ru.alex.courseModel.entity.TrainingCourseId;
import ru.alex.courseModel.model.CourseDto;
import ru.alex.courseModel.model.StudentDto;
import ru.alex.courseModel.service.TrainingCourseService;

import java.util.List;

@RestController
@RequestMapping("/training")
public class TrainingCourseController {

    private final TrainingCourseService trainingCourseService;

    public TrainingCourseController(TrainingCourseService trainingCourseService) {
        this.trainingCourseService = trainingCourseService;
    }

    @DeleteMapping
    public void delete(@RequestParam("courseId") int courseId,
                       @RequestParam("studentId") long studentId) {
        trainingCourseService.removeTrainingCourse(courseId, studentId);
    }

    @GetMapping("/students-from-course")
    public List<StudentDto> getStudentsFromCourse(@RequestParam("courseId") int courseId) {
        return StudentDto.getStudentDtoList(trainingCourseService.getStudentsFromCourse(courseId));
    }

    @GetMapping("/finished-student-courses")
    public List<CourseDto> getFinishedStudentCourses(@RequestParam("studentId") long studentId) {
        return CourseDto.getCourseDtoList(trainingCourseService.getFinishedStudentCourses(studentId, true));
    }

    @GetMapping("/all-student-courses")
    public List<CourseDto> getAllStudentCourses(@RequestParam("studentId") long studentId) {
        return CourseDto.getCourseDtoList(trainingCourseService.getCoursesFromStudent(studentId));
    }

    @PutMapping("/finalize")
    public void finalizeTrainingCourse(@RequestParam("finalGrade") int finalGrade,
                                       @RequestBody TrainingCourseId trainingCourseId) {
        trainingCourseService.finalizeTrainingCourse(trainingCourseId, finalGrade);
    }

    @GetMapping("/average-grade")
    public float getAverageGrade(@RequestParam("studentId") long studentId,
                                 @RequestParam("courseId") int courseId) {
        return trainingCourseService.getAverageGrade(courseId, studentId);
    }

    @GetMapping("/final-grade")
    public Integer getFinalGrade(@RequestParam("studentId") long studentId,
                                 @RequestParam("courseId") int courseId) {
        TrainingCourseId id = new TrainingCourseId(studentId, courseId);
        return trainingCourseService.getFinalGrade(id);
    }


}
