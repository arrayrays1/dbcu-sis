package com.academic.management.service;

import com.academic.management.dto.*;
import com.academic.management.model.*;
import com.academic.management.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AdminService {
    
    @Autowired
    private AdminRepository adminRepository;
    
    @Autowired
    private StudentRepository studentRepository;
    
    @Autowired
    private FacultyRepository facultyRepository;
    
    @Autowired
    private CourseRepository courseRepository;
    
    @Autowired
    private SubjectRepository subjectRepository;
    
    @Autowired
    private ScheduleRepository scheduleRepository;
    
    @Autowired
    private EnrollmentRepository enrollmentRepository;
    
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @Transactional
    public StudentDto createStudent(CreateStudentRequest request) {
        if (studentRepository.existsByStudentId(request.getStudentId())) {
            throw new RuntimeException("Student ID already exists");
        }
        
        Optional<Course> courseOptional = courseRepository.findById(request.getCourseId());
        if (courseOptional.isEmpty()) {
            throw new RuntimeException("Course not found");
        }
        
        Student student = new Student();
        student.setName(request.getName());
        student.setEmail(request.getEmail());
        student.setPassword(passwordEncoder.encode(request.getPassword()));
        student.setRole(Role.STUDENT);
        student.setStudentId(request.getStudentId());
        student.setCourse(courseOptional.get());

        student = studentRepository.save(student);
        
        StudentDto dto = new StudentDto();
        dto.setId(student.getId());
        dto.setName(student.getName());
        dto.setEmail(student.getEmail());
        dto.setRole(student.getRole());
        dto.setStudentId(student.getStudentId());
        dto.setCourseId(student.getCourse().getId());
        dto.setCourseName(student.getCourse().getName());
        
        return dto;
    }
    
    @Transactional
    public FacultyDto createFaculty(CreateFacultyRequest request) {
        Optional<Course> courseOptional = courseRepository.findById(request.getCourseId());
        if (courseOptional.isEmpty()) {
            throw new RuntimeException("Course not found");
        }
        
        Faculty faculty = new Faculty();
        faculty.setName(request.getName());
        faculty.setEmail(request.getEmail());
        faculty.setPassword(passwordEncoder.encode(request.getPassword()));
        faculty.setRole(Role.FACULTY);
        faculty.setCourse(courseOptional.get());
        
        faculty = facultyRepository.save(faculty);
        
        FacultyDto dto = new FacultyDto();
        dto.setId(faculty.getId());
        dto.setName(faculty.getName());
        dto.setEmail(faculty.getEmail());
        dto.setRole(faculty.getRole());
        dto.setCourseId(faculty.getCourse().getId());
        dto.setCourseName(faculty.getCourse().getName());
        
        return dto;
    }
    
    @Transactional
    public CourseDto createCourse(CreateCourseRequest request) {
        if (courseRepository.existsByCode(request.getCode())) {
            throw new RuntimeException("Course code already exists");
        }
        
        Course course = new Course();
        course.setCode(request.getCode());
        course.setName(request.getName());
        course.setDescription(request.getDescription());
        
        course = courseRepository.save(course);
        
        CourseDto dto = new CourseDto();
        dto.setId(course.getId());
        dto.setCode(course.getCode());
        dto.setName(course.getName());
        dto.setDescription(course.getDescription());
        
        return dto;
    }
    
    @Transactional
    public SubjectDto createSubject(CreateSubjectRequest request) {
        Optional<Course> courseOptional = courseRepository.findById(request.getCourseId());
        if (courseOptional.isEmpty()) {
            throw new RuntimeException("Course not found");
        }
        
        Subject subject = new Subject();
        subject.setCode(request.getCode());
        subject.setName(request.getName());
        subject.setDescription(request.getDescription());
        subject.setCourse(courseOptional.get());
        
        subject = subjectRepository.save(subject);
        
        SubjectDto dto = new SubjectDto();
        dto.setId(subject.getId());
        dto.setCode(subject.getCode());
        dto.setName(subject.getName());
        dto.setDescription(subject.getDescription());
        dto.setCourseId(subject.getCourse().getId());
        dto.setCourseName(subject.getCourse().getName());
        
        return dto;
    }
    
    @Transactional
    public ScheduleDto createSchedule(CreateScheduleRequest request) {
        Optional<Subject> subjectOptional = subjectRepository.findById(request.getSubjectId());
        if (subjectOptional.isEmpty()) {
            throw new RuntimeException("Subject not found");
        }
        
        Optional<Faculty> facultyOptional = facultyRepository.findById(request.getFacultyId());
        if (facultyOptional.isEmpty()) {
            throw new RuntimeException("Faculty not found");
        }
        
        Schedule schedule = new Schedule();
        schedule.setSubject(subjectOptional.get());
        schedule.setFaculty(facultyOptional.get());
        schedule.setDay(request.getDay());
        schedule.setStartTime(request.getStartTime());
        schedule.setEndTime(request.getEndTime());
        schedule.setRoom(request.getRoom());
        schedule.setSemester(request.getSemester());
        schedule.setAcademicYear(request.getAcademicYear());
        
        schedule = scheduleRepository.save(schedule);
        
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
    }
    
    @Transactional
    public EnrollmentDto updateEnrollmentStatus(Long enrollmentId, UpdateEnrollmentStatusRequest request) {
        Optional<Enrollment> enrollmentOptional = enrollmentRepository.findById(enrollmentId);
        if (enrollmentOptional.isEmpty()) {
            throw new RuntimeException("Enrollment not found");
        }
        
        Enrollment enrollment = enrollmentOptional.get();
        enrollment.setStatus(request.getStatus());
        
        if (request.getStatus() == EnrollmentStatus.REJECTED && request.getRejectionReason() != null) {
            enrollment.setRejectionReason(request.getRejectionReason());
        }
        
        enrollment = enrollmentRepository.save(enrollment);
        
        return mapToEnrollmentDto(enrollment);
    }
    
    public List<EnrollmentDto> getPendingEnrollments() {
        return enrollmentRepository.findByStatus(EnrollmentStatus.PENDING)
                .stream()
                .map(this::mapToEnrollmentDto)
                .collect(Collectors.toList());
    }
    
    public List<StudentDto> getAllStudents() {
        return studentRepository.findAll()
                .stream()
                .map(this::mapToStudentDto)
                .collect(Collectors.toList());
    }
    
    public List<FacultyDto> getAllFaculty() {
        return facultyRepository.findAll()
                .stream()
                .map(this::mapToFacultyDto)
                .collect(Collectors.toList());
    }
    
    public List<CourseDto> getAllCourses() {
        return courseRepository.findAll()
                .stream()
                .map(this::mapToCourseDto)
                .collect(Collectors.toList());
    }
    
    public List<SubjectDto> getAllSubjects() {
        return subjectRepository.findAll()
                .stream()
                .map(this::mapToSubjectDto)
                .collect(Collectors.toList());
    }
    
    public List<ScheduleDto> getAllSchedules() {
        return scheduleRepository.findAll()
                .stream()
                .map(this::mapToScheduleDto)
                .collect(Collectors.toList());
    }
    
    private StudentDto mapToStudentDto(Student student) {
        StudentDto dto = new StudentDto();
        dto.setId(student.getId());
        dto.setName(student.getName());
        dto.setEmail(student.getEmail());
        dto.setRole(student.getRole());
        dto.setStudentId(student.getStudentId());
        dto.setCourseId(student.getCourse().getId());
        dto.setCourseName(student.getCourse().getName());
        return dto;
    }
    
    private FacultyDto mapToFacultyDto(Faculty faculty) {
        FacultyDto dto = new FacultyDto();
        dto.setId(faculty.getId());
        dto.setName(faculty.getName());
        dto.setEmail(faculty.getEmail());
        dto.setRole(faculty.getRole());
        dto.setCourseId(faculty.getCourse().getId());
        dto.setCourseName(faculty.getCourse().getName());
        return dto;
    }
    
    private CourseDto mapToCourseDto(Course course) {
        CourseDto dto = new CourseDto();
        dto.setId(course.getId());
        dto.setCode(course.getCode());
        dto.setName(course.getName());
        dto.setDescription(course.getDescription());
        return dto;
    }
    
    private SubjectDto mapToSubjectDto(Subject subject) {
        SubjectDto dto = new SubjectDto();
        dto.setId(subject.getId());
        dto.setCode(subject.getCode());
        dto.setName(subject.getName());
        dto.setDescription(subject.getDescription());
        dto.setCourseId(subject.getCourse().getId());
        dto.setCourseName(subject.getCourse().getName());
        return dto;
    }
    
    private ScheduleDto mapToScheduleDto(Schedule schedule) {
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
    }
    
    private EnrollmentDto mapToEnrollmentDto(Enrollment enrollment) {
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
    }
}
