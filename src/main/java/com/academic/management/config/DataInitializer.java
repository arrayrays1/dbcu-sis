package com.academic.management.config;

import com.academic.management.model.*;
import com.academic.management.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Component
public class DataInitializer implements CommandLineRunner {
    
    @Autowired
    private AdminRepository adminRepository;
    
    @Autowired
    private CourseRepository courseRepository;
    
    @Autowired
    private SubjectRepository subjectRepository;
    
    @Autowired
    private FacultyRepository facultyRepository;
    
    @Autowired
    private StudentRepository studentRepository;
    
    @Autowired
    private ScheduleRepository scheduleRepository;
    
    @Autowired
    private GradeRepository gradeRepository;
    
    @Autowired
    private EnrollmentRepository enrollmentRepository;
    
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @Override
    public void run(String... args) throws Exception {
        // Create admin
        if (adminRepository.count() == 0) {
            Admin admin = new Admin();
            admin.setName("Admin User");
            admin.setEmail("admin@admindbcu.com");
            admin.setPassword(passwordEncoder.encode("admin123"));
            admin.setRole(Role.ADMIN);
            adminRepository.save(admin);
            
            System.out.println("Admin created: admin@admindbcu.com / admin123");
        }
        
        // Create courses
        if (courseRepository.count() == 0) {
            Course itCourse = new Course();
            itCourse.setCode("IT");
            itCourse.setName("Information Technology");
            itCourse.setDescription("Bachelor of Science in Information Technology");
            courseRepository.save(itCourse);
            
            Course csCourse = new Course();
            csCourse.setCode("CS");
            csCourse.setName("Computer Science");
            csCourse.setDescription("Bachelor of Science in Computer Science");
            courseRepository.save(csCourse);
            
            // Create subjects for IT
            Subject programming = new Subject();
            programming.setCode("IT101");
            programming.setName("Programming 1");
            programming.setDescription("Introduction to Programming");
            programming.setCourse(itCourse);
            subjectRepository.save(programming);
            
            Subject database = new Subject();
            database.setCode("IT102");
            database.setName("Database Systems");
            database.setDescription("Introduction to Database Systems");
            database.setCourse(itCourse);
            subjectRepository.save(database);
            
            // Create subjects for CS
            Subject algorithms = new Subject();
            algorithms.setCode("CS101");
            algorithms.setName("Algorithms");
            algorithms.setDescription("Introduction to Algorithms");
            algorithms.setCourse(csCourse);
            subjectRepository.save(algorithms);
            
            Subject dataStructures = new Subject();
            dataStructures.setCode("CS102");
            dataStructures.setName("Data Structures");
            dataStructures.setDescription("Introduction to Data Structures");
            dataStructures.setCourse(csCourse);
            subjectRepository.save(dataStructures);
            
            // Create faculty
            Faculty itFaculty = new Faculty();
            itFaculty.setName("IT Faculty");
            itFaculty.setEmail("faculty@facultydbcu.com");
            itFaculty.setPassword(passwordEncoder.encode("faculty123"));
            itFaculty.setRole(Role.FACULTY);
            itFaculty.setCourse(itCourse);
            facultyRepository.save(itFaculty);
            
            Faculty csFaculty = new Faculty();
            csFaculty.setName("CS Faculty");
            csFaculty.setEmail("cs.faculty@facultydbcu.com");
            csFaculty.setPassword(passwordEncoder.encode("faculty123"));
            csFaculty.setRole(Role.FACULTY);
            csFaculty.setCourse(csCourse);
            facultyRepository.save(csFaculty);
            
            System.out.println("Faculty created: faculty@facultydbcu.com / faculty123");
            System.out.println("Faculty created: cs.faculty@facultydbcu.com / faculty123");
            
            // Create students
            Student itStudent = new Student();
            itStudent.setName("IT Student");
            itStudent.setEmail("student@studentdbcu.com");
            itStudent.setPassword(passwordEncoder.encode("student123"));
            itStudent.setRole(Role.STUDENT);
            itStudent.setStudentId("2023-0001");
            itStudent.setCourse(itCourse);
            studentRepository.save(itStudent);
            
            Student csStudent = new Student();
            csStudent.setName("CS Student");
            csStudent.setEmail("cs.student@studentdbcu.com");
            csStudent.setPassword(passwordEncoder.encode("student123"));
            csStudent.setRole(Role.STUDENT);
            csStudent.setStudentId("2023-0002");
            csStudent.setCourse(csCourse);
            studentRepository.save(csStudent);
            
            System.out.println("Student created: student@studentdbcu.com / student123");
            System.out.println("Student created: cs.student@studentdbcu.com / student123");
            
            // Create schedules
            Schedule programmingSchedule = new Schedule();
            programmingSchedule.setSubject(programming);
            programmingSchedule.setFaculty(itFaculty);
            programmingSchedule.setDay(DayOfWeek.MONDAY);
            programmingSchedule.setStartTime(LocalTime.of(8, 0));
            programmingSchedule.setEndTime(LocalTime.of(10, 0));
            programmingSchedule.setRoom("Room 101");
            programmingSchedule.setSemester("1");
            programmingSchedule.setAcademicYear("2023-2024");
            scheduleRepository.save(programmingSchedule);
            
            Schedule databaseSchedule = new Schedule();
            databaseSchedule.setSubject(database);
            databaseSchedule.setFaculty(itFaculty);
            databaseSchedule.setDay(DayOfWeek.WEDNESDAY);
            databaseSchedule.setStartTime(LocalTime.of(13, 0));
            databaseSchedule.setEndTime(LocalTime.of(15, 0));
            databaseSchedule.setRoom("Room 102");
            databaseSchedule.setSemester("1");
            databaseSchedule.setAcademicYear("2023-2024");
            scheduleRepository.save(databaseSchedule);
            
            Schedule algorithmsSchedule = new Schedule();
            algorithmsSchedule.setSubject(algorithms);
            algorithmsSchedule.setFaculty(csFaculty);
            algorithmsSchedule.setDay(DayOfWeek.TUESDAY);
            algorithmsSchedule.setStartTime(LocalTime.of(10, 0));
            algorithmsSchedule.setEndTime(LocalTime.of(12, 0));
            algorithmsSchedule.setRoom("Room 201");
            algorithmsSchedule.setSemester("1");
            algorithmsSchedule.setAcademicYear("2023-2024");
            scheduleRepository.save(algorithmsSchedule);
            
            Schedule dataStructuresSchedule = new Schedule();
            dataStructuresSchedule.setSubject(dataStructures);
            dataStructuresSchedule.setFaculty(csFaculty);
            dataStructuresSchedule.setDay(DayOfWeek.THURSDAY);
            dataStructuresSchedule.setStartTime(LocalTime.of(15, 0));
            dataStructuresSchedule.setEndTime(LocalTime.of(17, 0));
            dataStructuresSchedule.setRoom("Room 202");
            dataStructuresSchedule.setSemester("1");
            dataStructuresSchedule.setAcademicYear("2023-2024");
            scheduleRepository.save(dataStructuresSchedule);
            
            // Create enrollments
            Enrollment programmingEnrollment = new Enrollment();
            programmingEnrollment.setStudent(itStudent);
            programmingEnrollment.setSubject(programming);
            programmingEnrollment.setSemester("1");
            programmingEnrollment.setAcademicYear("2023-2024");
            programmingEnrollment.setEnrollmentDate(LocalDateTime.now());
            programmingEnrollment.setStatus(EnrollmentStatus.APPROVED);
            enrollmentRepository.save(programmingEnrollment);
            
            Enrollment databaseEnrollment = new Enrollment();
            databaseEnrollment.setStudent(itStudent);
            databaseEnrollment.setSubject(database);
            databaseEnrollment.setSemester("1");
            databaseEnrollment.setAcademicYear("2023-2024");
            databaseEnrollment.setEnrollmentDate(LocalDateTime.now());
            databaseEnrollment.setStatus(EnrollmentStatus.APPROVED);
            enrollmentRepository.save(databaseEnrollment);
            
            Enrollment algorithmsEnrollment = new Enrollment();
            algorithmsEnrollment.setStudent(csStudent);
            algorithmsEnrollment.setSubject(algorithms);
            algorithmsEnrollment.setSemester("1");
            algorithmsEnrollment.setAcademicYear("2023-2024");
            algorithmsEnrollment.setEnrollmentDate(LocalDateTime.now());
            algorithmsEnrollment.setStatus(EnrollmentStatus.APPROVED);
            enrollmentRepository.save(algorithmsEnrollment);
            
            Enrollment dataStructuresEnrollment = new Enrollment();
            dataStructuresEnrollment.setStudent(csStudent);
            dataStructuresEnrollment.setSubject(dataStructures);
            dataStructuresEnrollment.setSemester("1");
            dataStructuresEnrollment.setAcademicYear("2023-2024");
            dataStructuresEnrollment.setEnrollmentDate(LocalDateTime.now());
            dataStructuresEnrollment.setStatus(EnrollmentStatus.PENDING);
            enrollmentRepository.save(dataStructuresEnrollment);
            
            // Create grades
            Grade programmingGrade = new Grade();
            programmingGrade.setStudent(itStudent);
            programmingGrade.setSubject(programming);
            programmingGrade.setValue(85.5);
            programmingGrade.setSemester("1");
            programmingGrade.setAcademicYear("2023-2024");
            gradeRepository.save(programmingGrade);
            
            Grade databaseGrade = new Grade();
            databaseGrade.setStudent(itStudent);
            databaseGrade.setSubject(database);
            databaseGrade.setValue(90.0);
            databaseGrade.setSemester("1");
            databaseGrade.setAcademicYear("2023-2024");
            gradeRepository.save(databaseGrade);
            
            Grade algorithmsGrade = new Grade();
            algorithmsGrade.setStudent(csStudent);
            algorithmsGrade.setSubject(algorithms);
            algorithmsGrade.setValue(88.0);
            algorithmsGrade.setSemester("1");
            algorithmsGrade.setAcademicYear("2023-2024");
            gradeRepository.save(algorithmsGrade);
            
            System.out.println("Sample data created successfully!");
        }
    }
}
