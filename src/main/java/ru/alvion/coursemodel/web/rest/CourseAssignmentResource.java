package ru.alvion.coursemodel.web.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import ru.alvion.coursemodel.repository.CourseAssignmentRepository;
import ru.alvion.coursemodel.service.CourseAssignmentService;
import ru.alvion.coursemodel.service.dto.CourseAssignmentDTO;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class CourseAssignmentResource {

    private final Logger log = LoggerFactory.getLogger(CourseAssignmentResource.class);

    private static final String ENTITY_NAME = "courseAssignment";

    @Value("${clientApp.name}")
    private String applicationName;

    private final CourseAssignmentService courseAssignmentService;

    private final CourseAssignmentRepository courseAssignmentRepository;

    public CourseAssignmentResource(
        CourseAssignmentService courseAssignmentService,
        CourseAssignmentRepository courseAssignmentRepository
    ) {
        this.courseAssignmentService = courseAssignmentService;
        this.courseAssignmentRepository = courseAssignmentRepository;
    }

    @PostMapping("/course-assignments")
    public ResponseEntity<CourseAssignmentDTO> createCourseAssignment(@RequestBody CourseAssignmentDTO courseAssignmentDTO)
        throws URISyntaxException {
        log.debug("REST request to save CourseAssignment : {}", courseAssignmentDTO);
        if (courseAssignmentDTO.getId() != null) {
            throw new RuntimeException(String.format("A new courseAssignment cannot already have an ID %s idexists", ENTITY_NAME));
        }
        if (courseAssignmentService.studentCannotAssignCourse(courseAssignmentDTO)){
            throw new RuntimeException(String.format("A new courseAssignment already have a Student ID %s studentexists", ENTITY_NAME));
        }
        CourseAssignmentDTO result = courseAssignmentService.save(courseAssignmentDTO);
        return ResponseEntity
            .created(new URI("/api/course-assignments/" + result.getId()))
            .headers(new HttpHeaders())
            .body(result);
    }

    @PutMapping("/course-assignments/{id}")
    public ResponseEntity<CourseAssignmentDTO> updateCourseAssignment(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody CourseAssignmentDTO courseAssignmentDTO
    ) throws URISyntaxException {
        log.debug("REST request to update CourseAssignment : {}, {}", id, courseAssignmentDTO);
        if (courseAssignmentDTO.getId() == null) {
            throw new RuntimeException(String.format("Invalid id %s idnull", ENTITY_NAME));
        }
        if (!Objects.equals(id, courseAssignmentDTO.getId())) {
            throw new RuntimeException(String.format("Invalid ID %s idinvalid", ENTITY_NAME));
        }

        if (!courseAssignmentRepository.existsById(id)) {
            throw new RuntimeException(String.format("Entity not found %s idnotfound", ENTITY_NAME));
        }

        CourseAssignmentDTO result = courseAssignmentService.update(courseAssignmentDTO);
        return ResponseEntity
            .ok()
            .headers(new HttpHeaders())
            .body(result);
    }

    @PatchMapping(value = "/course-assignments/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<CourseAssignmentDTO> partialUpdateCourseAssignment(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody CourseAssignmentDTO courseAssignmentDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update CourseAssignment partially : {}, {}", id, courseAssignmentDTO);
        if (courseAssignmentDTO.getId() == null) {
            throw new RuntimeException(String.format("Invalid id %s idnull", ENTITY_NAME));
        }
        if (!Objects.equals(id, courseAssignmentDTO.getId())) {
            throw new RuntimeException(String.format("Invalid ID %s idinvalid", ENTITY_NAME));
        }

        if (!courseAssignmentRepository.existsById(id)) {
            throw new RuntimeException(String.format("Entity not found %s idnotfound", ENTITY_NAME));
        }

        Optional<CourseAssignmentDTO> result = courseAssignmentService.partialUpdate(courseAssignmentDTO);

        return ResponseEntity
                .ok()
                .headers(new HttpHeaders())
                .body(result.orElseThrow(() -> {
                            return new ResponseStatusException(HttpStatus.NOT_FOUND);
                        })
                );
    }

    @GetMapping("/course-assignments")
    public List<CourseAssignmentDTO> getAllCourseAssignments() {
        log.debug("REST request to get all CourseAssignments");
        return courseAssignmentService.findAll();
    }

    @GetMapping("/course-assignments/{id}")
    public ResponseEntity<CourseAssignmentDTO> getCourseAssignment(@PathVariable Long id) {
        log.debug("REST request to get CourseAssignment : {}", id);
        Optional<CourseAssignmentDTO> courseAssignmentDTO = courseAssignmentService.findOne(id);
        return ResponseEntity
                .ok()
                .headers(new HttpHeaders())
                .body(courseAssignmentDTO.orElseThrow(() -> {
                            return new ResponseStatusException(HttpStatus.NOT_FOUND);
                        })
                );
    }

    @DeleteMapping("/course-assignments/{id}")
    public ResponseEntity<Void> deleteCourseAssignment(@PathVariable Long id) {
        log.debug("REST request to delete CourseAssignment : {}", id);
        courseAssignmentService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(new HttpHeaders())
            .build();
    }
}
