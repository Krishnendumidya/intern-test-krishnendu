package com.example.student.system.Controller;
import com.example.student.system.Service.StudentService;
import com.example.student.system.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PageController {

    @Autowired
    private StudentService studentService;

    @GetMapping("/")
    public String home() {
        return "login";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/register")
    public String register() {
        return "register";
    }

    @GetMapping("/dashboard")
    public String dashboard(Model model) {

        model.addAttribute("totalStudents",
                studentService.countStudents());

        model.addAttribute("students",
                studentService.getRecentStudents());

        return "dashboard";
    }

    @GetMapping("/studentsPage")
    public String students(@RequestParam(required = false) String keyword,
                           Model model) {

        model.addAttribute("students",
                studentService.searchStudents(keyword));

        model.addAttribute("keyword", keyword);

        return "students";
    }

    @GetMapping("/addStudent")
    public String addStudent(Model model) {

        model.addAttribute("student", new Student());

        return "addStudent";
    }

    @GetMapping("/students/edit/{id}")
    public String editStudent(@PathVariable Long id,
                              Model model) {

        Student student = studentService
                .getStudentById(id)
                .orElseThrow(() ->
                        new RuntimeException("Student Not Found"));

        model.addAttribute("student", student);

        return "editStudent";
    }

}

