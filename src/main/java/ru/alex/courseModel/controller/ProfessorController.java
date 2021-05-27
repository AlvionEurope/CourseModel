package ru.alex.courseModel.controller;

import org.springframework.web.bind.annotation.*;
import ru.alex.courseModel.entity.Professor;
import ru.alex.courseModel.model.ProfessorDto;
import ru.alex.courseModel.service.ProfessorService;

import java.util.List;

@RestController
@RequestMapping("/professor")
public class ProfessorController {

    private final ProfessorService professorService;

    public ProfessorController(ProfessorService professorService) {
        this.professorService = professorService;
    }

    @GetMapping("/all")
    public List<ProfessorDto> getProfessor() {
        return ProfessorDto.getProfessorDtoList(professorService.getAll());
    }

    @GetMapping("/{id}")
    public ProfessorDto getProfessorById(@PathVariable long id) {
        return new ProfessorDto(professorService.getProfessorById(id));
    }

    @PostMapping("/add")
    public ProfessorDto addProfessor(@RequestBody Professor professor) {
        return new ProfessorDto(professorService.saveProfessor(professor));
    }

    @DeleteMapping("/del-{id}")
    public void deleteProfessor(@PathVariable long id) {
        professorService.deleteProfessor(id);
    }

    @PutMapping("/update-{id}")
    public ProfessorDto updateProfessor(@PathVariable long id, @RequestBody Professor professor) {
        return new ProfessorDto(professorService.updateProfessor(id, professor));
    }
}
