package com.academic.management.model;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "faculty")
public class Faculty extends User {
    
    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;
    
    @OneToMany(mappedBy = "faculty")
    private Set<Schedule> schedules = new HashSet<>();
    
    public Faculty() {
        super();
    }
    
    public Faculty(Long id, String name, String email, String password, Role role, Course course) {
        super(id, name, email, password, role);
        this.course = course;
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
}
