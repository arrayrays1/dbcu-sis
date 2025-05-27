package com.academic.management.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.DayOfWeek;
import java.time.LocalTime;

public class CreateScheduleRequest {
    @NotNull(message = "Subject is required")
    private Long subjectId;
    
    @NotNull(message = "Faculty is required")
    private Long facultyId;
    
    @NotNull(message = "Day is required")
    private DayOfWeek day;
    
    @NotNull(message = "Start time is required")
    private LocalTime startTime;
    
    @NotNull(message = "End time is required")
    private LocalTime endTime;
    
    @NotBlank(message = "Room is required")
    private String room;
    
    @NotBlank(message = "Semester is required")
    private String semester;
    
    @NotBlank(message = "Academic year is required")
    private String academicYear;
    
    public CreateScheduleRequest() {
    }
    
    public CreateScheduleRequest(Long subjectId, Long facultyId, DayOfWeek day, LocalTime startTime, LocalTime endTime, String room, String semester, String academicYear) {
        this.subjectId = subjectId;
        this.facultyId = facultyId;
        this.day = day;
        this.startTime = startTime;
        this.endTime = endTime;
        this.room = room;
        this.semester = semester;
        this.academicYear = academicYear;
    }
    
    public Long getSubjectId() {
        return subjectId;
    }
    
    public void setSubjectId(Long subjectId) {
        this.subjectId = subjectId;
    }
    
    public Long getFacultyId() {
        return facultyId;
    }
    
    public void setFacultyId(Long facultyId) {
        this.facultyId = facultyId;
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
