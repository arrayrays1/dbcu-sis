package com.academic.management.dto;

import com.academic.management.model.EnrollmentStatus;
import java.time.LocalDateTime;

public class EnrollmentDto {
    private Long id;
    private Long studentId;
    private String studentName;
    private String studentIdNumber;
    private Long subjectId;
    private String subjectCode;
    private String subjectName;
    private String semester;
    private String academicYear;
    private LocalDateTime enrollmentDate;
    private EnrollmentStatus status;
    private String rejectionReason;
    
    public EnrollmentDto() {
    }
    
    public EnrollmentDto(Long id, Long studentId, String studentName, String studentIdNumber, Long subjectId, String subjectCode, String subjectName, String semester, String academicYear, LocalDateTime enrollmentDate, EnrollmentStatus status, String rejectionReason) {
        this.id = id;
        this.studentId = studentId;
        this.studentName = studentName;
        this.studentIdNumber = studentIdNumber;
        this.subjectId = subjectId;
        this.subjectCode = subjectCode;
        this.subjectName = subjectName;
        this.semester = semester;
        this.academicYear = academicYear;
        this.enrollmentDate = enrollmentDate;
        this.status = status;
        this.rejectionReason = rejectionReason;
    }
    
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public Long getStudentId() {
        return studentId;
    }
    
    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }
    
    public String getStudentName() {
        return studentName;
    }
    
    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }
    
    public String getStudentIdNumber() {
        return studentIdNumber;
    }
    
    public void setStudentIdNumber(String studentIdNumber) {
        this.studentIdNumber = studentIdNumber;
    }
    
    public Long getSubjectId() {
        return subjectId;
    }
    
    public void setSubjectId(Long subjectId) {
        this.subjectId = subjectId;
    }
    
    public String getSubjectCode() {
        return subjectCode;
    }
    
    public void setSubjectCode(String subjectCode) {
        this.subjectCode = subjectCode;
    }
    
    public String getSubjectName() {
        return subjectName;
    }
    
    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
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
    
    public LocalDateTime getEnrollmentDate() {
        return enrollmentDate;
    }
    
    public void setEnrollmentDate(LocalDateTime enrollmentDate) {
        this.enrollmentDate = enrollmentDate;
    }
    
    public EnrollmentStatus getStatus() {
        return status;
    }
    
    public void setStatus(EnrollmentStatus status) {
        this.status = status;
    }
    
    public String getRejectionReason() {
        return rejectionReason;
    }
    
    public void setRejectionReason(String rejectionReason) {
        this.rejectionReason = rejectionReason;
    }
}
