package com.studentmanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.studentmanagement.domain.StudentCourse;
import com.studentmanagement.domain.StudentCourseId;

import java.util.List;

@Repository
public interface StudentCourseRepository extends JpaRepository<StudentCourse,StudentCourseId>{
    @Query(value = "select * from student_course where student_course.roll_no = :rollNo",nativeQuery = true)
    List<StudentCourse> findByRollNo(String rollNo);
}
