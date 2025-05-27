package com.academic.management.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.academic.management.dto.LoginRequest;
import com.academic.management.dto.UserDto;
import com.academic.management.service.UserService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/auth")
public class AuthController {
    
    @Autowired
    private UserService userService;
    
    @GetMapping("/login")
    public String loginPage() {
        return "auth/login";
    }
    
    @PostMapping("/login")
    public String login(@RequestParam String email, @RequestParam String password, HttpSession session, Model model) {
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setEmail(email);
        loginRequest.setPassword(password);
        
        UserDto userDto = userService.login(loginRequest, session);
        
        if (userDto == null) {
            model.addAttribute("error", "Invalid Email or Password");
            return "auth/login";
        }
        
        switch (userDto.getRole()) {
            case ADMIN:
                return "redirect:/admin/dashboard";
            case FACULTY:
                return "redirect:/faculty/dashboard";
            case STUDENT:
                return "redirect:/student/dashboard";
            default:
                return "redirect:/auth/login";
        }
    }
    
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        userService.logout(session);
        return "redirect:/auth/login";
    }
}