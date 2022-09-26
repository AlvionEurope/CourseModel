package ru.alvion.coursemodel.web.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import ru.alvion.coursemodel.repository.ProfessorRepository;
import ru.alvion.coursemodel.service.ProfessorService;
import ru.alvion.coursemodel.service.XlsxService;
import ru.alvion.coursemodel.service.dto.ProfessorDTO;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class ProfessorResource {

    private final Logger log = LoggerFactory.getLogger(ProfessorResource.class);

    private static final String ENTITY_NAME = "professor";

    @Value("${clientApp.name}")
    private String applicationName;

    private final ProfessorService professorService;
    private final XlsxService xlsxService;

    private final ProfessorRepository professorRepository;

    public ProfessorResource(ProfessorService professorService, XlsxService xlsxService, ProfessorRepository professorRepository) {
        this.professorService = professorService;
        this.xlsxService = xlsxService;
        this.professorRepository = professorRepository;
    }

    @PostMapping("/professors")
    public ResponseEntity<ProfessorDTO> createProfessor(@RequestBody ProfessorDTO professorDTO) throws URISyntaxException {
        log.debug("REST request to save Professor : {}", professorDTO);
        if (professorDTO.getId() != null) {
            throw new RuntimeException(String.format("A new professor cannot already have an ID %s idexists", ENTITY_NAME));
        }
        ProfessorDTO result = professorService.save(professorDTO);
        return ResponseEntity
                .created(new URI("/api/professors/" + result.getId()))
                .headers(new HttpHeaders())
                .body(result);
    }

    @PutMapping("/professors/{id}")
    public ResponseEntity<ProfessorDTO> updateProfessor(
            @PathVariable(value = "id", required = false) final Long id,
            @RequestBody ProfessorDTO professorDTO
    ) throws URISyntaxException {
        log.debug("REST request to update Professor : {}, {}", id, professorDTO);
        if (professorDTO.getId() == null) {
            throw new RuntimeException(String.format("Invalid id %s idnull", ENTITY_NAME));
        }
        if (!Objects.equals(id, professorDTO.getId())) {
            throw new RuntimeException(String.format("Invalid ID %s idinvalid", ENTITY_NAME));
        }

        if (!professorRepository.existsById(id)) {
            throw new RuntimeException(String.format("Entity not found %s idnotfound", ENTITY_NAME));
        }

        ProfessorDTO result = professorService.update(professorDTO);
        return ResponseEntity
                .ok()
                .headers(new HttpHeaders())
                .body(result);
    }

    @PatchMapping(value = "/professors/{id}", consumes = {"application/json", "application/merge-patch+json"})
    public ResponseEntity<ProfessorDTO> partialUpdateProfessor(
            @PathVariable(value = "id", required = false) final Long id,
            @RequestBody ProfessorDTO professorDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update Professor partially : {}, {}", id, professorDTO);
        if (professorDTO.getId() == null) {
            throw new RuntimeException(String.format("Invalid id %s idnull", ENTITY_NAME));
        }
        if (!Objects.equals(id, professorDTO.getId())) {
           throw new RuntimeException(String.format("Invalid ID %s idinvalid", ENTITY_NAME));
        }

        if (!professorRepository.existsById(id)) {
            throw new RuntimeException(String.format("Entity not found %s idnotfound", ENTITY_NAME));
        }

        Optional<ProfessorDTO> result = professorService.partialUpdate(professorDTO);

        return ResponseEntity
                .ok()
                .headers(new HttpHeaders())
                .body(result.orElseThrow(() -> {
                            return new ResponseStatusException(HttpStatus.NOT_FOUND);
                        })
                );
    }

    @GetMapping("/professors")
    public List<ProfessorDTO> getAllProfessors(@RequestParam(required = false, defaultValue = "false") boolean eagerload) {
        log.debug("REST request to get all Professors");
        return professorService.findAll();
    }

    @GetMapping("/professors/{id}")
    public ResponseEntity<ProfessorDTO> getProfessor(@PathVariable Long id) {
        log.debug("REST request to get Professor : {}", id);
        Optional<ProfessorDTO> professorDTO = professorService.findOne(id);
        return ResponseEntity
                .ok()
                .headers(new HttpHeaders())
                .body(professorDTO.orElseThrow(() -> {
                            return new ResponseStatusException(HttpStatus.NOT_FOUND);
                        })
                );
    }

    @DeleteMapping("/professors/{id}")
    public ResponseEntity<Void> deleteProfessor(@PathVariable Long id) {
        log.debug("REST request to delete Professor : {}", id);
        professorService.delete(id);
        return ResponseEntity
                .noContent()
                .headers(new HttpHeaders())
                .build();
    }

    @GetMapping(value="/professors/report")
    public ResponseEntity<byte[]> downloadProfessorReport() throws Exception {
        log.debug("REST request to get Professors report.");
        try {
            return ResponseEntity
                    .ok()
                    .headers(new HttpHeaders())
                    .body(xlsxService.generateProfessorReport());
        } catch (Exception e) {
            log.error(e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
