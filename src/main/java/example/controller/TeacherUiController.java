package example.controller;

import example.entity.Teacher;
import example.service.TeacherService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequiredArgsConstructor
@Controller
@RequestMapping("ui/teachers")
public class TeacherUiController {
    private final TeacherService teacherService;

    @GetMapping({"/", ""})
    public String showUserList(Model model) {
        model.addAttribute("teachers", teacherService.getTeachers());
        return "teacher-index";
    }

    @GetMapping("/add")
    public String showSignUpForm(Teacher teacher) {
        return "teacher-add";
    }

    @GetMapping("/edit/{id}")
    public String showEditTeacherForm(@PathVariable Integer id, Model model) {
        model.addAttribute("teacher", teacherService.getTeacher(id).orElseThrow());
        return "teacher-edit";
    }

    @GetMapping("/delete/{id}")
    public String deleteTeacher(@PathVariable Integer id) {
        teacherService.deleteTeacher(id);
        return "redirect:/ui/teachers/";
    }

    @PostMapping("/add")
    public String addUser(Teacher teacher) {
        teacherService.saveTeacher(teacher);
        return "redirect:/ui/teachers/";
    }

    @PostMapping("/update/{id}")
    public String updateTeacher(@PathVariable Integer id, Teacher teacher) {
        teacherService.updateTeacher(id, teacher);
        return "redirect:/ui/teachers/";
    }
}
