package com.example.studentperformance.controller;

import com.example.studentperformance.model.Student;
import com.example.studentperformance.repository.StudentRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class AuthController {

    private final StudentRepository studentRepository;

    AuthController(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    // Signup page
    @GetMapping("/signup")
    public String signupPage() {
        return "signup";
    }

    // Signup
    @PostMapping("/signup")
    public String signup(@RequestParam String regNo,
                         @RequestParam String name,
                         @RequestParam String department,
                         @RequestParam String password,
                         @RequestParam String confirmPassword,
                         Model model) {

        if (!password.equals(confirmPassword)) {
            model.addAttribute("error", "Passwords do not match");
            return "signup";
        }

        if (studentRepository.existsById(regNo)) {
            model.addAttribute("error", "Register number already exists");
            return "signup";
        }

        Student student = new Student();

        student.setRegNo(regNo);
        student.setName(name);
        student.setDepartment(department);
        student.setPassword(password);

        studentRepository.save(student);

        return "redirect:/";
    }

    // Login page
    @GetMapping("/")
    public String loginPage() {
        return "login";
    }

    // Login
    @PostMapping("/login")
    public String login(@RequestParam String regNo,
                        @RequestParam String password,
                        HttpSession session,
                        Model model) {

        Student student =
                studentRepository.findByRegNoAndPassword(regNo, password);

        if (student != null) {

            session.setAttribute("regNo", student.getRegNo());

            return "redirect:/record";

        } else {

            model.addAttribute("error", "Invalid Register Number or Password");

            return "login";
        }
    }
}