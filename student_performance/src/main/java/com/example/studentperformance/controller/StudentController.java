
package com.example.studentperformance.controller;

import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.studentperformance.model.Student;
import com.example.studentperformance.model.Student_Marks;
import com.example.studentperformance.model.Attendance;

import com.example.studentperformance.repository.StudentRepository;
import com.example.studentperformance.repository.Student_MarksRepository;
import com.example.studentperformance.repository.AttendanceRepository;

@Controller
public class StudentController {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private Student_MarksRepository marksRepository;

    @Autowired
    private AttendanceRepository attendanceRepository;


    // PERFORMANCE CATEGORY

    private String getPerformance(double value) {

        if (value >= 75) {
            return "Good";
        } else if (value >= 50) {
            return "Average";
        } else {
            return "Poor";
        }
    }


    // 1. DISPLAY STUDENT RECORD

    @GetMapping("/record")
    public String recordPage(HttpSession session, Model model) {

        Integer regNo = (Integer) session.getAttribute("regNo");

        if (regNo == null) {
            return "redirect:/login";
        }

        Student student = studentRepository.findById(regNo)
                .orElse(null);

        if (student == null) {
            return "redirect:/login";
        }

        Student_Marks marks = marksRepository.findById(regNo)
                .orElse(null);

        Attendance attendance = attendanceRepository.findById(regNo)
                .orElse(null);

        model.addAttribute("student", student);
        model.addAttribute("marks", marks);
        model.addAttribute("attendance", attendance);

        return "record";
    }


    // 2. SAVE OR UPDATE STUDENT MARKS

    @PostMapping("/record")
    public String saveRecord(

            @RequestParam Integer regNo,
            @RequestParam Integer pythonMarks,
            @RequestParam Integer sqlMarks,
            @RequestParam Integer javaMarks,

            HttpSession session,
            RedirectAttributes redirectAttributes) {

        if (session.getAttribute("regNo") == null ||
                !session.getAttribute("regNo").equals(regNo)) {
            return "redirect:/login";
        }

        if (pythonMarks < 0 || pythonMarks > 100 ||
                sqlMarks < 0 || sqlMarks > 100 ||
                javaMarks < 0 || javaMarks > 100) {

            redirectAttributes.addFlashAttribute(
                    "error", "Enter marks between 0 and 100.");

            return "redirect:/record";
        }

        Student student = studentRepository.findById(regNo)
                .orElse(null);

        if (student == null) {
            return "redirect:/login";
        }

        Student_Marks marks = marksRepository.findById(regNo)
                .orElseGet(Student_Marks::new);

        marks.setStudentId(regNo);
        marks.setPythonMarks(pythonMarks);
        marks.setJavaMarks(javaMarks);
        marks.setSqlMarks(sqlMarks);

        marksRepository.save(marks);

        redirectAttributes.addFlashAttribute(
                "success", "Marks saved successfully!");

        return "redirect:/record";
    }


    // 3. SAVE OR UPDATE ATTENDANCE

    @PostMapping("/attendance")
    public String saveAttendance(

            @RequestParam Integer daysPresent,
            @RequestParam Integer totalDays,

            HttpSession session,
            RedirectAttributes redirectAttributes) {

        Integer regNo = (Integer) session.getAttribute("regNo");

        if (regNo == null) {
            return "redirect:/login";
        }

        if (daysPresent < 0 || totalDays <= 0 ||
                daysPresent > totalDays) {

            redirectAttributes.addFlashAttribute(
                    "attendanceError",
                    "Enter valid attendance details.");

            return "redirect:/performance";
        }

        Attendance attendance = attendanceRepository.findById(regNo)
                .orElseGet(Attendance::new);

        double percentage =
                (daysPresent * 100.0) / totalDays;

        attendance.setStudentId(regNo);
        attendance.setDaysPresent(daysPresent);
        attendance.setAttendance(percentage);

        attendanceRepository.save(attendance);

        redirectAttributes.addFlashAttribute(
                "attendanceSuccess",
                "Attendance saved successfully!");

        return "redirect:/performance";
    }


    // 4. PERFORMANCE ANALYSIS

    @GetMapping("/performance")
    public String performancePage(
            HttpSession session,
            Model model) {

        Integer regNo = (Integer) session.getAttribute("regNo");

        if (regNo == null) {
            return "redirect:/login";
        }

        Student student = studentRepository.findById(regNo)
                .orElse(null);

        if (student == null) {
            return "redirect:/login";
        }

        Student_Marks marks = marksRepository.findById(regNo)
                .orElse(null);

        Attendance attendance =
                attendanceRepository.findById(regNo)
                .orElse(null);

        model.addAttribute("student", student);
        model.addAttribute("marks", marks);
        model.addAttribute("attendance", attendance);


        // CHECK MARKS

        if (marks == null ||
                marks.getPythonMarks() == null ||
                marks.getJavaMarks() == null ||
                marks.getSqlMarks() == null) {

            model.addAttribute("marksAvailable", false);

            return "performance";
        }

        model.addAttribute("marksAvailable", true);


        // GET SUBJECT MARKS

        int python = marks.getPythonMarks();
        int java = marks.getJavaMarks();
        int sql = marks.getSqlMarks();


        // CALCULATE TOTAL

        int total = python + java + sql;


        // CALCULATE AVERAGE

        double average = total / 3.0;


        // CALCULATE PERCENTAGE

        double percentage = (total / 300.0) * 100;


        // SUBJECT PERFORMANCE

        String pythonResult = getPerformance(python);

        String javaResult = getPerformance(java);

        String sqlResult = getPerformance(sql);


        // OVERALL PERFORMANCE

        String overallResult = getPerformance(percentage);


        // SEND MARKS TO HTML

        model.addAttribute("total", total);

        model.addAttribute("average", average);

        model.addAttribute("percentage", percentage);

        model.addAttribute("pythonResult", pythonResult);

        model.addAttribute("javaResult", javaResult);

        model.addAttribute("sqlResult", sqlResult);

        model.addAttribute("overallResult", overallResult);


        // ATTENDANCE PERFORMANCE

        if (attendance != null &&
                attendance.getAttendance() != null) {

            String attendanceResult =
                    getPerformance(attendance.getAttendance());

            model.addAttribute(
                    "attendanceResult", attendanceResult);
        } else {

            model.addAttribute(
                    "attendanceResult", "Not Available");
        }


        // CONSOLE OUTPUT

        System.out.println("Student ID: " + regNo);
        System.out.println("Total: " + total);
        System.out.println("Average: " + average);
        System.out.println("Percentage: " + percentage);

        System.out.println("Python: " + pythonResult);
        System.out.println("Java: " + javaResult);
        System.out.println("SQL: " + sqlResult);

        System.out.println("Overall: " + overallResult);

        return "performance";
    }

}
