package ru.alex.courseModel.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.alex.courseModel.entity.Professor;
import ru.alex.courseModel.model.ProfessorDto;
import ru.alex.courseModel.service.ProfessorService;

import java.util.List;

@RestController
@RequestMapping("/professor")
public class ProfessorController {

    @Autowired
    private ProfessorService professorService;

    @GetMapping("/all")
    public List<ProfessorDto> getProfessor(){
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
    public long deleteProfessor(@PathVariable long id){
        professorService.deleteProfessor(id);
        return id;
    }

    @PutMapping("/update-{id}")
    public ProfessorDto updateProfessor(@PathVariable long id, @RequestBody Professor professor){
        return new ProfessorDto(professorService.updateProfessor(id, professor));
    }
}
