package com.studentmanagement.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.studentmanagement.domain.ProfessorCourse;
import com.studentmanagement.domain.ProfessorCourseId;

public interface ProfCourseRepository extends JpaRepository<ProfessorCourse,ProfessorCourseId> {
    

    @Query(value = "select professor_course.course_id from professor_course where professor_course.employee_id=:empId",nativeQuery = true)
    List<String> findAllProfCourse(String empId);
}
