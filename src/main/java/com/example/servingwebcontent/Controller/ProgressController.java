package com.example.servingwebcontent.Controller;
import com.example.servingwebcontent.Repo.ProgressRepo;
import com.example.servingwebcontent.models.Progress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class ProgressController {
    @Autowired
    private ProgressRepo progressRepo;
    @GetMapping("/progress")
    public String progress(Model model) {
        Iterable<Progress> progresses = progressRepo.findAll();
        model.addAttribute("progress", progresses);
        return "Progress";
    }
}
