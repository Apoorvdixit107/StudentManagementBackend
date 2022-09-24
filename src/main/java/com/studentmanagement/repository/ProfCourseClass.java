package com.studentmanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.studentmanagement.domain.ProfessorCourseCLass;
import com.studentmanagement.domain.ProfessorCourseClassId;

@Repository
public interface ProfCourseClass extends JpaRepository<ProfessorCourseCLass,ProfessorCourseClassId>{
    
}
