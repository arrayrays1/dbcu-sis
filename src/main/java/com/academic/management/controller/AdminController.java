package com.academic.management.controller;

import com.academic.management.dto.*;
import com.academic.management.model.Role;
import com.academic.management.model.Student;
import com.academic.management.service.AdminService;
import com.academic.management.service.UserService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/admin")
public class AdminController {
    
    @Autowired
    private AdminService adminService;
    
    @Autowired
    private UserService userService;
    
    @GetMapping("/dashboard")
    public String dashboard(HttpSession session, Model model) {
        UserDto currentUser = userService.getCurrentUser(session);
        if (currentUser == null || currentUser.getRole() != Role.ADMIN) {
            return "redirect:/auth/login";
        }
        
        model.addAttribute("user", currentUser);
        model.addAttribute("pendingEnrollments", adminService.getPendingEnrollments());
        return "admin/dashboard";
    }
    
    @GetMapping("/students")
    public String students(HttpSession session, Model model) {
        UserDto currentUser = userService.getCurrentUser(session);
        if (currentUser == null || currentUser.getRole() != Role.ADMIN) {
            return "redirect:/auth/login";
        }
        
        model.addAttribute("user", currentUser);
        model.addAttribute("students", adminService.getAllStudents());
        model.addAttribute("courses", adminService.getAllCourses());
        return "admin/students";
    }
    
    @GetMapping("/faculty")
    public String faculty(HttpSession session, Model model) {
        UserDto currentUser = userService.getCurrentUser(session);
        if (currentUser == null || currentUser.getRole() != Role.ADMIN) {
            return "redirect:/auth/login";
        }
        
        model.addAttribute("user", currentUser);
        model.addAttribute("faculty", adminService.getAllFaculty());
        model.addAttribute("courses", adminService.getAllCourses());
        return "admin/faculty";
    }
    
    @GetMapping("/courses")
    public String courses(HttpSession session, Model model) {
        UserDto currentUser = userService.getCurrentUser(session);
        if (currentUser == null || currentUser.getRole() != Role.ADMIN) {
            return "redirect:/auth/login";
        }
        
        model.addAttribute("user", currentUser);
        model.addAttribute("courses", adminService.getAllCourses());
        return "admin/courses";
    }
    
    @GetMapping("/subjects")
    public String subjects(HttpSession session, Model model) {
        UserDto currentUser = userService.getCurrentUser(session);
        if (currentUser == null || currentUser.getRole() != Role.ADMIN) {
            return "redirect:/auth/login";
        }
        
        model.addAttribute("user", currentUser);
        model.addAttribute("subjects", adminService.getAllSubjects());
        model.addAttribute("courses", adminService.getAllCourses());
        return "admin/subjects";
    }
    
    @GetMapping("/schedules")
    public String schedules(HttpSession session, Model model) {
        UserDto currentUser = userService.getCurrentUser(session);
        if (currentUser == null || currentUser.getRole() != Role.ADMIN) {
            return "redirect:/auth/login";
        }
        
        model.addAttribute("user", currentUser);
        model.addAttribute("schedules", adminService.getAllSchedules());
        model.addAttribute("subjects", adminService.getAllSubjects());
        model.addAttribute("faculty", adminService.getAllFaculty());
        return "admin/schedules";
    }
    
    @GetMapping("/enrollments")
    public String enrollments(HttpSession session, Model model) {
        UserDto currentUser = userService.getCurrentUser(session);
        if (currentUser == null || currentUser.getRole() != Role.ADMIN) {
            return "redirect:/auth/login";
        }
        
        model.addAttribute("user", currentUser);
        model.addAttribute("pendingEnrollments", adminService.getPendingEnrollments());
        return "admin/enrollments";
    }
    
    @PostMapping("/students/create")
    public String createStudent(@Valid CreateStudentRequest request, HttpSession session) {
        UserDto currentUser = userService.getCurrentUser(session);
        if (currentUser == null || currentUser.getRole() != Role.ADMIN) {
            return "redirect:/auth/login";
        }
        
        adminService.createStudent(request);
        return "redirect:/admin/students";
    }
    
    @PostMapping("/faculty/create")
    public String createFaculty(@Valid CreateFacultyRequest request, HttpSession session) {
        UserDto currentUser = userService.getCurrentUser(session);
        if (currentUser == null || currentUser.getRole() != Role.ADMIN) {
            return "redirect:/auth/login";
        }
        
        adminService.createFaculty(request);
        return "redirect:/admin/faculty";
    }
    
    @PostMapping("/courses/create")
    public String createCourse(@Valid CreateCourseRequest request, HttpSession session) {
        UserDto currentUser = userService.getCurrentUser(session);
        if (currentUser == null || currentUser.getRole() != Role.ADMIN) {
            return "redirect:/auth/login";
        }
        
        adminService.createCourse(request);
        return "redirect:/admin/courses";
    }
    
    @PostMapping("/subjects/create")
    public String createSubject(@Valid CreateSubjectRequest request, HttpSession session) {
        UserDto currentUser = userService.getCurrentUser(session);
        if (currentUser == null || currentUser.getRole() != Role.ADMIN) {
            return "redirect:/auth/login";
        }
        
        adminService.createSubject(request);
        return "redirect:/admin/subjects";
    }
    
    @PostMapping("/schedules/create")
    public String createSchedule(@Valid CreateScheduleRequest request, HttpSession session) {
        UserDto currentUser = userService.getCurrentUser(session);
        if (currentUser == null || currentUser.getRole() != Role.ADMIN) {
            return "redirect:/auth/login";
        }
        
        adminService.createSchedule(request);
        return "redirect:/admin/schedules";
    }
    
    @PostMapping("/enrollments/{id}/update-status")
    public String updateEnrollmentStatus(@PathVariable("id") Long enrollmentId, 
                                         @Valid UpdateEnrollmentStatusRequest request, 
                                         HttpSession session) {
        UserDto currentUser = userService.getCurrentUser(session);
        if (currentUser == null || currentUser.getRole() != Role.ADMIN) {
            return "redirect:/auth/login";
        }
        
        adminService.updateEnrollmentStatus(enrollmentId, request);
        return "redirect:/admin/enrollments";
    }
}
