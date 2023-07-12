package me.rudnikov.backend.controller;

import me.rudnikov.backend.dto.create.ProfessorCreateDto;
import me.rudnikov.backend.dto.read.ProfessorDto;
import me.rudnikov.backend.service.ProfessorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import lombok.AllArgsConstructor;

import java.util.List;

@RestController
@RequestMapping(
        value = "/api/v1/professors"
)
@AllArgsConstructor
public class ProfessorController {

    private final ProfessorService professorService;

    // Create course (HTTP::POST)
    @RequestMapping(
            value = "",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Long> createProfessor(@RequestBody ProfessorCreateDto dto) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(professorService.createProfessor(dto));
    }

    // Read course by id (HTTP::GET)
    @RequestMapping(
            value = "{id}",
            method = RequestMethod.GET,
            consumes = MediaType.ALL_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<ProfessorDto> readProfessorById(@PathVariable("id") Long id) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(professorService.readProfessorById(id));
    }

    // Read all courses (HTTP::GET)
    @RequestMapping(
            value = "",
            method = RequestMethod.GET,
            consumes = MediaType.ALL_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<List<ProfessorDto>> readAllProfessors() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(professorService.readAllProfessors());
    }

    // Update course by id (HTTP::PUT)
    @RequestMapping(
            value = "{id}",
            method = RequestMethod.PUT,
            consumes = MediaType.ALL_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Boolean> updateProfessorById(
            @PathVariable("id") Long id,
            ProfessorDto dto
    ) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(professorService.updateProfessorById(id, dto));
    }

    // Delete course by id (HTTP::DELETE)
    @RequestMapping(
            value = "{id}",
            method = RequestMethod.DELETE,
            consumes = MediaType.ALL_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Boolean> deleteProfessorById(@PathVariable("id") Long id) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(professorService.deleteProfessorById(id));
    }

}