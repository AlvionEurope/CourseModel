package ru.alex.courseModel.controller;

import org.springframework.web.bind.annotation.*;
import ru.alex.courseModel.entity.Professor;
import ru.alex.courseModel.model.ProfessorDto;
import ru.alex.courseModel.service.ProfessorService;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

@RestController
@RequestMapping("/professor")
public class ProfessorController {

    private final ProfessorService professorService;

    public ProfessorController(ProfessorService professorService) {
        this.professorService = professorService;
    }

    @GetMapping("/all")
    public List<ProfessorDto> getAll() {
        return ProfessorDto.getProfessorDtoList(professorService.getAll());
    }

    @GetMapping("/{id}")
    public ProfessorDto get(@PathVariable long id) {
        return new ProfessorDto(professorService.get(id));
    }

    @PostMapping
    public void add(@RequestBody Professor professor) {
        professorService.save(professor);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable long id) {
        professorService.deleteProfessor(id);
    }

    @PutMapping("/{id}")
    public void update(@PathVariable long id,
                       @RequestBody Professor professor) {
        professorService.update(id, professor);
    }

    @PutMapping("/add-course")
    public void assignCourseToProfessor(@RequestParam("courseId") int courseId,
                                        @RequestParam("professorId") long professorId) {
        professorService.assignCourse(courseId, professorId);
    }

    @DeleteMapping("/delete-course")
    public void deleteCourseFromProfessor(@RequestParam("courseId") int courseId,
                                          @RequestParam("professorId") long professorId) {
        professorService.removeCourse(courseId, professorId);
    }

    @GetMapping("/download-report")
    public void getReport(HttpServletResponse response) throws IOException {
        byte[] bytes = professorService.getReport();
        String mimeType = "application/octet-stream";
        response.setContentType(mimeType);
        response.setContentLength(bytes.length);
        String headerKey = "Content-Disposition";
        String headerValue = String.format("attachment; filename=\"%s\"", "report.xlsx");
        response.setHeader(headerKey, headerValue);

        InputStream in = new ByteArrayInputStream(bytes);
        OutputStream out = response.getOutputStream();

        byte[] buffer = new byte[4096];
        int bytesRead;
        while ((bytesRead = in.read(buffer)) != -1) {
            out.write(buffer, 0, bytesRead);
        }

        in.close();
        out.close();
    }
}
