package com.academic.management.model;

import jakarta.persistence.*;

@Entity
@Table(name = "grades")
public class Grade {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "student_id", nullable = false)
    private Student student;
    
    @ManyToOne
    @JoinColumn(name = "subject_id", nullable = false)
    private Subject subject;
    
    @Column(nullable = false)
    private Double value;
    
    @Column(nullable = false)
    private String semester;
    
    @Column(nullable = false)
    private String academicYear;
    
    public Grade() {
    }
    
    public Grade(Long id, Student student, Subject subject, Double value, String semester, String academicYear) {
        this.id = id;
        this.student = student;
        this.subject = subject;
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
    
    public Student getStudent() {
        return student;
    }
    
    public void setStudent(Student student) {
        this.student = student;
    }
    
    public Subject getSubject() {
        return subject;
    }
    
    public void setSubject(Subject subject) {
        this.subject = subject;
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
