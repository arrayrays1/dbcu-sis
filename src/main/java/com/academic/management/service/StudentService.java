package com.academic.management.service;

import com.academic.management.dto.CreateEnrollmentRequest;
import com.academic.management.dto.EnrollmentDto;
import com.academic.management.dto.GradeDto;
import com.academic.management.dto.ScheduleDto;
import com.academic.management.model.*;
import com.academic.management.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StudentService {
    
    @Autowired
    private StudentRepository studentRepository;
    
    @Autowired
    private SubjectRepository subjectRepository;
    
    @Autowired
    private GradeRepository gradeRepository;
    
    @Autowired
    private EnrollmentRepository enrollmentRepository;
    
    @Autowired
    private ScheduleRepository scheduleRepository;
    
    public List<GradeDto> getStudentGrades(Long studentId, String semester, String academicYear) {
        Optional<Student> studentOptional = studentRepository.findById(studentId);
        if (studentOptional.isEmpty()) {
            throw new RuntimeException("Student not found");
        }
        
        return gradeRepository.findByStudentAndSemesterAndAcademicYear(studentOptional.get(), semester, academicYear)
                .stream()
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
    
    @Transactional
    public EnrollmentDto createEnrollment(Long studentId, CreateEnrollmentRequest request) {
        Optional<Student> studentOptional = studentRepository.findById(studentId);
        if (studentOptional.isEmpty()) {
            throw new RuntimeException("Student not found");
        }
        
        Optional<Subject> subjectOptional = subjectRepository.findById(request.getSubjectId());
        if (subjectOptional.isEmpty()) {
            throw new RuntimeException("Subject not found");
        }
        
        // Check if enrollment already exists
        Optional<Enrollment> existingEnrollment = enrollmentRepository.findByStudentAndSubjectAndSemesterAndAcademicYear(
                studentOptional.get(), subjectOptional.get(), request.getSemester(), request.getAcademicYear());
        
        if (existingEnrollment.isPresent()) {
            throw new RuntimeException("You are already enrolled in this subject for the given semester and academic year");
        }
        
        Enrollment enrollment = new Enrollment();
        enrollment.setStudent(studentOptional.get());
        enrollment.setSubject(subjectOptional.get());
        enrollment.setSemester(request.getSemester());
        enrollment.setAcademicYear(request.getAcademicYear());
        enrollment.setEnrollmentDate(LocalDateTime.now());
        enrollment.setStatus(EnrollmentStatus.PENDING);
        
        enrollment = enrollmentRepository.save(enrollment);
        
        EnrollmentDto dto = new EnrollmentDto();
        dto.setId(enrollment.getId());
        dto.setStudentId(enrollment.getStudent().getId());
        dto.setStudentName(enrollment.getStudent().getName());
        dto.setStudentIdNumber(enrollment.getStudent().getStudentId());
        dto.setSubjectId(enrollment.getSubject().getId());
        dto.setSubjectCode(enrollment.getSubject().getCode());
        dto.setSubjectName(enrollment.getSubject().getName());
        dto.setSemester(enrollment.getSemester());
        dto.setAcademicYear(enrollment.getAcademicYear());
        dto.setEnrollmentDate(enrollment.getEnrollmentDate());
        dto.setStatus(enrollment.getStatus());
        
        return dto;
    }
    
    public List<EnrollmentDto> getStudentEnrollments(Long studentId, String semester, String academicYear) {
        Optional<Student> studentOptional = studentRepository.findById(studentId);
        if (studentOptional.isEmpty()) {
            throw new RuntimeException("Student not found");
        }
        
        return enrollmentRepository.findByStudentAndSemesterAndAcademicYear(studentOptional.get(), semester, academicYear)
                .stream()
                .map(enrollment -> {
                    EnrollmentDto dto = new EnrollmentDto();
                    dto.setId(enrollment.getId());
                    dto.setStudentId(enrollment.getStudent().getId());
                    dto.setStudentName(enrollment.getStudent().getName());
                    dto.setStudentIdNumber(enrollment.getStudent().getStudentId());
                    dto.setSubjectId(enrollment.getSubject().getId());
                    dto.setSubjectCode(enrollment.getSubject().getCode());
                    dto.setSubjectName(enrollment.getSubject().getName());
                    dto.setSemester(enrollment.getSemester());
                    dto.setAcademicYear(enrollment.getAcademicYear());
                    dto.setEnrollmentDate(enrollment.getEnrollmentDate());
                    dto.setStatus(enrollment.getStatus());
                    dto.setRejectionReason(enrollment.getRejectionReason());
                    return dto;
                })
                .collect(Collectors.toList());
    }
    
    public List<ScheduleDto> getStudentSchedule(Long studentId, String semester, String academicYear) {
        Optional<Student> studentOptional = studentRepository.findById(studentId);
        if (studentOptional.isEmpty()) {
            throw new RuntimeException("Student not found");
        }
        
        Student student = studentOptional.get();
        
        // Get approved enrollments for the student
        List<Enrollment> approvedEnrollments = enrollmentRepository.findByStudentAndSemesterAndAcademicYear(student, semester, academicYear)
                .stream()
                .filter(enrollment -> enrollment.getStatus() == EnrollmentStatus.APPROVED)
                .collect(Collectors.toList());
        
        // Get schedules for the approved subjects
        return approvedEnrollments.stream()
                .flatMap(enrollment -> scheduleRepository.findAll().stream()
                        .filter(schedule -> schedule.getSubject().getId().equals(enrollment.getSubject().getId()) &&
                                schedule.getSemester().equals(semester) &&
                                schedule.getAcademicYear().equals(academicYear)))
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
}
