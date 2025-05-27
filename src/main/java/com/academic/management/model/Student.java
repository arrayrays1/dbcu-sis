package com.academic.management.model;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "students")
public class Student extends User {
    
    @Column(nullable = false, unique = true)
    private String studentId;
    
    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;
    
    @OneToMany(mappedBy = "student")
    private Set<Grade> grades = new HashSet<>();
    
    @OneToMany(mappedBy = "student")
    private Set<Enrollment> enrollments = new HashSet<>();

    public Student() {
        super();
    }
    
    public Student(Long id, String name, String email, String password, Role role, String studentId, Course course) {
        super(id, name, email, password, role);
        this.studentId = studentId;
        this.course = course;
    }
    
    public String getStudentId() {
        return studentId;
    }
    
    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }
    
    public Course getCourse() {
        return course;
    }
    
    public void setCourse(Course course) {
        this.course = course;
    }
    
    public Set<Grade> getGrades() {
        return grades;
    }
    
    public void setGrades(Set<Grade> grades) {
        this.grades = grades;
    }
    
    public Set<Enrollment> getEnrollments() {
        return enrollments;
    }
    
    public void setEnrollments(Set<Enrollment> enrollments) {
        this.enrollments = enrollments;
    }
}
