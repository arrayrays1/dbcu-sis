package com.academic.management.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.web.multipart.MultipartFile;

public class CreateStudentRequest {
    @NotBlank(message = "Name is required")
    private String name;
    
    @NotBlank(message = "Student ID is required")
    private String studentId;
    
    @NotNull(message = "Course is required")
    private Long courseId;
    
    @NotBlank(message = "Email is required")
    @Email(message = "Email should be valid")
    private String email;
    
    @NotBlank(message = "Password is required")
    private String password;

    
    public CreateStudentRequest() {
    }
    
    public CreateStudentRequest(String name, String studentId, Long courseId, String email, String password) {
        this.name = name;
        this.studentId = studentId;
        this.courseId = courseId;
        this.email = email;
        this.password = password;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getStudentId() {
        return studentId;
    }
    
    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }
    
    public Long getCourseId() {
        return courseId;
    }
    
    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getPassword() {
        return password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
}
