package com.academic.management.repository;

import com.academic.management.model.Faculty;
import com.academic.management.model.Schedule;
import com.academic.management.model.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
    List<Schedule> findByFaculty(Faculty faculty);
    List<Schedule> findBySubject(Subject subject);
    List<Schedule> findBySubjectCourseId(Long courseId);
    List<Schedule> findByFacultyAndSemesterAndAcademicYear(Faculty faculty, String semester, String academicYear);
    List<Schedule> findBySubjectCourseIdAndSemesterAndAcademicYear(Long courseId, String semester, String academicYear);
}
