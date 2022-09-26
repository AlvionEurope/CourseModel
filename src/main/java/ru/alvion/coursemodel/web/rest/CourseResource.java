package ru.alvion.coursemodel.web.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import ru.alvion.coursemodel.repository.CourseRepository;
import ru.alvion.coursemodel.service.CourseService;
import ru.alvion.coursemodel.service.dto.CourseDTO;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class CourseResource {

    private final Logger log = LoggerFactory.getLogger(CourseResource.class);

    private static final String ENTITY_NAME = "course";

    @Value("${clientApp.name}")
    private String applicationName;

    private final CourseService courseService;

    private final CourseRepository courseRepository;

    public CourseResource(CourseService courseService, CourseRepository courseRepository) {
        this.courseService = courseService;
        this.courseRepository = courseRepository;
    }

    @PostMapping("/courses")
    public ResponseEntity<CourseDTO> createCourse(@RequestBody CourseDTO courseDTO) throws URISyntaxException {
        log.debug("REST request to save Course : {}", courseDTO);
        if (courseDTO.getId() != null) {
            throw new RuntimeException(String.format("A new course cannot already have an ID %s idexists", ENTITY_NAME));
        }
        CourseDTO result = courseService.save(courseDTO);
        return ResponseEntity
            .created(new URI("/api/courses/" + result.getId()))
            .headers(new HttpHeaders())
            .body(result);
    }

    @PutMapping("/courses/{id}")
    public ResponseEntity<CourseDTO> updateCourse(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody CourseDTO courseDTO
    ) throws URISyntaxException {
        log.debug("REST request to update Course : {}, {}", id, courseDTO);
        if (courseDTO.getId() == null) {
            throw new RuntimeException(String.format("Invalid id %s idnull", ENTITY_NAME));
        }
        if (!Objects.equals(id, courseDTO.getId())) {
            throw new RuntimeException(String.format("Invalid ID %s idinvalid", ENTITY_NAME));
        }

        if (!courseRepository.existsById(id)) {
            throw new RuntimeException(String.format("Entity not found %s idnotfound", ENTITY_NAME));
        }

        CourseDTO result = courseService.update(courseDTO);
        return ResponseEntity
            .ok()
            .headers(new HttpHeaders())
            .body(result);
    }

    @PatchMapping(value = "/courses/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<CourseDTO> partialUpdateCourse(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody CourseDTO courseDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update Course partially : {}, {}", id, courseDTO);
        if (courseDTO.getId() == null) {
            throw new RuntimeException(String.format("Invalid id %s idnull", ENTITY_NAME));
        }
        if (!Objects.equals(id, courseDTO.getId())) {
            throw new RuntimeException(String.format("Invalid ID %s idinvalid", ENTITY_NAME));
        }

        if (!courseRepository.existsById(id)) {
            throw new RuntimeException(String.format("Entity not found %s idnotfound", ENTITY_NAME));
        }

        Optional<CourseDTO> result = courseService.partialUpdate(courseDTO);

        return ResponseEntity
                .ok()
                .headers(new HttpHeaders())
                .body(result.orElseThrow(() -> {
                            return new ResponseStatusException(HttpStatus.NOT_FOUND);
                        })
                );
    }

    @GetMapping("/courses")
    public List<CourseDTO> getAllCourses() {
        log.debug("REST request to get all Courses");
        return courseService.findAll();
    }

    @GetMapping("/courses/{id}")
    public ResponseEntity<CourseDTO> getCourse(@PathVariable Long id) {
        log.debug("REST request to get Course : {}", id);
        Optional<CourseDTO> courseDTO = courseService.findOne(id);
        return ResponseEntity
                .ok()
                .headers(new HttpHeaders())
                .body(courseDTO.orElseThrow(() -> {
                            return new ResponseStatusException(HttpStatus.NOT_FOUND);
                        })
                );
    }

    @DeleteMapping("/courses/{id}")
    public ResponseEntity<Void> deleteCourse(@PathVariable Long id) {
        log.debug("REST request to delete Course : {}", id);
        courseService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(new HttpHeaders())
            .build();
    }
}
