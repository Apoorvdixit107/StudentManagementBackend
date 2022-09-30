package com.studentmanagement.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.studentmanagement.domain.ProfessorCourseCLass;
import com.studentmanagement.domain.ProfessorCourseClassId;

@Repository
public interface ProfCourseClass extends JpaRepository<ProfessorCourseCLass,ProfessorCourseClassId>{
    


@Query(value = "select * from professor_courseclass where professor_courseclass.class_id LIKE :%class_id% AND professor_courseclass.course_id LIKE :%course_id% AND professor_courseclass.employee_id LIKE :%emp_id%",nativeQuery = true)    
List<ProfessorCourseCLass> findByIds(String class_id,String emp_id,String course_id);


}
