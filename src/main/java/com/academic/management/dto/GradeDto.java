package com.academic.management.dto;

public class GradeDto {
    private Long id;
    private Long studentId;
    private String studentName;
    private String studentIdNumber;
    private Long subjectId;
    private String subjectCode;
    private String subjectName;
    private Double value;
    private String semester;
    private String academicYear;
    
    public GradeDto() {
    }
    
    public GradeDto(Long id, Long studentId, String studentName, String studentIdNumber, Long subjectId, String subjectCode, String subjectName, Double value, String semester, String academicYear) {
        this.id = id;
        this.studentId = studentId;
        this.studentName = studentName;
        this.studentIdNumber = studentIdNumber;
        this.subjectId = subjectId;
        this.subjectCode = subjectCode;
        this.subjectName = subjectName;
        this.value = value;
        this.semester = semester;
        this.academicYear = academicYear;
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
