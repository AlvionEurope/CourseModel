package me.rudnikov.backend.controller;

import me.rudnikov.backend.dto.create.CourseCreateDto;
import me.rudnikov.backend.dto.read.CourseDto;
import me.rudnikov.backend.dto.update.CourseUpdateDto;
import me.rudnikov.backend.service.CourseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import lombok.AllArgsConstructor;

import java.util.List;

@RestController
@RequestMapping(
        value = "/api/v1/courses"
)
@AllArgsConstructor
public class CourseController {
    private final CourseService courseService;

    // Create course (HTTP::POST)
    @RequestMapping(
            value = "",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Long> createCourse(@RequestBody CourseCreateDto dto) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(courseService.createCourse(dto));
    }

    // Read course by id (HTTP::GET)
    @RequestMapping(
            value = "{id}",
            method = RequestMethod.GET,
            consumes = MediaType.ALL_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<CourseDto> readCourseById(@PathVariable("id") Long id) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(courseService.readCourseById(id));
    }

    // Read all courses (HTTP::GET)
    @RequestMapping(
            value = "",
            method = RequestMethod.GET,
            consumes = MediaType.ALL_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<List<CourseDto>> readAllCourses() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(courseService.readAllCourses());
    }

    // Update course by id (HTTP::PUT)
    @RequestMapping(
            value = "{id}",
            method = RequestMethod.PUT,
            consumes = MediaType.ALL_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Boolean> updateCourseById(
            @PathVariable("id") Long id,
            @RequestBody CourseUpdateDto dto
    ) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(courseService.updateCourseById(id, dto));
    }

    // Delete course by id (HTTP::DELETE)
    @RequestMapping(
            value = "{id}",
            method = RequestMethod.DELETE,
            consumes = MediaType.ALL_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Boolean> deleteCourseById(@PathVariable("id") Long id) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(courseService.deleteCourseById(id));
    }
}
