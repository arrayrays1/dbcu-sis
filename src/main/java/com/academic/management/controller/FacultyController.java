package com.academic.management.controller;

import com.academic.management.dto.CreateGradeRequest;
import com.academic.management.dto.UserDto;
import com.academic.management.model.Role;
import com.academic.management.service.FacultyService;
import com.academic.management.service.UserService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/faculty")
public class FacultyController {
    
    @Autowired
    private FacultyService facultyService;
    
    @Autowired
    private UserService userService;
    
    @GetMapping("/dashboard")
    public String dashboard(HttpSession session, Model model) {
        UserDto currentUser = userService.getCurrentUser(session);
        if (currentUser == null || currentUser.getRole() != Role.FACULTY) {
            return "redirect:/auth/login";
        }
        
        model.addAttribute("user", currentUser);
        return "faculty/dashboard";
    }
    
    @GetMapping("/schedule")
    public String schedule(@RequestParam(required = false, defaultValue = "1") String semester,
                           @RequestParam(required = false, defaultValue = "2023-2024") String academicYear,
                           HttpSession session, Model model) {
        UserDto currentUser = userService.getCurrentUser(session);
        if (currentUser == null || currentUser.getRole() != Role.FACULTY) {
            return "redirect:/auth/login";
        }
        
        model.addAttribute("user", currentUser);
        model.addAttribute("schedules", facultyService.getFacultySchedule(currentUser.getId(), semester, academicYear));
        model.addAttribute("semester", semester);
        model.addAttribute("academicYear", academicYear);
        return "faculty/schedule";
    }
    
    @GetMapping("/grades")
    public String grades(@RequestParam Long subjectId,
                         @RequestParam(required = false, defaultValue = "1") String semester,
                         @RequestParam(required = false, defaultValue = "2023-2024") String academicYear,
                         HttpSession session, Model model) {
        
        UserDto currentUser = userService.getCurrentUser(session);
        if (currentUser == null || currentUser.getRole() != Role.FACULTY) {
            return "redirect:/auth/login";
        }

        model.addAttribute("user", currentUser);
        model.addAttribute("grades", facultyService.getGradesBySubject(subjectId, semester, academicYear));
        model.addAttribute("subjectId", subjectId);
        model.addAttribute("semester", semester);
        model.addAttribute("academicYear", academicYear);
        return "faculty/grades";
    }
    
    @PostMapping("/grades/create")
    public String createGrade(@Valid CreateGradeRequest request, HttpSession session) {
        UserDto currentUser = userService.getCurrentUser(session);
        if (currentUser == null || currentUser.getRole() != Role.FACULTY) {
            return "redirect:/auth/login";
        }
        
        facultyService.createOrUpdateGrade(request);
        return "redirect:/faculty/grades?subjectId=" + request.getSubjectId() + 
               "&semester=" + request.getSemester() + 
               "&academicYear=" + request.getAcademicYear();
    }
}
