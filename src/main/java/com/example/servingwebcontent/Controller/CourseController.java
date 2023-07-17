package com.example.servingwebcontent.Controller;
import com.example.servingwebcontent.Repo.CourseRepo;
import com.example.servingwebcontent.models.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestParam;
@Controller
public class CourseController {
    @Autowired
    private CourseRepo courseRepo;
    @GetMapping("/courses")
    public String courses(Model model) {
        Iterable<Course> courses1 = courseRepo.findAll();
        model.addAttribute("course", courses1);
        return "Courses";
    }
}
