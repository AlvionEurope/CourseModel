package com.courseModel.controller;

import com.courseModel.dto.AverageScoreDTO;
import com.courseModel.dto.ScoreDTO;
import com.courseModel.dto.TeachingDTO;
import com.courseModel.service.TeachingService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/v1/teaching")
@RequiredArgsConstructor
@Api(tags = "teaching")
public class TeachingController {
    final TeachingService service;

    @PostMapping(value = "/course/{course-number}/student/{grade-book}/score")
    @ApiOperation(value = "Добавление оценки")
    void addScore(@PathVariable(name = "course-number") int courseNumber,
                  @PathVariable(name = "grade-book") int gradeBook,
                  @Valid @RequestBody ScoreDTO score) {
        service.addScore(courseNumber, gradeBook, score);
    }

    @GetMapping(value = "/course/{course-number}/student/{grade-book}/score")
    @ApiOperation(value = "Получение средней оценки по курсу")
    AverageScoreDTO getAvgScore(@PathVariable(name = "course-number") int courseNumber,
                                @PathVariable(name = "grade-book") int gradeBook) {
        return service.getAvgScore(courseNumber, gradeBook);
    }

    @PostMapping(value = "/course/{course-number}/student/{grade-book}/final-score")
    @ApiOperation(value = "Закончить обучение, поставить финальную оценку", notes = "статус обучения переходит в FINISHED")
    TeachingDTO finishTeaching(@PathVariable(name = "course-number") int courseNumber,
                               @PathVariable(name = "grade-book") int gradeBook) {
        return service.finishTeaching(courseNumber, gradeBook);
    }

    @GetMapping(value = "/course/{course-number}/student/{grade-book}")
    @ApiOperation(value = "Получение обучения по номеру курса и зачетной книжки студента")
    TeachingDTO getTeaching(@PathVariable(name = "course-number") int courseNumber,
                            @PathVariable(name = "grade-book") int gradeBook) {
        return service.getTeachingDTO(courseNumber, gradeBook);
    }
}
