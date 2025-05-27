package com.academic.management.controller;

import com.academic.management.dto.CreateEnrollmentRequest;
import com.academic.management.dto.UserDto;
import com.academic.management.model.Role;
import com.academic.management.service.StudentService;
import com.academic.management.service.UserService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/student")
public class StudentController {
    
    @Autowired
    private StudentService studentService;
    
    @Autowired
    private UserService userService;
    
    @GetMapping("/dashboard")
    public String dashboard(HttpSession session, Model model) {
        UserDto currentUser = userService.getCurrentUser(session);
        if (currentUser == null || currentUser.getRole() != Role.STUDENT) {
            return "redirect:/auth/login";
        }
        
        model.addAttribute("user", currentUser);
        return "student/dashboard";
    }
    
    @GetMapping("/grades")
    public String grades(@RequestParam(required = false, defaultValue = "1") String semester,
                         @RequestParam(required = false, defaultValue = "2023-2024") String academicYear,
                         HttpSession session, Model model) {
        UserDto currentUser = userService.getCurrentUser(session);
        if (currentUser == null || currentUser.getRole() != Role.STUDENT) {
            return "redirect:/auth/login";
        }
        
        model.addAttribute("user", currentUser);
        model.addAttribute("grades", studentService.getStudentGrades(currentUser.getId(), semester, academicYear));
        model.addAttribute("semester", semester);
        model.addAttribute("academicYear", academicYear);
        return "student/grades";
    }
    
    @GetMapping("/enrollment")
    public String enrollment(@RequestParam(required = false, defaultValue = "1") String semester,
                             @RequestParam(required = false, defaultValue = "2023-2024") String academicYear,
                             HttpSession session, Model model) {
        UserDto currentUser = userService.getCurrentUser(session);
        if (currentUser == null || currentUser.getRole() != Role.STUDENT) {
            return "redirect:/auth/login";
        }
      
        model.addAttribute("user", currentUser);
        model.addAttribute("enrollments", studentService.getStudentEnrollments(currentUser.getId(), semester, academicYear));
        model.addAttribute("semester", semester);
        model.addAttribute("academicYear", academicYear);
        return "student/enrollment";
    }
    
    @PostMapping("/enrollment/create")
    public String createEnrollment(@Valid CreateEnrollmentRequest request, HttpSession session) {
        UserDto currentUser = userService.getCurrentUser(session);
        if (currentUser == null || currentUser.getRole() != Role.STUDENT) {
            return "redirect:/auth/login";
        }
        
        studentService.createEnrollment(currentUser.getId(), request);
        return "redirect:/student/enrollment?semester=" + request.getSemester() + "&academicYear=" + request.getAcademicYear();
    }
    
    @GetMapping("/schedule")
    public String schedule(@RequestParam(required = false, defaultValue = "1") String semester,
                           @RequestParam(required = false, defaultValue = "2023-2024") String academicYear,
                           HttpSession session, Model model) {
        UserDto currentUser = userService.getCurrentUser(session);
        if (currentUser == null || currentUser.getRole() != Role.STUDENT) {
            return "redirect:/auth/login";
        }
        
        model.addAttribute("user", currentUser);
        model.addAttribute("schedules", studentService.getStudentSchedule(currentUser.getId(), semester, academicYear));
        model.addAttribute("semester", semester);
        model.addAttribute("academicYear", academicYear);
        return "student/schedule";
    }
}
