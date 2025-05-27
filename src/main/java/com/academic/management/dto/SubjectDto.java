package com.academic.management.dto;

public class SubjectDto {
    private Long id;
    private String code;
    private String name;
    private String description;
    private Long courseId;
    private String courseName;
    
    public SubjectDto() {
    }
    
    public SubjectDto(Long id, String code, String name, String description, Long courseId, String courseName) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.description = description;
        this.courseId = courseId;
        this.courseName = courseName;
    }
    
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
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
    
    public String getCourseName() {
        return courseName;
    }
    
    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }
}
