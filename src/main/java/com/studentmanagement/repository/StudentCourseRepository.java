package com.studentmanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.studentmanagement.domain.StudentCourse;
import com.studentmanagement.domain.StudentCourseId;

@Repository
public interface StudentCourseRepository extends JpaRepository<StudentCourse,StudentCourseId>{
    
}
