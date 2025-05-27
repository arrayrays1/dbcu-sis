package com.academic.management.repository;

import com.academic.management.model.Course;
import com.academic.management.model.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SubjectRepository extends JpaRepository<Subject, Long> {
    List<Subject> findByCourse(Course course);
    Optional<Subject> findByCode(String code);
}
