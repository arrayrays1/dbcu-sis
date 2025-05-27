package com.academic.management.repository;

import com.academic.management.model.Course;
import com.academic.management.model.Faculty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FacultyRepository extends JpaRepository<Faculty, Long> {
    List<Faculty> findByCourse(Course course);
}
