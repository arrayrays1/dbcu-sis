package com.academic.management.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class CreateSubjectRequest {
    @NotBlank(message = "Code is required")
    private String code;
    
    @NotBlank(message = "Name is required")
    private String name;
    
    private String description;
    
    @NotNull(message = "Course is required")
    private Long courseId;
    
    public CreateSubjectRequest() {
    }
    
    public CreateSubjectRequest(String code, String name, String description, Long courseId) {
        this.code = code;
        this.name = name;
        this.description = description;
        this.courseId = courseId;
    }
    
    public String getCode() {
        return code;
    }
    
    public void setCode(String code) {
        this.code = code;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    public Long getCourseId() {
        return courseId;
    }
    
    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }
}
