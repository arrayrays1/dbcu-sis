package com.academic.management.repository;

import com.academic.management.model.Course;
import com.academic.management.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    List<Student> findByCourse(Course course);
    Optional<Student> findByStudentId(String studentId);
    boolean existsByStudentId(String studentId);
}
