package com.academic.management.repository;

import com.academic.management.model.Grade;
import com.academic.management.model.Student;
import com.academic.management.model.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface GradeRepository extends JpaRepository<Grade, Long> {
    List<Grade> findByStudent(Student student);
    List<Grade> findByStudentAndSemesterAndAcademicYear(Student student, String semester, String academicYear);
    Optional<Grade> findByStudentAndSubjectAndSemesterAndAcademicYear(Student student, Subject subject, String semester, String academicYear);
}
