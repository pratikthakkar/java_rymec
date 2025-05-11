package com.example.studentmarks.controller;

import com.example.studentmarks.model.Student;
import com.example.studentmarks.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class StudentController {

    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/form")
    public String showForm(Model model) {
        model.addAttribute("student", new Student());
        return "form";
    }

    @PostMapping("/submit")
    public String submitForm(@ModelAttribute Student student) {
        studentService.saveStudent(student);
        return "redirect:/report";
    }

    @GetMapping("/report")
    public String showReport(Model model) {
        model.addAttribute("students", studentService.getAllStudents());
        return "report";
    }
}
