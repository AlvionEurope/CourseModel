package ru.alvion.coursemodel.web.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import ru.alvion.coursemodel.repository.GradeAssignmentRepository;
import ru.alvion.coursemodel.service.GradeAssignmentService;
import ru.alvion.coursemodel.service.StudentService;
import ru.alvion.coursemodel.service.dto.GradeAssignmentDTO;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class GradeAssignmentResource {

    private final Logger log = LoggerFactory.getLogger(GradeAssignmentResource.class);

    private static final String ENTITY_NAME = "gradeAssignment";

    @Value("${clientApp.name}")
    private String applicationName;

    private final GradeAssignmentService gradeAssignmentService;
    private final StudentService studentService;

    private final GradeAssignmentRepository gradeAssignmentRepository;

    public GradeAssignmentResource(GradeAssignmentService gradeAssignmentService, StudentService studentService, GradeAssignmentRepository gradeAssignmentRepository) {
        this.gradeAssignmentService = gradeAssignmentService;
        this.studentService = studentService;
        this.gradeAssignmentRepository = gradeAssignmentRepository;
    }

    @PostMapping("/grade-assignments")
    public ResponseEntity<GradeAssignmentDTO> createGradeAssignment(@RequestBody GradeAssignmentDTO gradeAssignmentDTO)
        throws URISyntaxException {
        log.debug("REST request to save GradeAssignment : {}", gradeAssignmentDTO);
        if (gradeAssignmentDTO.getId() != null) {
            throw new RuntimeException(String.format("A new gradeAssignment cannot already have an ID %s idexists", ENTITY_NAME));
        }
        GradeAssignmentDTO result = gradeAssignmentService.save(gradeAssignmentDTO);

        return ResponseEntity
            .created(new URI("/api/grade-assignments/" + result.getId()))
            .headers(new HttpHeaders())
            .body(result);
    }

    @PutMapping("/grade-assignments/{id}")
    public ResponseEntity<GradeAssignmentDTO> updateGradeAssignment(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody GradeAssignmentDTO gradeAssignmentDTO
    ) throws URISyntaxException {
        log.debug("REST request to update GradeAssignment : {}, {}", id, gradeAssignmentDTO);
        if (gradeAssignmentDTO.getId() == null) {
            throw new RuntimeException(String.format("Invalid id %s idnull", ENTITY_NAME));
        }
        if (!Objects.equals(id, gradeAssignmentDTO.getId())) {
            throw new RuntimeException(String.format("Invalid ID %s idinvalid", ENTITY_NAME));
        }

        if (!gradeAssignmentRepository.existsById(id)) {
            throw new RuntimeException(String.format("Entity not found %s idnotfound", ENTITY_NAME));
        }

        GradeAssignmentDTO result = gradeAssignmentService.update(gradeAssignmentDTO);
        return ResponseEntity
            .ok()
            .headers(new HttpHeaders())
            .body(result);
    }

    @PatchMapping(value = "/grade-assignments/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<GradeAssignmentDTO> partialUpdateGradeAssignment(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody GradeAssignmentDTO gradeAssignmentDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update GradeAssignment partially : {}, {}", id, gradeAssignmentDTO);
        if (gradeAssignmentDTO.getId() == null) {
            throw new RuntimeException(String.format("Invalid id %s idnull", ENTITY_NAME));
        }
        if (!Objects.equals(id, gradeAssignmentDTO.getId())) {
            throw new RuntimeException(String.format("Invalid ID %s idinvalid", ENTITY_NAME));
        }

        if (!gradeAssignmentRepository.existsById(id)) {
            throw new RuntimeException(String.format("Entity not found %s idnotfound", ENTITY_NAME));
        }

        Optional<GradeAssignmentDTO> result = gradeAssignmentService.partialUpdate(gradeAssignmentDTO);

        return ResponseEntity
                .ok()
                .headers(new HttpHeaders())
                .body(result.orElseThrow(() -> {
                            return new ResponseStatusException(HttpStatus.NOT_FOUND);
                        })
                );
    }

    @GetMapping("/grade-assignments")
    public List<GradeAssignmentDTO> getAllGradeAssignments() {
        log.debug("REST request to get all GradeAssignments");
        return gradeAssignmentService.findAll();
    }

    @GetMapping("/grade-assignments/{id}")
    public ResponseEntity<GradeAssignmentDTO> getGradeAssignment(@PathVariable Long id) {
        log.debug("REST request to get GradeAssignment : {}", id);
        Optional<GradeAssignmentDTO> gradeAssignmentDTO = gradeAssignmentService.findOne(id);
        return ResponseEntity
                .ok()
                .headers(new HttpHeaders())
                .body(gradeAssignmentDTO.orElseThrow(() -> {
                            return new ResponseStatusException(HttpStatus.NOT_FOUND);
                        })
                );
    }

    @DeleteMapping("/grade-assignments/{id}")
    public ResponseEntity<Void> deleteGradeAssignment(@PathVariable Long id) {
        log.debug("REST request to delete GradeAssignment : {}", id);
        gradeAssignmentService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(new HttpHeaders())
            .build();
    }
}
