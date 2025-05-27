package com.academic.management.dto;

import com.academic.management.model.Role;

public class FacultyDto extends UserDto {
    private Long courseId;
    private String courseName;
    
    public FacultyDto() {
        super();
    }
    
    public FacultyDto(Long id, String name, String email, Role role, Long courseId, String courseName) {
        super(id, name, email, role);
        this.courseId = courseId;
        this.courseName = courseName;
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
