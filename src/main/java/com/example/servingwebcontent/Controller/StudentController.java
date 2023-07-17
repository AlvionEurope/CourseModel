package com.example.servingwebcontent.Controller;
import com.example.servingwebcontent.Repo.ProgressRepo;
import com.example.servingwebcontent.Repo.StudentRepo;
import com.example.servingwebcontent.models.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
@Controller
public class StudentController {
    @Autowired
    private StudentRepo studentRepo;

    @GetMapping("/students")
    public String student(Model model) {
        Iterable<Student> students = studentRepo.findAll();
        model.addAttribute("student", students);
        return "Students";
    }
}
