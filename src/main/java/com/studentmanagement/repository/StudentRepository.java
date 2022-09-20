package com.studentmanagement.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.studentmanagement.domain.Student;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, String>{
    Optional<Student> findByMobile(String mobile);
    Optional<Student> findByRollNo(String roll);

    @Query(value = "SELECT a.roll_no from student a , student_course b WHERE a.roll_no = b.roll_no AND a.branch = :branch AND a.section = :section AND a.semester = :semester AND b.course_id = :courseId ",nativeQuery = true)
    List<String> findStudentsToAssign(String branch,String section,String semester,String courseId);
    
    
    
    
}
