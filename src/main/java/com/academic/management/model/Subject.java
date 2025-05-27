package com.academic.management.model;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "subjects")
public class Subject {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String code;
    
    @Column(nullable = false)
    private String name;
    
    @Column(length = 1000)
    private String description;
    
    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;
    
    @OneToMany(mappedBy = "subject")
    private Set<Schedule> schedules = new HashSet<>();
    
    @OneToMany(mappedBy = "subject")
    private Set<Grade> grades = new HashSet<>();
    
    public Subject() {
    }
    
    public Subject(Long id, String code, String name, String description, Course course) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.description = description;
        this.course = course;
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
    
    public Course getCourse() {
        return course;
    }
    
    public void setCourse(Course course) {
        this.course = course;
    }
    
    public Set<Schedule> getSchedules() {
        return schedules;
    }
    
    public void setSchedules(Set<Schedule> schedules) {
        this.schedules = schedules;
    }
    
    public Set<Grade> getGrades() {
        return grades;
    }
    
    public void setGrades(Set<Grade> grades) {
        this.grades = grades;
    }
}
