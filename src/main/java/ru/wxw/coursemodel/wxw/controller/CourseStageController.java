package ru.wxw.coursemodel.wxw.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.wxw.coursemodel.wxw.dto.AcademicPerformanceDTO;
import ru.wxw.coursemodel.wxw.dto.FinalGradeDTO;
import ru.wxw.coursemodel.wxw.service.CourseStageService;

@RestController
@RequestMapping("/coursestage")
public class CourseStageController {
    @Autowired
    private final CourseStageService courseStageService;

    public CourseStageController(CourseStageService courseStageService) {
        this.courseStageService = courseStageService;
    }

    @PostMapping("/getGrade")
    public ResponseEntity<AcademicPerformanceDTO> getAcademicPerformanceStudent(@RequestParam(value = "studentId") Long studentId,
                                                                                @RequestParam(value = "courseId") Long courseId) {
        return new ResponseEntity<>(courseStageService.getPerformance(studentId, courseId), HttpStatus.OK);
    }

    @PostMapping("/getFinalGrade")
    public ResponseEntity<FinalGradeDTO> getFinalGradeStudent(@RequestParam(value = "studentId") Long studentId,
                                                              @RequestParam(value = "courseId") Long courseId) {
        return new ResponseEntity<>(courseStageService.getFinalGrade(studentId, courseId), HttpStatus.OK);
    }
}
