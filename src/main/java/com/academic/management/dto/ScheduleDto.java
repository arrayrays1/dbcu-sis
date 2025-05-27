package com.academic.management.dto;

import java.time.DayOfWeek;
import java.time.LocalTime;

public class ScheduleDto {
    private Long id;
    private Long subjectId;
    private String subjectCode;
    private String subjectName;
    private Long facultyId;
    private String facultyName;
    private DayOfWeek day;
    private LocalTime startTime;
    private LocalTime endTime;
    private String room;
    private String semester;
    private String academicYear;
    
    public ScheduleDto() {
    }
    
    public ScheduleDto(Long id, Long subjectId, String subjectCode, String subjectName, Long facultyId, String facultyName, DayOfWeek day, LocalTime startTime, LocalTime endTime, String room, String semester, String academicYear) {
        this.id = id;
        this.subjectId = subjectId;
        this.subjectCode = subjectCode;
        this.subjectName = subjectName;
        this.facultyId = facultyId;
        this.facultyName = facultyName;
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
    
    public Long getFacultyId() {
        return facultyId;
    }
    
    public void setFacultyId(Long facultyId) {
        this.facultyId = facultyId;
    }
    
    public String getFacultyName() {
        return facultyName;
    }
    
    public void setFacultyName(String facultyName) {
        this.facultyName = facultyName;
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
