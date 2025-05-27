package com.academic.management.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class CreateEnrollmentRequest {
    @NotNull(message = "Subject is required")
    private Long subjectId;
    
    @NotBlank(message = "Semester is required")
    private String semester;
    
    @NotBlank(message = "Academic year is required")
    private String academicYear;
    
    public CreateEnrollmentRequest() {
    }
    
    public CreateEnrollmentRequest(Long subjectId, String semester, String academicYear) {
        this.subjectId = subjectId;
        this.semester = semester;
        this.academicYear = academicYear;
    }
    
    public Long getSubjectId() {
        return subjectId;
    }
    
    public void setSubjectId(Long subjectId) {
        this.subjectId = subjectId;
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
