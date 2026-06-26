package com.example.student.system.Controller;
import com.example.student.system.Service.StudentService;
import com.example.student.system.entity.Student;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @PostMapping
    public String saveStudent(@Valid @ModelAttribute("student") Student student,
                              BindingResult result,
                              Model model,
                              RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return "addStudent";
        }

        try {

            studentService.saveStudent(student);

            redirectAttributes.addFlashAttribute(
                    "success",
                    "Student Added Successfully");

            return "redirect:/studentsPage";

        } catch (RuntimeException e) {

            model.addAttribute("error", e.getMessage());

            return "addStudent";
        }

    }

    @PostMapping("/update/{id}")
    public String updateStudent(@PathVariable Long id,
                                @Valid @ModelAttribute("student") Student student,
                                BindingResult result) {

        if (result.hasErrors()) {
            return "editStudent";
        }

        student.setId(id);

        studentService.updateStudent(student);

        return "redirect:/studentsPage";
    }
    @GetMapping("/delete/{id}")
    public String deleteStudent(@PathVariable Long id) {

        studentService.deleteStudent(id);

        return "redirect:/studentsPage";
    }

}
