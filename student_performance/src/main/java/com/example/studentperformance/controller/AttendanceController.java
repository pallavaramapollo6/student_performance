package com.example.studentperformance.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import com.example.studentperformance.model.Student;
import com.example.studentperformance.repository.StudentRepository;

@Controller
public class AttendanceController {

    private final StudentRepository studentRepository;

    public AttendanceController(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @GetMapping("/")
    public String showPage(Model model) {
        model.addAttribute("student", new Student());
        model.addAttribute("students", studentRepository.findAll());
        return "attendance";
    }
}

