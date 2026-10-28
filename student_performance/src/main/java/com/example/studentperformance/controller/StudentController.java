package com.example.studentperformance.controller;

import com.example.studentperformance.model.Student;
import com.example.studentperformance.repository.StudentRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class StudentController {

    private final StudentRepository studentRepository;

    StudentController(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    // Record student details
    @GetMapping("/record")
    public String recordPage(HttpSession session, Model model) {

        String regNo = (String) session.getAttribute("regNo");

        if (regNo == null) {
            return "redirect:/";
        }

        Student student = studentRepository.findById(regNo).orElse(null);

        model.addAttribute("student", student);

        return "record";
    }

    // Save marks and attendance
    @PostMapping("/record")
    public String saveRecord(@RequestParam String regNo,
                             @RequestParam Integer pythonMarks,
                             @RequestParam Integer sqlMarks,
                             @RequestParam Integer javaMarks,
                             @RequestParam Double attendance) {

        Student student = studentRepository.findById(regNo).orElse(null);

        if (student != null) {

            student.setPythonMarks(pythonMarks);
            student.setPythonMarks(sqlMarks);
            student.setPythonMarks(javaMarks);
            student.setAttendance(attendance);

            studentRepository.save(student);
        }

        return "redirect:/performance";
    }

    // Performance page
    @GetMapping("/performance")
    public String performance(HttpSession session, Model model) {

        String regNo = (String) session.getAttribute("regNo");

        if (regNo == null) {
            return "redirect:/";
        }

        Student student = studentRepository.findById(regNo).orElse(null);

        model.addAttribute("student", student);

        if (student != null) {

            int total =
                    student.getPythonMarks()
                    + student.getPythonMarks()
                    + student.getPythonMarks();

            double average = total / 3.0;

            model.addAttribute("total", total);
            model.addAttribute("average", average);
        }

        return "performance";
    }
}