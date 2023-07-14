package me.rudnikov.backend.controller;

import me.rudnikov.backend.dto.create.CourseProgressCreateDto;
import me.rudnikov.backend.dto.read.CourseProgressDto;
import me.rudnikov.backend.dto.update.CourseProgressUpdateDto;
import me.rudnikov.backend.service.CourseProgressService;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import lombok.AllArgsConstructor;

import java.util.List;

@RestController
@RequestMapping(
        value = "/api/v1/course_progresses"
)
@AllArgsConstructor
public class CourseProgressController {
    private final CourseProgressService courseProgressService;

    // Create course progress (HTTP::POST)
    @RequestMapping(
            value = "",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Long> createCourseProgress(@RequestBody CourseProgressCreateDto dto) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(courseProgressService.createCourseProgress(dto));
    }

    // Read course progress by id (HTTP::GET)
    @RequestMapping(
            value = "{id}",
            method = RequestMethod.GET,
            consumes = MediaType.ALL_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<CourseProgressDto> readCourseProgressById(@PathVariable("id") Long id) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(courseProgressService.readCourseProgressById(id));
    }

    // Read all courses (HTTP::GET)
    @RequestMapping(
            value = "",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<List<CourseProgressDto>> readAllCourseProgresses() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(courseProgressService.readAllCourseProgresses());
    }

    // Update course progress by id (HTTP::PUT)
    @RequestMapping(
            value = "{id}",
            method = RequestMethod.PUT,
            consumes = MediaType.ALL_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Boolean> updateCourseProgressById(
            @PathVariable("id") Long id,
            @RequestBody CourseProgressUpdateDto dto
    ) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(courseProgressService.updateCourseProgressById(id, dto));
    }

    // Delete course progress by id (HTTP::DELETE)
    @RequestMapping(
            value = "{id}",
            method = RequestMethod.DELETE,
            consumes = MediaType.ALL_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Boolean> deleteCourseProgressById(@PathVariable("id") Long id) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(courseProgressService.deleteCourseProgressById(id));
    }
}