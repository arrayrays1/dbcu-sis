package com.academic.management.model;

import jakarta.persistence.*;
import java.time.DayOfWeek;
import java.time.LocalTime;

@Entity
@Table(name = "schedules")
public class Schedule {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "subject_id", nullable = false)
    private Subject subject;
    
    @ManyToOne
    @JoinColumn(name = "faculty_id")
    private Faculty faculty;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private DayOfWeek day;
    
    @Column(nullable = false)
    private LocalTime startTime;
    
    @Column(nullable = false)
    private LocalTime endTime;
    
    @Column(nullable = false)
    private String room;
    
    @Column(nullable = false)
    private String semester;
    
    @Column(nullable = false)
    private String academicYear;
    
    public Schedule() {
    }
    
    public Schedule(Long id, Subject subject, Faculty faculty, DayOfWeek day, LocalTime startTime, LocalTime endTime, String room, String semester, String academicYear) {
        this.id = id;
        this.subject = subject;
        this.faculty = faculty;
        this.day = day;
        this.startTime = startTime;
        this.endTime = endTime;
        this.room = room;
        this.semester = semester;
        this.academicYear = academicYear;
    }
    
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public Subject getSubject() {
        return subject;
    }
    
    public void setSubject(Subject subject) {
        this.subject = subject;
    }
    
    public Faculty getFaculty() {
        return faculty;
    }
    
    public void setFaculty(Faculty faculty) {
        this.faculty = faculty;
    }
    
    public DayOfWeek getDay() {
        return day;
    }
    
    public void setDay(DayOfWeek day) {
        this.day = day;
    }
    
    public LocalTime getStartTime() {
        return startTime;
    }
    
    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }
    
    public LocalTime getEndTime() {
        return endTime;
    }
    
    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }
    
    public String getRoom() {
        return room;
    }
    
    public void setRoom(String room) {
        this.room = room;
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
