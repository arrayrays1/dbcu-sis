package com.academic.management.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class CreateGradeRequest {
    @NotNull(message = "Student is required")
    private Long studentId;
    
    @NotNull(message = "Subject is required")
    private Long subjectId;
    
    @NotNull(message = "Grade value is required")
    private Double value;
    
    @NotBlank(message = "Semester is required")
    private String semester;
    
    @NotBlank(message = "Academic year is required")
    private String academicYear;
    
    public CreateGradeRequest() {
    }
    
    public CreateGradeRequest(Long studentId, Long subjectId, Double value, String semester, String academicYear) {
        this.studentId = studentId;
        this.subjectId = subjectId;
        this.value = value;
        this.semester = semester;
        this.academicYear = academicYear;
    }
    
    public Long getStudentId() {
        return studentId;
    }
    
    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }
    
    public Long getSubjectId() {
        return subjectId;
    }
    
    public void setSubjectId(Long subjectId) {
        this.subjectId = subjectId;
    }
    
    public Double getValue() {
        return value;
    }
    
    public void setValue(Double value) {
        this.value = value;
    }
    
    public String getSemester() {
        return semester;
    }
    
    public void setSemester(String semester) {
        this.semester = semester;
    }
    
    public String getAcademicYear() {
        return academicYear;
    }
    
    public void setAcademicYear(String academicYear) {
        this.academicYear = academicYear;
    }
}
