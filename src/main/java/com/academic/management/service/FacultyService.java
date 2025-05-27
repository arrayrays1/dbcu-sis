package com.academic.management.service;

import com.academic.management.dto.CreateGradeRequest;
import com.academic.management.dto.GradeDto;
import com.academic.management.dto.ScheduleDto;
import com.academic.management.model.Faculty;
import com.academic.management.model.Grade;
import com.academic.management.model.Student;
import com.academic.management.model.Subject;
import com.academic.management.repository.FacultyRepository;
import com.academic.management.repository.GradeRepository;
import com.academic.management.repository.ScheduleRepository;
import com.academic.management.repository.StudentRepository;
import com.academic.management.repository.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FacultyService {
    
    @Autowired
    private FacultyRepository facultyRepository;
    
    @Autowired
    private ScheduleRepository scheduleRepository;
    
    @Autowired
    private StudentRepository studentRepository;
    
    @Autowired
    private SubjectRepository subjectRepository;
    
    @Autowired
    private GradeRepository gradeRepository;
    
    public List<ScheduleDto> getFacultySchedule(Long facultyId, String semester, String academicYear) {
        Optional<Faculty> facultyOptional = facultyRepository.findById(facultyId);
        if (facultyOptional.isEmpty()) {
            throw new RuntimeException("Faculty not found");
        }
        
        return scheduleRepository.findByFacultyAndSemesterAndAcademicYear(facultyOptional.get(), semester, academicYear)
                .stream()
                .map(schedule -> {
                    ScheduleDto dto = new ScheduleDto();
                    dto.setId(schedule.getId());
                    dto.setSubjectId(schedule.getSubject().getId());
                    dto.setSubjectCode(schedule.getSubject().getCode());
                    dto.setSubjectName(schedule.getSubject().getName());
                    dto.setFacultyId(schedule.getFaculty().getId());
                    dto.setFacultyName(schedule.getFaculty().getName());
                    dto.setDay(schedule.getDay());
                    dto.setStartTime(schedule.getStartTime());
                    dto.setEndTime(schedule.getEndTime());
                    dto.setRoom(schedule.getRoom());
                    dto.setSemester(schedule.getSemester());
                    dto.setAcademicYear(schedule.getAcademicYear());
                    return dto;
                })
                .collect(Collectors.toList());
    }
    
    @Transactional
    public GradeDto createOrUpdateGrade(CreateGradeRequest request) {
        Optional<Student> studentOptional = studentRepository.findById(request.getStudentId());
        if (studentOptional.isEmpty()) {
            throw new RuntimeException("Student not found");
        }
        
        Optional<Subject> subjectOptional = subjectRepository.findById(request.getSubjectId());
        if (subjectOptional.isEmpty()) {
            throw new RuntimeException("Subject not found");
        }
        
        Optional<Grade> existingGradeOptional = gradeRepository.findByStudentAndSubjectAndSemesterAndAcademicYear(
                studentOptional.get(), subjectOptional.get(), request.getSemester(), request.getAcademicYear());
        
        Grade grade;
        if (existingGradeOptional.isPresent()) {
            grade = existingGradeOptional.get();
            grade.setValue(request.getValue());
        } else {
            grade = new Grade();
            grade.setStudent(studentOptional.get());
            grade.setSubject(subjectOptional.get());
            grade.setValue(request.getValue());
            grade.setSemester(request.getSemester());
            grade.setAcademicYear(request.getAcademicYear());
        }
        
        grade = gradeRepository.save(grade);
        
        GradeDto dto = new GradeDto();
        dto.setId(grade.getId());
        dto.setStudentId(grade.getStudent().getId());
        dto.setStudentName(grade.getStudent().getName());
        dto.setStudentIdNumber(grade.getStudent().getStudentId());
        dto.setSubjectId(grade.getSubject().getId());
        dto.setSubjectCode(grade.getSubject().getCode());
        dto.setSubjectName(grade.getSubject().getName());
        dto.setValue(grade.getValue());
        dto.setSemester(grade.getSemester());
        dto.setAcademicYear(grade.getAcademicYear());
        
        return dto;
    }
    
    public List<GradeDto> getGradesBySubject(Long subjectId, String semester, String academicYear) {
        Optional<Subject> subjectOptional = subjectRepository.findById(subjectId);
        if (subjectOptional.isEmpty()) {
            throw new RuntimeException("Subject not found");
        }
        
        return gradeRepository.findAll().stream()
                .filter(grade -> grade.getSubject().getId().equals(subjectId) &&
                        grade.getSemester().equals(semester) &&
                        grade.getAcademicYear().equals(academicYear))
                .map(grade -> {
                    GradeDto dto = new GradeDto();
                    dto.setId(grade.getId());
                    dto.setStudentId(grade.getStudent().getId());
                    dto.setStudentName(grade.getStudent().getName());
                    dto.setStudentIdNumber(grade.getStudent().getStudentId());
                    dto.setSubjectId(grade.getSubject().getId());
                    dto.setSubjectCode(grade.getSubject().getCode());
                    dto.setSubjectName(grade.getSubject().getName());
                    dto.setValue(grade.getValue());
                    dto.setSemester(grade.getSemester());
                    dto.setAcademicYear(grade.getAcademicYear());
                    return dto;
                })
                .collect(Collectors.toList());
    }
}
