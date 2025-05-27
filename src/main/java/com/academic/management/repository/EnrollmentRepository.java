package com.academic.management.repository;

import com.academic.management.model.Enrollment;
import com.academic.management.model.EnrollmentStatus;
import com.academic.management.model.Student;
import com.academic.management.model.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EnrollmentRepository extends JpaRepository<Enrollment, Long> {
    List<Enrollment> findByStudent(Student student);
    List<Enrollment> findByStatus(EnrollmentStatus status);
    List<Enrollment> findByStudentAndSemesterAndAcademicYear(Student student, String semester, String academicYear);
    Optional<Enrollment> findByStudentAndSubjectAndSemesterAndAcademicYear(Student student, Subject subject, String semester, String academicYear);
}
