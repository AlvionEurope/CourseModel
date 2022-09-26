package ru.alvion.coursemodel.web.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import ru.alvion.coursemodel.domain.CourseAssignment;
import ru.alvion.coursemodel.domain.Student;
import ru.alvion.coursemodel.repository.StudentRepository;
import ru.alvion.coursemodel.service.CourseAssignmentService;
import ru.alvion.coursemodel.service.StudentService;
import ru.alvion.coursemodel.service.dto.StudentDTO;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class StudentResource {

    private final Logger log = LoggerFactory.getLogger(StudentResource.class);

    private static final String ENTITY_NAME = "student";

    @Value("${clientApp.name}")
    private String applicationName;

    private final StudentService studentService;
    private final CourseAssignmentService courseAssignmentService;

    private final StudentRepository studentRepository;

    public StudentResource(StudentService studentService, CourseAssignmentService courseAssignmentService, StudentRepository studentRepository) {
        this.studentService = studentService;
        this.courseAssignmentService = courseAssignmentService;
        this.studentRepository = studentRepository;
    }

    @PostMapping("/students")
    public ResponseEntity<StudentDTO> createStudent(@RequestBody StudentDTO studentDTO) throws URISyntaxException {
        log.debug("REST request to save Student : {}", studentDTO);
        if (studentDTO.getId() != null) {
            throw new RuntimeException(String.format("A new student cannot already have an ID %s idexists", ENTITY_NAME));
        }
        StudentDTO result = studentService.save(studentDTO);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type","application/json");
        return ResponseEntity
            .created(new URI("/api/students/" + result.getId()))
            .headers(headers)
            .body(result);
    }

    @PutMapping("/students/{id}")
    public ResponseEntity<StudentDTO> updateStudent(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody StudentDTO studentDTO
    ) throws URISyntaxException {
        log.debug("REST request to update Student : {}, {}", id, studentDTO);
        if (studentDTO.getId() == null) {
            throw new RuntimeException(String.format("Invalid id %s idnull", ENTITY_NAME));
        }
        if (!Objects.equals(id, studentDTO.getId())) {
            throw new RuntimeException(String.format("Invalid ID %s idinvalid", ENTITY_NAME));
        }

        if (!studentRepository.existsById(id)) {
            throw new RuntimeException(String.format("Entity not found %s idnotfound", ENTITY_NAME));
        }

        StudentDTO result = studentService.update(studentDTO);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type","application/json");
        return ResponseEntity
            .ok()
            .headers(headers)
            .body(result);
    }

    @PatchMapping(value = "/students/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<StudentDTO> partialUpdateStudent(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody StudentDTO studentDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update Student partially : {}, {}", id, studentDTO);
        if (studentDTO.getId() == null) {
            throw new RuntimeException(String.format("Invalid id %s idnull", ENTITY_NAME));
        }
        if (!Objects.equals(id, studentDTO.getId())) {
            throw new RuntimeException(String.format("Invalid ID %s idinvalid", ENTITY_NAME));
        }

        if (!studentRepository.existsById(id)) {
            throw new RuntimeException(String.format("Entity not found %s idnotfound", ENTITY_NAME));
        }

        Optional<StudentDTO> result = studentService.partialUpdate(studentDTO);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type","application/json");

        return ResponseEntity
                .ok()
                .headers(headers)
                .body(result.orElseThrow(() -> {
                            return new ResponseStatusException(HttpStatus.NOT_FOUND);
                        })
                );
    }

    @GetMapping("/students")
    public List<StudentDTO> getAllStudents() {
        log.debug("REST request to get all Students");
        return studentService.findAll();
    }

    @GetMapping("/students/{id}/course-assignments")
    public List<CourseAssignment> getAllCourseAssignmentsByStudent(@PathVariable Long id) {
        log.debug("REST request to get all CourseAssignments for Student : {}", id);
        Student student = studentRepository.findById(id).orElseThrow();
        return courseAssignmentService.findAllByStudent(student);
    }

    @GetMapping("/students/{id}")
    public ResponseEntity<StudentDTO> getStudent(@PathVariable Long id) {
        log.debug("REST request to get Student : {}", id);
        Optional<StudentDTO> studentDTO = studentService.findOne(id);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type","application/json");
        return ResponseEntity
                .ok()
                .headers(headers)
                .body(studentDTO.orElseThrow(() -> {
                            return new ResponseStatusException(HttpStatus.NOT_FOUND);
                        })
                );
    }

    @DeleteMapping("/students/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable Long id) {
        log.debug("REST request to delete Student : {}", id);
        studentService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(new HttpHeaders())
            .build();
    }
}
