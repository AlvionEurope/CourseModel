package com.example.servingwebcontent.Controller;
import com.example.servingwebcontent.Repo.TeacherRepo;
import com.example.servingwebcontent.models.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
@Controller
public class TeacherController {
    @Autowired
    private TeacherRepo teacherRepo;
    @GetMapping("/teacher")
    public String teacher(Model model) {
        Iterable<Teacher> teachers = teacherRepo.findAll();
        model.addAttribute("teacher", teachers);
        return "Teacher";
    }
}
