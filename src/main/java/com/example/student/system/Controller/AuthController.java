package com.example.student.system.Controller;

import com.example.student.system.Service.UserService;
import com.example.student.system.entity.User;
import com.example.student.system.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public String register(@ModelAttribute User user,
                           RedirectAttributes redirectAttributes) {

        user.setRole("USER");
        userService.register(user);

        redirectAttributes.addFlashAttribute("success",
                "Registration Successful");

        return "redirect:/login";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute User user,
                        RedirectAttributes redirectAttributes) {

        Optional<User> dbUser = userService.findByEmail(user.getEmail());

        if (dbUser.isPresent()
                && passwordEncoder.matches(user.getPassword(),
                dbUser.get().getPassword())) {

            String token = JwtUtil.generateToken(user.getEmail());

            redirectAttributes.addFlashAttribute("token", token);

            return "redirect:/dashboard";
        }

        redirectAttributes.addFlashAttribute("error",
                "Invalid Email or Password");

        return "redirect:/login";
    }
}