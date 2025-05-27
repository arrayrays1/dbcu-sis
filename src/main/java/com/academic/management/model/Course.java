package com.academic.management.model;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "courses")
public class Course {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false, unique = true)
    private String code;
    
    @Column(nullable = false)
    private String name;
    
    @Column(length = 1000)
    private String description;
    
    @OneToMany(mappedBy = "course")
    private Set<Student> students = new HashSet<>();
    
    @OneToMany(mappedBy = "course")
    private Set<Faculty> faculty = new HashSet<>();
    
    @OneToMany(mappedBy = "course")
    private Set<Subject> subjects = new HashSet<>();
    
    public Course() {
    }
    
    public Course(Long id, String code, String name, String description) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.description = description;
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
    
    public Set<Student> getStudents() {
        return students;
    }
    
    public void setStudents(Set<Student> students) {
        this.students = students;
    }
    
    public Set<Faculty> getFaculty() {
        return faculty;
    }
    
    public void setFaculty(Set<Faculty> faculty) {
        this.faculty = faculty;
    }
    
    public Set<Subject> getSubjects() {
        return subjects;
    }
    
    public void setSubjects(Set<Subject> subjects) {
        this.subjects = subjects;
    }
}
