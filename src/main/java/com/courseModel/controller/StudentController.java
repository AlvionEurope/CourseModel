package com.courseModel.controller;

import com.courseModel.dto.AverageScoreDTO;
import com.courseModel.dto.CreateStudentRequest;
import com.courseModel.dto.StudentDTO;
import com.courseModel.entity.Course;
import com.courseModel.service.StudentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/v1/student")
@RequiredArgsConstructor
@Api(tags = "student")
public class StudentController {
    final StudentService service;

    @PostMapping
    @ApiOperation(value = "Создание студента")
    public StudentDTO create(@Valid @RequestBody CreateStudentRequest request) {
        return service.create(request);
    }

    @GetMapping(value = "/{grade-book}")
    @ApiOperation(value = "Получение студента по номеру зачетной книжки")
    public StudentDTO readByGradeBook(@PathVariable(name = "grade-book") int gradeBook) {
        return service.readByGradeBook(gradeBook);
    }

    @GetMapping(value = "/{grade-book}/courses")
    @ApiOperation(value = "Получение списка завершенных курсов")
    public List<Course> getFinishedCoursesByGradeBook(@PathVariable(name = "grade-book") int gradeBook) {
        return service.finishedCoursesByGradeBook(gradeBook);
    }

    @PostMapping(value = "/{grade-book}")
    @ApiOperation(value = "Получение средней успеваемости студента по номеру зачетной книжки")
    public AverageScoreDTO getAvgGrade(int gradeBook) {
        return service.getAvgGrade(gradeBook);
    }


    @PutMapping(value = "/{grade-book}")
    @ApiOperation(value = "Изменение студента по номеру зачетной книжки")
    public StudentDTO updateByGradeBook(@PathVariable(name = "grade-book") int gradeBook, @Valid @RequestBody CreateStudentRequest request) {
        return service.updateByGradeBook(gradeBook, request);
    }

    @DeleteMapping(value = "/{grade-book}")
    @ApiOperation(value = "Удаление студента по номеру зачетной книжки")
    public boolean deleteByGradeBook(@PathVariable(name = "grade-book") int gradeBook) {
        return service.deleteByGradeBook(gradeBook);
    }

    @PostMapping(value = "/{grade-book}/course/{course-number}/start-learning")
    @ApiOperation(value = "Начать обучение", notes = "изменение статуса обучения на IN_PROGRESS")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void startLearning(@PathVariable(name = "grade-book") int gradeBook, @PathVariable(name = "course-number") int courseNumber) {
        service.signUpCourse(courseNumber, gradeBook);
    }
}
