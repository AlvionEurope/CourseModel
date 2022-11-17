package ru.wxw.coursemodel.wxw.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import ru.wxw.coursemodel.wxw.entity.Professor;
import ru.wxw.coursemodel.wxw.service.ProfessorService;

@Controller
@RequestMapping("/professor")
public class ProfessorController {
    @Autowired
    private final ProfessorService professorService;

    public ProfessorController(ProfessorService professorService) {
        this.professorService = professorService;
    }

    @GetMapping
    public String getProfessor() {
        return "professor";
    }

    @GetMapping("/all")
    public String getProfessorAll(Model model) {
        model.addAttribute("professorList", professorService.findAll());
        return "professorall";
    }

    @GetMapping("/{id}")
    public String getProfessorId(@PathVariable("id") Long id, Model model) {
        model.addAttribute("professor", professorService.findById(id));
        return "idshow";
    }

    @GetMapping("/new")
    public String newProfessor(Model model) {
        model.addAttribute("professor", new Professor());
        return "newprofessor";
    }

    @PostMapping()
    public String create(@ModelAttribute("professor") Professor professor) {
        professorService.save(professor);
        return "redirect:/professor/all";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") Long id) {
        model.addAttribute("professor", professorService.findById(id));
        return "edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("professor") Professor professor,
                         @PathVariable("id") Long id) {
        professorService.update(professor, id);
        return "redirect:/professor/all";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") Long id) {
        professorService.delete(id);
        return "redirect:/professor/all";
    }
}
